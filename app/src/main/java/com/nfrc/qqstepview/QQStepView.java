package com.nfrc.qqstepview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangl on 2018/10/16.
 */

public class QQStepView extends View {


    private int mOuterColor = Color.RED;
    private int mInnerColor = Color.BLUE;
    private int mBorderWidth = 20;
    private int mStepTextColor = Color.BLUE;
    private int mStepTextSize = 20;

    private Paint mOutPian,mInnerPaint,mTextPaint;




    private int mStepMax = 0;       //总共
    private int mCurrentStep = 0;   //当前



    public QQStepView(Context context) {
        this(context,null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.QQStepView);
        mOuterColor = array.getColor(R.styleable.QQStepView_outerColor,mOuterColor);
        mInnerColor = array.getColor(R.styleable.QQStepView_innerColor,mInnerColor);
        mStepTextColor = array.getColor(R.styleable.QQStepView_stepTextColor,mStepTextColor);
        mBorderWidth = (int) array.getDimension(R.styleable.QQStepView_borderWidth,mBorderWidth);
        mStepTextSize = array.getDimensionPixelOffset(R.styleable.QQStepView_stepTextSize,mStepTextSize);
        array.recycle();

        mOutPian = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOutPian.setStrokeWidth(mBorderWidth);
        mOutPian.setColor(mOuterColor);
        mOutPian.setStyle(Paint.Style.STROKE); //画笔实心
        mOutPian.setStrokeCap(Paint.Cap.ROUND);
        mOutPian.setStrokeJoin(Paint.Join.ROUND);

        mInnerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerPaint.setStrokeWidth(mBorderWidth);
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStyle(Paint.Style.STROKE); //画笔实心
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);
        mInnerPaint.setStrokeJoin(Paint.Join.ROUND);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mInnerColor);
        mTextPaint.setTextSize(mStepTextSize);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(Math.min(width,height),Math.min(width,height));

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画最外面的圆环

        int center = getWidth()/2;
        int radius = getWidth()/2 - mBorderWidth/2;


        RectF rectF =  new RectF(center-radius,center-radius,center+radius,center+radius);


        canvas.drawArc(rectF,135,270,false,mOutPian);


        // 画内环

        if (mStepMax == 0){
            return;
        }
        float sweepAngle = (float) mCurrentStep/mStepMax;

        canvas.drawArc(rectF,135,270 * sweepAngle,false,mInnerPaint);



        //画文字
        String stepText = mCurrentStep+"";
        Rect textBounds = new Rect();

        mTextPaint.getTextBounds(stepText,0,stepText.length(),textBounds);
        int dx = getWidth()/2 - textBounds.width()/2;

        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top)/2 - fontMetricsInt.bottom;

        int baseLine = getWidth()/2 + dy;

        canvas.drawText(stepText,dx,baseLine,mTextPaint);

    }







    public synchronized void setmStepMax(int mStepMax) {
        this.mStepMax = mStepMax;
    }

    public synchronized void setmCurrentStep(int mCurrentStep) {
        this.mCurrentStep = mCurrentStep;

        invalidate();
    }




}
