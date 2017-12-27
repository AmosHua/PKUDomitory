package com.choosedormitory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.List;

/**
 * Created by amoshua on 17/12/2017.
 */
public class MyRoommateRecylerViewAdapter extends RecyclerSwipeAdapter<MyRoommateRecylerViewAdapter.MyViewHolder> {
    private Context context;
    private SelectActivity.romove romove;
    public List<Roommate> getData(){
        return list;
    }

    public void setDate(List<Roommate> list) {
        for(int i =0;i<list.size();i++){
            Log.i("add before params",list.get(i).getNo());
        }
        this.list = list;
        for(int i =0;i<list.size();i++){
            Log.i("add after params",list.get(i).getNo());
        }
    }

    private List<Roommate> list;
    public MyRoommateRecylerViewAdapter(Context context, List<Roommate> list) {
        this.context = context;
        this.list = list;
    }
    // 实例化展示的view
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_roommate, parent, false);
        return new MyViewHolder(view);
    }
    //绑定数据
    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        viewHolder.name.setText(list.get(position).getNo());
        viewHolder.name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                romove.Addroommate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        viewHolder.vcode.setText(list.get(position).getVocd());
        viewHolder.vcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                romove.Addroommate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        viewHolder.bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                romove.onRemove(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return position;
    }

    public void setCallBack(SelectActivity.romove callBack) {
        this.romove = callBack;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private SwipeLayout swipeLayout;
        private TextView bottom;
        public EditText name;
        public EditText vcode;
        public MyViewHolder(View itemView) {
            super(itemView);
            swipeLayout=(SwipeLayout)itemView.findViewById(R.id.swipe_layout);
            bottom=(TextView) itemView.findViewById(R.id.bottom);
            name =(EditText)itemView.findViewById(R.id.surface_name);
            vcode = (EditText)itemView.findViewById(R.id.surface_vcode);
        }
    }
}
