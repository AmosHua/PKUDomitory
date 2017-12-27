package com.choosedormitory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by amoshua on 17/12/2017.
 */

public class FinishActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        ((TextView)findViewById(R.id.name)).setText(Util.student.getData().getName());
        ((TextView)findViewById(R.id.num)).setText(Util.student.getData().getStudentid());
        ((TextView)findViewById(R.id.gender)).setText(Util.student.getData().getGender());
        ((TextView)findViewById(R.id.status)).setText("已完成办理");
        Intent i = getIntent();
        if(i.hasExtra("build")){
            ((TextView)findViewById(R.id.build_no)).setText(i.getStringExtra("build"));
            ((TextView)findViewById(R.id.dormitory_no)).setText(Util.student.getData().getRoom());
        }else {
            ((TextView)findViewById(R.id.build_no)).setText(Util.student.getData().getBuilding());
            ((TextView)findViewById(R.id.dormitory_no)).setText(Util.student.getData().getRoom());
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
