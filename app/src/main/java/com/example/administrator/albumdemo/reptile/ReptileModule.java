package com.example.administrator.albumdemo.reptile;

import com.example.administrator.albumdemo.application.AlbumApplication;
import com.example.administrator.albumdemo.base.ActivityScope;

import dagger.Module;
import dagger.Provides;

/*
 * Author: liyudong
 * Date: 2018/10/31 0031
 * Description:
 */
@Module
public class ReptileModule {

    private ReptileContract.View view;

    public ReptileModule(ReptileContract.View view){
        this.view = view;
    }

    @Provides
    public ReptileContract.View provideReptileView(){
        return view;
    }

    @ActivityScope
    @Provides
    public ReptileContract.Presenter providReptilePresenter(){
        return new ReptilePresenter(view);
    }

    @Provides
    public ReptileContract.Repository provideReptileRepository(AlbumApplication application){
        return new ReptileRepository(application);
    }

}
