package com.example.administrator.albumdemo.reptile;

import com.example.administrator.albumdemo.application.AppComponent;
import com.example.administrator.albumdemo.base.ActivityScope;

import javax.inject.Singleton;

import dagger.Component;

/*
 * Author: liyudong
 * Date: 2018/10/31 0031
 * Description:
 */
@ActivityScope
@Component(modules = ReptileModule.class, dependencies = AppComponent.class)
public interface ReptileComponent {
    void inject(ReptileActivity activity);
}
