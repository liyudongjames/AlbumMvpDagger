package com.example.administrator.albumdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/*
 * Author: liyudong
 * Date: 2018/10/31 0031
 * Description:
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity  {

    @Inject
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        inject();
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    protected abstract int layoutId();

    protected abstract void inject();

    protected abstract void initView();

    protected abstract void initData();

}
