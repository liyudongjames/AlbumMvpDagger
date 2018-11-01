package com.example.administrator.albumdemo.reptile;

import android.util.Log;

import com.example.administrator.albumdemo.application.AlbumApplication;
import com.example.administrator.albumdemo.base.BaseNet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/*
 * Author: liyudong
 * Date: 2018/10/31 0031
 * Description:
 */
public class ReptileRepository implements ReptileContract.Repository {

    protected AlbumApplication albumApplication;

    public ReptileRepository(AlbumApplication albumApplication){
        this.albumApplication = albumApplication;
    }

    @Override
    public Observable<List<String>> startReptile(String url) {
        return AlbumApplication.appComponent()
                .provideRetrofit()
                .newBuilder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(BaseNet.class)
                .pureRequest()
                .flatMap(new Function<String, ObservableSource<List<String>>>() {
                    @Override
                    public ObservableSource<List<String>> apply(String htmlStr) {

                        final List<String> strings = new ArrayList<>();
                        String img = "";
                        Pattern p_image;
                        Matcher m_image;
                        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
                        p_image = Pattern.compile
                                (regEx_img, Pattern.CASE_INSENSITIVE);
                        m_image = p_image.matcher(htmlStr);

                        if(m_image.find()){
                            img = m_image.group();
                            // 匹配<img>中的src数据
                            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
                            while (m.find()) {
                                String url = m.group(1);
                                if(m.group(1).contains("http")) {
                                    strings.add(m.group(1));
                                }else {
                                    strings.add("http:" + m.group(1));

                                }
                            }
                        }

                        return Observable.create(new ObservableOnSubscribe<List<String>>() {
                            @Override
                            public void subscribe(ObservableEmitter<List<String>> emitter) {
                                try {
                                    emitter.onNext(strings);
                                }catch (Exception e){
                                    emitter.onError(e);
                                }finally {
                                    emitter.onComplete();
                                }
                            }
                        });
                    }
                });
    }
}
