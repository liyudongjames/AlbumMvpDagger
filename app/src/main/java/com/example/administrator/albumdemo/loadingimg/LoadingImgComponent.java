package com.example.administrator.albumdemo.loadingimg;

import com.example.administrator.albumdemo.application.AppComponent;
import com.example.administrator.albumdemo.application.AppModule;
import com.example.administrator.albumdemo.base.ActivityScope;
import com.example.administrator.albumdemo.base.BaseInject;

import dagger.Component;

/*
 * Author: liyudong
 * Date: 2018/11/1 0001
 * Description:
 */
@ActivityScope
@Component(modules = LoadingImgModule.class, dependencies = AppComponent.class)
public interface LoadingImgComponent extends BaseInject<LoadingImgActivity> {
}
