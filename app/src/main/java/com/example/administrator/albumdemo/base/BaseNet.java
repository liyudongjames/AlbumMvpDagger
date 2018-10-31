package com.example.administrator.albumdemo.base;

import io.reactivex.Observable;
import retrofit2.http.GET;

/*
 * Author: liyudong
 * Date: 2018/10/31 0031
 * Description:
 */
public interface BaseNet {

    @GET("/")
    Observable<String> pureRequest();

}
