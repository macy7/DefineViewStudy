package com.mcy.define;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy.define
 * @ClassName: HomeAdapter
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/4/17 21:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/17 21:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    public OnItemClickListener mOnItemClickListener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_adaper, parent, false);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewHome.setText(position % 2 == 0 ? "1" : "2");
        holder.itemView.setTag(position);
        ViewGroup.LayoutParams layoutParams = holder.textViewHome.getLayoutParams();
        layoutParams.height = (int) (position + 100 + Math.random() * 300);
        holder.textViewHome.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return 40;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemLongClick(v, (int) v.getTag());
        }
        return true;
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewHome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHome = itemView.findViewById(R.id.tv_HomeName);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
}
