package com.example.administrator.albumdemo.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.albumdemo.R;

import java.util.List;

/*
 * Author: liyudong
 * Date: 2018/11/2 0002
 * Description:
 */
public class UpLoadAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public UpLoadAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext)
                .load(item)
                .into((AppCompatImageView) helper.getView(R.id.iv_album));
    }
}
