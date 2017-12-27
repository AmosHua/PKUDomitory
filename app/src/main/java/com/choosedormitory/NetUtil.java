package com.choosedormitory;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by amoshua on 14/12/2017.
 */

public class NetUtil {
    public static String URL_LOGIN="https://api.mysspku.com/index.php/V1/MobileCourse/Login";
    public static String URL_GetDetail = "https://api.mysspku.com/index.php/V1/MobileCourse/getDetail";
    public static String URL_GetRoom = "https://api.mysspku.com/index.php/V1/MobileCourse/getRoom?gender=";
    public static String URL_Commit = "https://api.mysspku.com/index.php/V1/MobileCourse/SelectRoom";
    public static final int NETWORN_NONE = 0;
    public static final int NETWORN_WIFI = 1;
    public static final int NETWORN_MOBILE = 2;
    public static RequestQueue mQueue;
    public static void initHttpManager(Context context){
        if(context != null)
        {
            mQueue = Volley.newRequestQueue(context);
        }
    }
    public static void GetRequest(String url,  final NetWorkCallBack callback){
        SsX509TrustManager.allowAllSSL();
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.Success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.Failure(error.toString());
            }
        });
        mQueue.add(mJsonObjectRequest);
        Log.i("GET", "star");
    }
    public static void PostRequest(String url,final Map<String,String > params,final ChooseActivity callback){
        Log.i("params",params.toString());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("POST", response );
                        try {
                            callback.Success(new JSONObject(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.Failure(error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                return params;
            }
        };
        mQueue.add(postRequest);
        Log.i("POST", "star");
    }
    public static int getNetworkState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return NETWORN_NONE;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            return NETWORN_MOBILE;
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return NETWORN_WIFI;
        }
        return NETWORN_NONE;
    }

}
