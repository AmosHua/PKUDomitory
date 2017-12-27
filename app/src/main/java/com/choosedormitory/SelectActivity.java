package com.choosedormitory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.dd.CircularProgressButton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amoshua on 14/12/2017.
 */

public class SelectActivity extends AppCompatActivity implements View.OnClickListener,NetWorkCallBack {
    private CircularProgressButton btn;
    private RecyclerView recyclerView;
    private List<Roommate> list = new ArrayList<>();
    private List<String> buildname = new ArrayList<>();
    private List<Integer> buildID = new ArrayList<>();
    private MyRoommateRecylerViewAdapter myRecyclerViewAdapter;
    private TextView Add;
    private Spinner buildno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        ((TextView)findViewById(R.id.name)).setText(Util.student.getData().getName());
        ((TextView)findViewById(R.id.num)).setText(Util.student.getData().getStudentid());
        ((TextView)findViewById(R.id.gender)).setText(Util.student.getData().getGender());
        ((TextView)findViewById(R.id.captcha)).setText(Util.student.getData().getVcode());
        buildno = (Spinner)findViewById(R.id.choose_no);
        buildname.add("未选择");
        int number = Util.room.getData().get_$5();
        //获取当前床位数，若无则隐藏
        if(number>0){
            ((TextView)findViewById(R.id.no5)).setText(String.valueOf(number)+"个");
            buildID.add(5);
            buildname.add("5号楼");
        }else{
            findViewById(R.id.layout_5).setVisibility(View.GONE);
        }
         number = Util.room.getData().get_$13();
        if(number>0){
            ((TextView)findViewById(R.id.no13)).setText(String.valueOf(number)+"个");
            buildID.add(13);
            buildname.add("13号楼");
        }else{
            findViewById(R.id.layout_13).setVisibility(View.GONE);
        }
        number = Util.room.getData().get_$14();
        if(number>0){
            ((TextView)findViewById(R.id.no14)).setText(String.valueOf(number)+"个");
            buildID.add(14);
            buildname.add("14号楼");
        }else{
            findViewById(R.id.layout_14).setVisibility(View.GONE);
        }
        number = Util.room.getData().get_$8();
        if(number>0){
            ((TextView)findViewById(R.id.no8)).setText(String.valueOf(number)+"个");
            buildID.add(8);
            buildname.add("8号楼");
        }else{
            findViewById(R.id.layout_8).setVisibility(View.GONE);
        }
        number = Util.room.getData().get_$9();
        if(number>0){
            ((TextView)findViewById(R.id.no9)).setText(String.valueOf(number)+"个");
            buildID.add(9);
            buildname.add("9号楼");
        }else{
            findViewById(R.id.layout_9).setVisibility(View.GONE);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, buildname);
        //Spinner控件选择宿舍
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buildno.setAdapter(adapter);
        buildno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btn.setProgress(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //recyclerview展示
        recyclerView = (RecyclerView)findViewById(R.id.roommates);
        myRecyclerViewAdapter=new MyRoommateRecylerViewAdapter(this,list);
        myRecyclerViewAdapter.setCallBack(new romove() {
            @Override
            public void onRemove(int position) {
                btn.setProgress(0);
                if(list.size()==3){
                    Add.setVisibility(View.VISIBLE);
                }
                for(int i = 0 ; i <list.size();i++){
                    View v = recyclerView.getChildAt(i);
                    if(recyclerView.getChildViewHolder(v)!=null){
                        MyRoommateRecylerViewAdapter.MyViewHolder holder =
                                (MyRoommateRecylerViewAdapter.MyViewHolder) recyclerView.getChildViewHolder(v);
                        list.get(i).setNo(holder.name.getText().toString());
                        list.get(i).setVocd(holder.vcode.getText().toString());
                    }
                }
                list.remove(position);
                myRecyclerViewAdapter.setDate(list);
                myRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void Addroommate() {
                btn.setProgress(0);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myRecyclerViewAdapter);

        btn = (CircularProgressButton)findViewById(R.id.btn);
        btn.setOnClickListener(this);

        Add = (TextView)findViewById(R.id.add);
        Add.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                btn.setProgress(0);
                if(list.size()==2){
                    Add.setVisibility(View.GONE);
                }
                for(int i = 0 ; i <list.size();i++){
                    View view = recyclerView.getChildAt(i);
                    if(recyclerView.getChildViewHolder(view)!=null){
                        MyRoommateRecylerViewAdapter.MyViewHolder holder =
                                (MyRoommateRecylerViewAdapter.MyViewHolder) recyclerView.getChildViewHolder(view);
                        list.get(i).setNo(holder.name.getText().toString());
                        list.get(i).setVocd(holder.vcode.getText().toString());
                    }
                }
                list.add(list.size(),new Roommate("",""));
                Log.i("params",String.valueOf(list.size()));
                myRecyclerViewAdapter.setDate(list);
                myRecyclerViewAdapter.notifyDataSetChanged();
                break;
            case R.id.btn:
                boolean cancle = false;
                btn.setEnabled(false);
                Map<String,String> params = new HashMap<>();
                params.put("stuid",Util.student.getData().getStudentid());
                params.put("num",String.valueOf(myRecyclerViewAdapter.getItemCount()+1));
                if(buildno.getSelectedItemPosition() <1){
                    btn.setProgress(-1);
                    cancle=true;
                    btn.setErrorText("未选择宿舍号");
                    btn.setEnabled(true);
                }else {
                    params.put("buildingNo", String.valueOf(buildID.get(buildno.getSelectedItemPosition()-1)));

                    for (int i = 0; i < myRecyclerViewAdapter.getItemCount(); i++) {
                        View view = recyclerView.getChildAt(i);
                        String no = "stu" + String.valueOf(i + 1) + "id";
                        String vcode = "v" + String.valueOf(i + 1) + "code";
                        if (recyclerView.getChildViewHolder(view) != null) {
                            MyRoommateRecylerViewAdapter.MyViewHolder holder = (MyRoommateRecylerViewAdapter.MyViewHolder) recyclerView.getChildViewHolder(view);
                            if (holder.name.getText().toString().equals("")) {
                                btn.setProgress(-1);
                                cancle = true;
                                btn.setErrorText("缺少同住人信息");
                                btn.setEnabled(true);
                            } else {
                                params.put(no, holder.name.getText().toString());
                                params.put(vcode, holder.vcode.getText().toString());
                            }
                        }
                    }
                }
                if(!cancle) NetUtil.PostRequest(NetUtil.URL_Commit, params, this);

                break;
        }

    }

    @Override
    public void Success(JSONObject response) {
        btn.setEnabled(true);
        btn.setProgress(50);
        btn.setProgress(100);
        Intent i = new Intent(this,FinishActivity.class);
        i.putExtra("build",buildname.get(buildno.getSelectedItemPosition()));
        startActivity(i);
        this.finish();
    }

    @Override
    public void Failure(String response) {
        btn.setEnabled(true);
        btn.setProgress(-1);
        btn.setErrorText("网络连接异常");

    }

    public  interface romove{
        void onRemove(int i  );
        void Addroommate();
    }

}
