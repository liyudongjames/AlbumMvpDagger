package com.example.administrator.albumdemo.base;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/*
 * Author: liyudong
 * Date: 2018/10/31 0031
 * Description:
 */
public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter {

    protected V view;
    protected CompositeDisposable mCompositeSubscription = new CompositeDisposable();

    public BasePresenter(V view){
        this.view = view;
    }

    protected void addDisposable(Disposable disposable){
        mCompositeSubscription.add(disposable);
    }

    @Override
    public void dropView() {
        if(!mCompositeSubscription.isDisposed()){
            mCompositeSubscription.dispose();
        }
        mCompositeSubscription = null;
        view = null;
        Log.d("dropView","dropView");
    }
    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<T, T> obs_io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
