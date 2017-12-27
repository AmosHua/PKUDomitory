package com.choosedormitory;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amoshua on 23/11/2017.
 */
public class LoginActivity extends AppCompatActivity  implements NetWorkCallBack{

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mLoginFormView;
    private CircularProgressButton mEmailSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mEmailSignInButton = (CircularProgressButton) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setEnabled(false);
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
    }


    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
            mEmailSignInButton.setProgress(-1);
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
            mEmailSignInButton.setProgress(-1);
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            Map<String,String> params = new HashMap<>();
            params.put("username",username);
            params.put("password",password);
            NetUtil.GetRequest(NetUtil.URL_LOGIN+"?username="+username+"&password="+password,this);
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */



    @Override
    public void Success(JSONObject response) {
        mEmailSignInButton.setEnabled(true);
        Intent i = new Intent();
        int errcode = -1;
        try {
            errcode = response.getInt("errcode");
            if(errcode==0){
                if(response.getJSONObject("data").has("errmsg")){
                    mEmailSignInButton.setProgress(50);
                    NetUtil.GetRequest(NetUtil.URL_GetDetail+"?stuid="+mEmailView.getText().toString(),this);
                }else {

                    mEmailSignInButton.setProgress(100);
                    Util.student = Student.getStudent(response.toString());
                    if(Util.student.getData().getRoom()==null){
                        i.setClass(this,DetailActivity.class);
                    }else{
                        i.setClass(this,FinishActivity.class);
                    }
                    startActivity(i);
                }
            }else{
                mEmailSignInButton.setProgress(-1);
               // mPasswordView.setError(String.valueOf(errcode));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
      //  mPasswordView.setError(getString(R.string.error_incorrect_password));
    }

    @Override
    public void Failure(String response) {
        mEmailSignInButton.setEnabled(true);
        Toast.makeText(this,response,Toast.LENGTH_SHORT).show();
    }
}

