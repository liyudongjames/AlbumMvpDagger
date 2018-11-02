package com.example.administrator.albumdemo.view;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

/*
 * Author: liyudong
 * Date: 2018/11/1
 * Description:
 */
public class LoadingImageView extends AppCompatImageView {

    private Paint paint;
    private Path path;
    private Path successePath;
    private int width;
    // view高度
    private int height;
    // 波浪高低偏移量
    private int offset;
    // X轴，view的偏移量
    private int xoffset;
    private int xoffset2;
    // view的Y轴高度
    private int viewY;
    // 波浪速度
    private int waveSpeed;
    private int waveColor;

    private boolean isRun;
    private ValueAnimator frountWaveAnim;
    private ValueAnimator backWaveAnim;
    private State state;

    private ValueAnimator successedAnim;
    private ValueAnimator successedAnimAlpha;
    private ValueAnimator successedAnimpath;
    private float pathprogress;

    // 这个视图拥有的状态
    public enum State {
        NONE,
        LOADING,
        SUCCESSED,
        ERROR
    }

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
        successePath = new Path();
        waveColor = Color.rgb(89, 186, 231);
        offset = 80;
        xoffset = 0;
        xoffset2 = 0;
        viewY = 0;
        waveSpeed = 40;
        state = State.NONE;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (state){
            case NONE:
                break;
            case LOADING:
                if(isRun) {
                    paint.setColor(getResources().getColor(android.R.color.background_dark));
                    paint.setStyle(Paint.Style.FILL);
                    paint.setAlpha(100);

                    canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

                    paint.setAlpha(255);
                    paint.setColor(waveColor);
                    paint.setAntiAlias(true);
                    paint.setStrokeWidth(5);
                    drawWave(canvas);

                    paint.setAlpha(150);
                    drawWaveBack(canvas);
                }
                break;
            case ERROR:
                break;
            case SUCCESSED:
                canvas.drawRect(0,0, width, height, paint);
                successePath = new Path();

                successePath.moveTo(width / 12 * 4, height / 2);
                successePath.lineTo(width / 3 + width / 7, height / 2 + height / 6);
                successePath.lineTo(width / 12 * 9 , height / 2  - height / 10);

                Path circlePath = new Path();
                circlePath.addCircle(width/2, height /2 , width > height ? height / 3 : width /  3, Path.Direction.CCW);

                Paint pathPaint = new Paint();
                pathPaint.setStyle(Paint.Style.STROKE);
                pathPaint.setColor(getResources().getColor(android.R.color.holo_green_light));
                pathPaint.setStrokeWidth(10);
                pathPaint.setStrokeCap(Paint.Cap.ROUND);
                pathPaint.setStrokeJoin(Paint.Join.ROUND);
                pathPaint.setAntiAlias(true);

                PathMeasure pathMeasure = new PathMeasure(successePath,false);
                Path path2 = new Path();
                pathMeasure.getSegment(0, pathprogress * pathMeasure.getLength(), path2, true);

                PathMeasure circleMeasure = new PathMeasure(circlePath, false);
                Path path3 = new Path();
                circleMeasure.getSegment(0, pathprogress * circleMeasure.getLength(), path3, true);

                canvas.drawPath(path2, pathPaint);
                canvas.drawPath(path3, pathPaint);

                break;
        }

    }

    private void drawWave(Canvas canvas){
        path.reset();
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
        canvas.drawPath(path, paint);
    }

    private void drawWaveBack(Canvas canvas){
        path.reset();
        // 绘制屏幕内的波浪
        path.moveTo(xoffset2, viewY);
        path.quadTo(width / 4 + xoffset2,  viewY + offset, width / 2 + xoffset2, viewY);
        path.moveTo(width / 2 + xoffset2, viewY);
        path.quadTo(width / 4 * 3 + xoffset2, viewY - offset, width + xoffset2, viewY);

        // 绘制屏幕外的波浪
        path.moveTo(xoffset2 - width, viewY);
        path.quadTo(width / 4 + xoffset2 - width, viewY + offset, width / 2 + xoffset2 - width, viewY);
        path.moveTo(width / 2 + xoffset2 - width, viewY);
        path.quadTo(width / 4 * 3 + xoffset2 - width,  viewY - offset, width + xoffset2 - width, viewY);

        path.moveTo(width + xoffset2, viewY);
        path.lineTo(width + xoffset2, height);
        path.lineTo(xoffset2 - width, height);
        path.lineTo(xoffset2 - width, viewY);

        path.close();
        canvas.drawPath(path, paint);
    }

    private void successedAnim(){
        successedAnim = ValueAnimator.ofInt(Color.rgb(89, 186, 231), android.R.color.black);
        successedAnim.setDuration(300);
        successedAnim.setEvaluator(new ArgbEvaluator());
        successedAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                paint.setColor((Integer) animation.getAnimatedValue());
                invalidate();
            }
        });

        successedAnimAlpha = ValueAnimator.ofInt(255, 100);
        successedAnimAlpha.setDuration(300);
        successedAnimAlpha.setInterpolator(new LinearInterpolator());
        successedAnimAlpha.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                paint.setAlpha((Integer) animation.getAnimatedValue());
                invalidate();
            }
        });

        successedAnimpath = ValueAnimator.ofFloat(0, 1);
        successedAnimpath.setDuration(500);
        successedAnimpath.setStartDelay(300);
        successedAnimpath.setInterpolator(new LinearInterpolator());
        successedAnimpath.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                pathprogress = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        successedAnim.start();
        successedAnimAlpha.start();
        successedAnimpath.start();
    }

    public void startAnimatior(){
        if (isRun){
            return;
        }
        state = State.LOADING;
        isRun = true;
        frountWaveAnim = ValueAnimator.ofFloat(0, width);
        frountWaveAnim.setDuration(waveSpeed * 10);
        frountWaveAnim.setInterpolator(new LinearInterpolator());
        frountWaveAnim.setRepeatCount(Animation.INFINITE);
        frountWaveAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float change = (float) animation.getAnimatedValue();
                xoffset = (int) change;
                invalidate();
            }
        });
        frountWaveAnim.start();

        backWaveAnim = ValueAnimator.ofFloat(0, width);
        backWaveAnim.setDuration(waveSpeed * 20);
        backWaveAnim.setInterpolator(new LinearInterpolator());
        backWaveAnim.setRepeatCount(Animation.INFINITE);
        backWaveAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float change = (float) animation.getAnimatedValue();
                xoffset2 = width - (int) change;
            }
        });
        backWaveAnim.start();
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        //设置一个默认值，就是这个View的默认宽度为500，这个看我们自定义View的要求
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {  //相当于我们设置为wrap_content
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {   //相当于我们设置为match_parent或者为一个具体的值
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
        return result;
    }

    public void OnSuccuess(){
        frountWaveAnim.cancel();
        backWaveAnim.cancel();
        frountWaveAnim.end();
        backWaveAnim.end();
        state = State.SUCCESSED;
        successedAnim();
    }

    /**
     * 0 - 100 取值
     * @param progress
     */
    public void setProgress(int progress){
        viewY = height - (height + offset * 2) / 100 * progress;
    }

    /**
     * 设置波浪速度
     * @param waveSpeed
     */
    public LoadingImageView setWaveSpeed(int waveSpeed){
        this.waveSpeed = waveSpeed;
        return this;
    }

    /**
     * 设置波浪高低
     * @param offset
     */
    public LoadingImageView setOffset(int offset){
        this.offset = offset;
        return this;
    }

    public LoadingImageView setWaveColor(int waveColor){
        this.waveColor = waveColor;
        return this;
    }

    /**
     * 动画是否在进行
     * @return
     */
    public boolean isRunning(){
        return isRun;
    }

}
