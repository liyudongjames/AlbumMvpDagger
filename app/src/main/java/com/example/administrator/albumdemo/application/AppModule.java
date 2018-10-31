package com.example.administrator.albumdemo.application;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/*
 * Author: liyudong
 * Date: 2018/10/31 0031
 * Description:
 */
@Module
public class AppModule {

    private AlbumApplication albumApplication;

    public AppModule(AlbumApplication application){
        this.albumApplication = application;
    }

    @Provides
    public AlbumApplication provideAlbumApplication(){
        return albumApplication;
    }

    @Singleton
    @Provides
    public SharedPreferences providesSharedPreferences(){
        return albumApplication.getSharedPreferences("album", Context.MODE_PRIVATE);
    }

}
