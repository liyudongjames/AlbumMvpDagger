package com.example.administrator.albumdemo.loadingimg;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.albumdemo.R;
import com.example.administrator.albumdemo.adapter.UpLoadAdapter;
import com.example.administrator.albumdemo.application.AlbumApplication;
import com.example.administrator.albumdemo.base.BaseMvpActivity;
import com.example.administrator.albumdemo.view.LoadingImageView;

import java.util.ArrayList;
import java.util.List;

/*
 * Author: liyudong
 * Date: 2018/11/1 0001
 * Description:
 */
public class LoadingImgActivity extends BaseMvpActivity<LoadingImgPresenter> implements LoadingImgContract.View {

    private RecyclerView ryUpLoad;
    private BaseQuickAdapter baseQuickAdapter;
    private LoadingImageView iv_loading;

    @Override
    protected int layoutId() {
        return R.layout.activity_loading_img;
    }

    @Override
    protected void inject() {
        DaggerLoadingImgComponent.builder()
                .appComponent(AlbumApplication.appComponent())
                .loadingImgModule(new LoadingImgModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        ryUpLoad = findViewById(R.id.ry_upLoad);
        iv_loading = findViewById(R.id.iv_loading);

        baseQuickAdapter = new UpLoadAdapter(R.layout.item_upload,new ArrayList<String>());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4,LinearLayoutManager.VERTICAL,false);
        ryUpLoad.setAdapter(baseQuickAdapter);
        ryUpLoad.setLayoutManager(gridLayoutManager);
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final LoadingImageView livImage = (LoadingImageView) view.findViewById(R.id.iv_album);
                livImage.setWaveSpeed(45)
                        .setOffset(20)
                        .startAnimatior();
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
                valueAnimator.setDuration(10000);
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        livImage.setProgress((Integer) animation.getAnimatedValue());
                    }
                });
                valueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                        livImage.OnSuccuess();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        livImage.OnSuccuess();
                    }
                });
                valueAnimator.start();

            }
        });
        Glide.with(this)
                .load("https://www.baidu.com/img/bd_logo1.png?where=super")
                .into(iv_loading);
        iv_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_loading.setWaveSpeed(45)
                        .setOffset(50)
                        .startAnimatior();
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
                valueAnimator.setDuration(2000);
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        iv_loading.setProgress((Integer) animation.getAnimatedValue());
                    }
                });
                valueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                        iv_loading.OnSuccuess();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        iv_loading.OnSuccuess();
                    }
                });
                valueAnimator.start();
            }
        });

        iv_loading.setVisibility(View.GONE);
        ryUpLoad.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        List<String> datas = new ArrayList<>();
        datas.add("https://www.baidu.com/img/bd_logo1.png?where=super");
        datas.add("https://www.baidu.com/img/bd_logo1.png?where=super");
        datas.add("https://www.baidu.com/img/bd_logo1.png?where=super");
        datas.add("https://www.baidu.com/img/bd_logo1.png?where=super");
        baseQuickAdapter.addData(datas);
    }

    @Override
    public void onUploadImg(Integer value) {

    }

    @Override
    public void onFailed(Throwable error, String message) {

    }

    @Override
    public void startLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
