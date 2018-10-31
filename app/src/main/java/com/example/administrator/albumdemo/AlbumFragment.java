package com.example.administrator.albumdemo;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.albumdemo.adapter.AlbumAdapter;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.administrator.albumdemo.DetailActivity.PIC;

/*
 * Author: liyudong
 * Date: 2018/10/30
 * Description:
 */
public class AlbumFragment extends Fragment {

    private List<String> imagePath = new ArrayList<>();
    private AlbumAdapter albumAdapter;

    private RecyclerView ryAlbum;
    private ProgressBar progress;
    private View rootView;

    @SuppressLint("ValidFragment")
    private AlbumFragment(){

    }

    public static AlbumFragment getInstance(Bundle bundle){
        AlbumFragment albumFragment = new AlbumFragment();
        albumFragment.setArguments(bundle);
        return albumFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.album_fragment, container, false);
        ryAlbum = (RecyclerView) findViewByIdFg(R.id.ry_album);
        progress = (ProgressBar) findViewByIdFg(R.id.progress);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),
                3, GridLayoutManager.VERTICAL,false);

        albumAdapter = new AlbumAdapter(R.layout.item_album, imagePath, gridLayoutManager);
        ryAlbum.setAdapter(albumAdapter);
        ryAlbum.setLayoutManager(gridLayoutManager);

        getImages();

        albumAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(PIC, albumAdapter.getData().get(position));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                            view.findViewById(R.id.iv_album), getActivity().getResources().getString(R.string.album)).toBundle());
                }else {
                    startActivity(intent);
                }
            }
        });

        return rootView;
    }

    private View findViewByIdFg(int id){
        return rootView.findViewById(id);
    }

    private void showToast(String content){
        Toast.makeText(getActivity(),content,Toast.LENGTH_SHORT).show();
    }

    //获取图片的路径和父路径 及 图片size
    private void getImages() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            showToast("检测到没有内存卡");
            return;
        }

        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = getActivity().getContentResolver();
                Cursor mCursor = null;
                try {
                    mCursor = mContentResolver.query(mImageUri, null,
                            MediaStore.Images.Media.MIME_TYPE + "=? or " +
                                    MediaStore.Images.Media.MIME_TYPE + "=? or " +
                                    MediaStore.Images.Media.MIME_TYPE + "=? or " +
                                    MediaStore.Images.Media.MIME_TYPE + "=?",
                            new String[]{"image/jpeg", "image/png", "image/jpg", "image/gif"},
                            MediaStore.Images.Media.DATE_TAKEN + " DESC");//获取图片的cursor，按照时间倒序（发现没卵用)

                    while (mCursor.moveToNext()) {
                        String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));// 1.获取图片的路径
                        imagePath.add(path);
                    }
                    emitter.onNext(imagePath);
                    emitter.onComplete();
                }catch (Exception e){
                    emitter.onError(e);
                }finally {
                    mCursor.close();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> strings) {
                albumAdapter.addData(strings);
                TransitionManager.beginDelayedTransition(ryAlbum,new AutoTransition());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                progress.setVisibility(View.GONE);
            }
        });
    }


}