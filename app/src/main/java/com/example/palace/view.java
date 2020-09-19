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
        setBackgroundColor(0xFF31B94D);
        canvas.drawRect(displayConvert*500,displayConvert*350, displayConvert*550, displayConvert*450, cardPaint);
        /**
         External Citation
         Date: 19 September 2020
         Problem: had to make pixels universal across devices
         Resource:
         https://stackoverflow.com/questions/6391823/drawing-drawables-to-a-canvas-in-dp-units
         Solution: I used the example code from this post.
         */

    }

}
