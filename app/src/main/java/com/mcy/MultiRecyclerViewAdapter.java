package com.mcy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: DefineStudyDemo
 * @Package: com.mcy
 * @ClassName: MultiRecyclerViewAdapter
 * @Description: java类作用描述
 * @Author: macy
 * @CreateDate: 2020/5/10 17:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/10 17:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MultiRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TYPE_BANNER = 0;
    private static int TYPE_SMALL_ICON = 1;
    private Context mContext;
    private List<String> mData;

    public MultiRecyclerViewAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else {
            return TYPE_SMALL_ICON;
        }
    }


    public void addData(String ele) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(ele);
    }

    private static class BannerViewHolder extends RecyclerView.ViewHolder {
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private static class SmallIconViewHolder extends RecyclerView.ViewHolder {

        public SmallIconViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
