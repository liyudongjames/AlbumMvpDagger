package com.example.administrator.albumdemo.loadingimg;

import com.example.administrator.albumdemo.base.ActivityScope;

import dagger.Module;
import dagger.Provides;

/*
 * Author: liyudong
 * Date: 2018/11/1 0001
 * Description:
 */
@Module
public class LoadingImgModule {

    private LoadingImgContract.View view;

    public LoadingImgModule(LoadingImgContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public LoadingImgContract.View provideLoadingImgView(){
        return view;
    }

    @ActivityScope
    @Provides
    public LoadingImgContract.Repository provideLoadingImgRepository(){
        return new LoadingImgRepository();
    }

}
