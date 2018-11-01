package com.example.administrator.albumdemo.reptile;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.albumdemo.DetailActivity;
import com.example.administrator.albumdemo.R;
import com.example.administrator.albumdemo.adapter.AlbumAdapter;
import com.example.administrator.albumdemo.adapter.NetPicAdapter;
import com.example.administrator.albumdemo.application.AlbumApplication;
import com.example.administrator.albumdemo.base.BaseMvpActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.albumdemo.DetailActivity.PIC;

/*
 * Author: liyudong
 * Date: 2018/10/31 0031
 * Description:
 */
public class ReptileActivity extends BaseMvpActivity<ReptilePresenter> implements ReptileContract.View {

    private RecyclerView ryNet;
    private ProgressBar progressBar;
    private AppCompatButton btSearch;
    private AppCompatEditText etHttp;
    private List<String> netPics = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private NetPicAdapter picAdapter;

    @Override
    protected int layoutId() {
        return R.layout.activity_reptile;
    }

    @Override
    protected void inject() {
        DaggerReptileComponent.builder()
                .appComponent(AlbumApplication.appComponent())
                .reptileModule(new ReptileModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        ryNet = findViewById(R.id.ry_net);
        progressBar = findViewById(R.id.progressBar);
        etHttp = findViewById(R.id.et_http);
        btSearch = findViewById(R.id.bt_search);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        picAdapter = new NetPicAdapter(R.layout.item_net, netPics);
        ryNet.setAdapter(picAdapter);
        ryNet.setLayoutManager(layoutManager);

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startReptile(etHttp.getText().toString());
            }
        });

        picAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ReptileActivity.this, DetailActivity.class);
                intent.putExtra(PIC, picAdapter.getData().get(position));
                intent.putExtra("isFile", false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ReptileActivity.this,
                            view.findViewById(R.id.iv_album), ReptileActivity.this.getResources().getString(R.string.album)).toBundle());
                }else {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    public void reptilSuccess(List<String> photos) {
        Log.d("reptilSuccess", "reptilSuccess: " + photos.toString());
        picAdapter.addData(photos);
    }

    @Override
    public void onFailed(Throwable error, String message) {
        Snackbar.make(ryNet,message, 1000).show();
    }

    @Override
    public void startLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
