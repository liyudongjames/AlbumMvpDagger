package com.example.administrator.albumdemo.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.albumdemo.R;
import java.io.File;
import java.util.List;

/*
 * Author: liyudong
 * Date: 2018/10/30 0030
 * Description:
 */
public class AlbumAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private GridLayoutManager gridLayoutManager;

    public AlbumAdapter(int layoutResId, @Nullable List<String> data, GridLayoutManager gridLayoutManager) {
        super(layoutResId, data);
        this.gridLayoutManager = gridLayoutManager;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ViewGroup.LayoutParams parm = helper.itemView.getLayoutParams();
        parm.height = gridLayoutManager.getWidth() / gridLayoutManager.getSpanCount()
                - 2 * helper.itemView.getPaddingLeft() - 2 * ((ViewGroup.MarginLayoutParams) parm).leftMargin;
        helper.itemView.setLayoutParams(parm);

        ImageView imageView = helper.getView(R.id.iv_album);

        Glide.with(mContext)
                .load(new File(item))
                .into(imageView);
    }

}