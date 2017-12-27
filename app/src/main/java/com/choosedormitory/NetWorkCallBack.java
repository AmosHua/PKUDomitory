package com.choosedormitory;


import org.json.JSONObject;

/**
 * Created by amoshua on 14/12/2017.
 */

public interface NetWorkCallBack {
    void Success(JSONObject response);
    void Failure(String response);
}
