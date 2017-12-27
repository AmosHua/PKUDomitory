package com.choosedormitory;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.service.chooser.ChooserTargetService;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dd.CircularProgressButton;

import org.json.JSONObject;

/**
 * Created by amoshua on 14/12/2017.
 */

public class DetailActivity extends AppCompatActivity implements View.OnClickListener, NetWorkCallBack {
    private CircularProgressButton btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ((TextView)findViewById(R.id.name)).setText(Util.student.getData().getName());
        ((TextView)findViewById(R.id.num)).setText(Util.student.getData().getStudentid());
        ((TextView)findViewById(R.id.gender)).setText(Util.student.getData().getGender());
        ((TextView)findViewById(R.id.captcha)).setText(Util.student.getData().getVcode());
        btn = (CircularProgressButton)findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void onClick(View v) {
        v.setEnabled(false);
        String gender = (Util.student.getData().getGender().equals("男"))?"1":"2";
        NetUtil.GetRequest(NetUtil.URL_GetRoom+gender,this);

    }

    @Override
    public void Success(JSONObject response) {
        btn.setProgress(100);
        btn.setEnabled(true);
        Util.room = Room.getRoom(response.toString());
        startActivity(new Intent(this, ChooseActivity.class));
        this.finish();
    }

    @Override
    public void Failure(String response) {
        btn.setProgress(-1);
        btn.setEnabled(false);

    }
}
