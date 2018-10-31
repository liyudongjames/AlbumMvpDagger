package com.example.administrator.albumdemo.reptile;

import com.example.administrator.albumdemo.base.IBasePresenter;
import com.example.administrator.albumdemo.base.IBaseView;

import java.util.List;

import io.reactivex.Observable;

/*
 * Author: liyudong
 * Date: 2018/10/31 0031
 * Description:
 */
public interface ReptileContract {

    interface View extends IBaseView<Presenter> {
        void reptilSuccess(List<String> photos);
    }

    interface Presenter extends IBasePresenter {
        void startReptile(String url);
    }

    interface Repository {
        Observable<List<String>> startReptile(String url);
    }

}
