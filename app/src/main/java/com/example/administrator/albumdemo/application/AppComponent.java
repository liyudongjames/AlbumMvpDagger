package com.example.administrator.albumdemo.application;

import android.content.SharedPreferences;
import javax.inject.Singleton;
import dagger.Component;
import retrofit2.Retrofit;

/*
 * Author: liyudong
 * Date: 2018/10/31 0031
 * Description:
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    SharedPreferences providesSharedPrefrences();

    AlbumApplication provideApplication();

    Retrofit provideRetrofit();
}
