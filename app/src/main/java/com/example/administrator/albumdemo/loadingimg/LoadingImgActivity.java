package com.example.administrator.albumdemo.loadingimg;

import android.view.View;

import com.bumptech.glide.Glide;
import com.example.administrator.albumdemo.R;
import com.example.administrator.albumdemo.application.AlbumApplication;
import com.example.administrator.albumdemo.base.BaseMvpActivity;
import com.example.administrator.albumdemo.view.LoadingImageView;

/*
 * Author: liyudong
 * Date: 2018/11/1 0001
 * Description:
 */
public class LoadingImgActivity extends BaseMvpActivity<LoadingImgPresenter> implements LoadingImgContract.View {

    private LoadingImageView livImage;

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
        livImage = findViewById(R.id.liv_image);
        Glide.with(this)
                .load("https://www.baidu.com/img/bd_logo1.png?where=super")
                .into(livImage);

        livImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                livImage.startAnimatior();
            }
        });
    }

    @Override
    protected void initData() {

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
