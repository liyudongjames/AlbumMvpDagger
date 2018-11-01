package com.example.administrator.albumdemo.loadingimg;

import com.example.administrator.albumdemo.base.IBasePresenter;
import com.example.administrator.albumdemo.base.IBaseView;
import com.example.administrator.albumdemo.reptile.ReptileContract;

import java.util.List;

import io.reactivex.Observable;

/*
 * Author: liyudong
 * Date: 2018/11/1 0001
 * Description:
 */
public interface LoadingImgContract {

    interface View extends IBaseView<ReptileContract.Presenter> {
        void onUploadImg(Integer value);
    }

    interface Presenter extends IBasePresenter {
        void startUpLoad();
    }

    interface Repository {
        Observable<Integer> upLoadDataForTest();
    }

}
