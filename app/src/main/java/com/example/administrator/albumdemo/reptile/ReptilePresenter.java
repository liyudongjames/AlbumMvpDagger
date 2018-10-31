package com.example.administrator.albumdemo.reptile;

import com.example.administrator.albumdemo.application.AlbumApplication;
import com.example.administrator.albumdemo.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/*
 * Author: liyudong
 * Date: 2018/10/31 0031
 * Description:
 */
public class ReptilePresenter extends BasePresenter<ReptileContract.View> implements ReptileContract.Presenter{

    @Inject
    ReptileContract.Repository mRepository;

    @Inject
    AlbumApplication albumApplication;

    @Inject
    public ReptilePresenter(ReptileContract.View view){
        super(view);
    }

    @Override
    public void startReptile(String url) {
        mRepository.startReptile(url)
                .compose(BasePresenter.<List<String>>obs_io_main())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                        view.startLoading();
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        view.reptilSuccess(strings);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFailed(e, e.toString());
                        view.hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        view.hideLoading();
                    }
                });
    }

}
