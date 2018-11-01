package com.example.administrator.albumdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.example.administrator.albumdemo.R;

/*
 * Author: liyudong
 * Date: 2018/11/1
 * Description:
 */
public class LoadingImageView extends AppCompatImageView {
    private Paint paint;
    private Path path;
    private ValueAnimator valueAnimator;   // view宽度
    private int width;
    // view高度
    private int height;

    // 波浪高低偏移量
    private int offset = 100;

    // X轴，view的偏移量
    private int xoffset = 0;
    private int xoffset2 = 0;

    // view的Y轴高度
    private int viewY = 0;
    private int viewY2 = 0;

    // 波浪速度
    private int waveSpeed = 40;

    public LoadingImageView(Context context) {
        super(context);
        initPaint();
    }

    public LoadingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public LoadingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){
        path = new Path();
        paint = new Paint();
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(getResources().getColor(android.R.color.background_dark));
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(60);

        canvas.drawRect(0,0,getWidth(),getHeight(),paint);

        path.reset();

//        viewY = height / 2;

        // 绘制屏幕内的波浪
        path.moveTo(xoffset, viewY);
        path.quadTo(width / 4 + xoffset, viewY - offset, width / 2 + xoffset, viewY);
        path.moveTo(width / 2 + xoffset, viewY);
        path.quadTo(width / 4 * 3 + xoffset, viewY + offset, width + xoffset, viewY);

        // 绘制屏幕外的波浪
        path.moveTo(xoffset - width, viewY);
        path.quadTo(width / 4 + xoffset - width, viewY - offset, width / 2 + xoffset - width, viewY);
        path.moveTo(width / 2 + xoffset - width, viewY);
        path.quadTo(width / 4 * 3 + xoffset - width, viewY + offset, width + xoffset - width, viewY);

        path.moveTo(width + xoffset, viewY);
        path.lineTo(width + xoffset, height);
        path.lineTo(xoffset - width, height);
        path.lineTo(xoffset - width, viewY);

        path.close();

        paint.setAlpha(100);
//        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.rgb(205, 243, 246));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);

        canvas.drawPath(path, paint);

        path.reset();
        // 绘制屏幕内的波浪
        path.moveTo(xoffset2, viewY2);
        path.quadTo(width / 4 + xoffset2,  viewY2 + offset, width / 2 + xoffset2, viewY2);
        path.moveTo(width / 2 + xoffset2, viewY2);
        path.quadTo(width / 4 * 3 + xoffset2, viewY2 - offset, width + xoffset2, viewY2);

        // 绘制屏幕外的波浪
        path.moveTo(xoffset2 - width, viewY2);
        path.quadTo(width / 4 + xoffset2 - width, viewY2 + offset, width / 2 + xoffset2 - width, viewY2);
        path.moveTo(width / 2 + xoffset2 - width, viewY2);
        path.quadTo(width / 4 * 3 + xoffset2 - width,  viewY2 - offset, width + xoffset2 - width, viewY2);

        path.moveTo(width + xoffset2, viewY2);
        path.lineTo(width + xoffset2, height);
        path.lineTo(xoffset2 - width, height);
        path.lineTo(xoffset2 - width, viewY2);

        path.close();
        paint.setColor(Color.rgb(89, 186, 231));
        canvas.drawPath(path, paint);

    }

    public void startAnimatior(){
        valueAnimator = ValueAnimator.ofFloat(0, width);
        valueAnimator.setDuration(waveSpeed * 10);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(Animation.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float change = (float) animation.getAnimatedValue();
                        xoffset = (int) change;
                    invalidate();
                }
            });
        valueAnimator.start();

        ValueAnimator valueAnimator2 = ValueAnimator.ofFloat(0, height + offset);
        valueAnimator2.setDuration(20000);
        valueAnimator2.setInterpolator(new LinearInterpolator());
        valueAnimator2.setRepeatCount(Animation.INFINITE);
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float change = (float) animation.getAnimatedValue();
                viewY = height + offset - (int) change;
            }
        });
        valueAnimator2.start();

        ValueAnimator valueAnimator3 = ValueAnimator.ofFloat(0, width);
        valueAnimator3.setDuration(waveSpeed * 20);
        valueAnimator3.setInterpolator(new LinearInterpolator());
        valueAnimator3.setRepeatCount(Animation.INFINITE);
        valueAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float change = (float) animation.getAnimatedValue();
                xoffset2 = (int) change;
                invalidate();
            }
        });
        valueAnimator3.start();

        ValueAnimator valueAnimator4 = ValueAnimator.ofFloat(0, height + offset);
        valueAnimator4.setDuration(20000);
        valueAnimator4.setInterpolator(new LinearInterpolator());
        valueAnimator4.setRepeatCount(Animation.INFINITE);
        valueAnimator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float change = (float) animation.getAnimatedValue();
                viewY2 = height + offset - (int) change;
            }
        });
        valueAnimator4.start();
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        //设置一个默认值，就是这个View的默认宽度为500，这个看我们自定义View的要求
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {//相当于我们设置为wrap_content
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {//相当于我们设置为match_parent或者为一个具体的值
            result = specSize;
        }
        width = result;
        return result;
    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        height = specSize;
        viewY = height + offset;
        viewY2 = height + offset;
        return result;
    }


}
