package com.example.administrator.albumdemo.reptile;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.albumdemo.R;
import com.example.administrator.albumdemo.application.AlbumApplication;
import com.example.administrator.albumdemo.base.BaseMvpActivity;

import java.util.List;

/*
 * Author: liyudong
 * Date: 2018/10/31 0031
 * Description:
 */
public class ReptileActivity extends BaseMvpActivity<ReptilePresenter> implements ReptileContract.View {

    private TextView textView;
    private ProgressBar progressBar;

    @Override
    protected int layoutId() {
        return R.layout.activity_reptile;
    }

    @Override
    protected void inject() {
        DaggerReptileComponent.builder()
                .appComponent(AlbumApplication.appComponent())
                .reptileModule(new ReptileModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        textView = findViewById(R.id.textView2);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    protected void initData() {
        presenter.startReptile("http://www.baidu.com/");
    }

    @Override
    public void reptilSuccess(List<String> photos) {
        textView.setText(photos.toString());
    }

    @Override
    public void onFailed(Throwable error, String message) {
        textView.setText(message);
    }

    @Override
    public void startLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
