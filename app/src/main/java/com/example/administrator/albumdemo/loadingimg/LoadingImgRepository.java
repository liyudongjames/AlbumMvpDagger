package com.example.administrator.albumdemo.loadingimg;

import android.os.Build;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/*
 * Author: liyudong
 * Date: 2018/11/1 0001
 * Description:
 */
public class LoadingImgRepository implements LoadingImgContract.Repository {
    @Override
    public Observable<Integer> upLoadDataForTest() {
        return Observable.interval(1000,1000, TimeUnit.MILLISECONDS)
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long aLong) throws Exception {
                        int intValue = aLong.intValue();
                        return intValue;
                    }
                });
    }
}
