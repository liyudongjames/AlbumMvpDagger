package com.example.administrator.albumdemo.application;

import android.app.Application;

/*
 * Author: liyudong
 * Date: 2018/10/31 0031
 * Description:
 */
public class AlbumApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
    }

    public static AppComponent appComponent(){
        return appComponent;
    }

    public String logMe(){
        return "U got a Album Application";
    }
}
