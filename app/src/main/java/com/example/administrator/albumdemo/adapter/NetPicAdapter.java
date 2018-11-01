package com.example.administrator.albumdemo.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.albumdemo.R;

import java.util.List;

/*
 * Author: liyudong
 * Date: 2018/11/1 0001
 * Description:
 */
public class NetPicAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public NetPicAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imageView = helper.getView(R.id.iv_album);

        Glide.with(mContext)
                .load(item)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

}
