package com.example.palace;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class view extends SurfaceView {

    Paint cardPaint = new Paint();
    Paint cardBackPaint = new Paint();
    Paint textPaint = new Paint();
    Paint cardOutlinePaint = new Paint();
    float displayConvert = getResources().getDisplayMetrics().density;

    public view(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        cardPaint.setStyle(Paint.Style.FILL);
        cardPaint.setColor(Color.WHITE);
        cardBackPaint.setStyle(Paint.Style.FILL);
        cardBackPaint.setColor(Color.RED);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(20);
        cardOutlinePaint.setColor(Color.BLACK);
        cardOutlinePaint.setStyle(Paint.Style.STROKE);
        cardOutlinePaint.setStrokeWidth(10);

    }

    @Override
    public void onDraw(Canvas canvas){
        setBackgroundColor(Color.GREEN);
        canvas.drawRect(displayConvert*1000,displayConvert*500, displayConvert*1100, displayConvert*650, cardPaint);
    }

}
