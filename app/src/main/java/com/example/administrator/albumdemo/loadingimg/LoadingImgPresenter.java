package com.example.administrator.albumdemo.loadingimg;

import com.example.administrator.albumdemo.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/*
 * Author: liyudong
 * Date: 2018/11/1 0001
 * Description:
 */
public class LoadingImgPresenter extends BasePresenter<LoadingImgContract.View>
        implements LoadingImgContract.Presenter{

    @Inject
    LoadingImgContract.Repository repository;

    @Inject
    public LoadingImgPresenter(LoadingImgContract.View view) {
        super(view);
    }

    @Override
    public void startUpLoad() {
        repository.upLoadDataForTest()
                .compose(BasePresenter.<Integer>obs_io_main())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
