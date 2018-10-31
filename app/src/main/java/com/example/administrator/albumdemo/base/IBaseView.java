package com.example.administrator.albumdemo.base;

/*
 * Author: liyudong
 * Date: 2018/10/31 0031
 * Description:
 */
public interface IBaseView<P extends IBasePresenter> {

    void onFailed(Throwable error, String message);

    void startLoading();

    void hideLoading();

}
