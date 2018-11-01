package com.example.administrator.albumdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

public class DetailActivity extends AppCompatActivity {

    public final static String PIC = "com.example.administrator.albumdemo.pic";

    private boolean isFile = true;

    private AppCompatImageView iv_detail;

    private String picPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        iv_detail = findViewById(R.id.iv_detail);

        picPath = getIntent().getStringExtra(PIC);
        isFile = getIntent().getBooleanExtra("isFile", true);

        File file = new File(picPath);
        String prefix = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".")+1);

        if(isFile) {
            if (prefix.equals("gif")) {
                Glide.with(this)
                        .load(file)
                        .asGif()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(iv_detail);
            } else {
                Glide.with(this)
                        .load(file)
                        .into(iv_detail);
            }
        }else {
            Glide.with(this)
                    .load(picPath)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv_detail);
        }
    }
}
