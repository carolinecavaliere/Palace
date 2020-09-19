package com.example.palace;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class view extends SurfaceView {

    Paint cardPaint = new Paint();
    Paint cardBackPaint = new Paint();
    Paint textPaint = new Paint();
    Paint cardOutlinePaint = new Paint();
    float displayConvert = getResources().getDisplayMetrics().density;
    /**
     External Citation
     Date: 19 September 2020
     Problem: had to make pixels universal across devices
     Resource:
     https://stackoverflow.com/questions/6391823/drawing-drawables-to-a-canvas-in-dp-units
     Solution: I used the example code from this post.
     */

    public view(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        cardPaint.setStyle(Paint.Style.FILL);
        cardPaint.setColor(Color.WHITE);
        cardBackPaint.setStyle(Paint.Style.FILL);
        cardBackPaint.setColor(Color.RED);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);
        textPaint.setTypeface(Typeface.SANS_SERIF);
        cardOutlinePaint.setColor(Color.BLACK);
        cardOutlinePaint.setStyle(Paint.Style.STROKE);
        cardOutlinePaint.setStrokeWidth(10);
    }

    @Override
    public void onDraw(Canvas canvas){
        setBackgroundColor(0xFF31B94D);
        drawCard(canvas, 600, 350, 10);
        drawCard(canvas, 400, 350, 14);
        drawCard(canvas, 200, 350, 11);

    }

    public void drawCard(Canvas canvas, float left, float top, int rank) {
        canvas.drawRect(displayConvert*left,displayConvert*top, displayConvert*(left+75), displayConvert*(top+100), cardOutlinePaint);
        if(rank==-1){
            canvas.drawRect(displayConvert * left, displayConvert * top, displayConvert * (left + 75), displayConvert * (top + 100), cardBackPaint);
        }
        else {
            canvas.drawRect(displayConvert * left, displayConvert * top, displayConvert * (left + 75), displayConvert * (top + 100), cardPaint);
            if(rank>1&&rank<11){//standard cards
                canvas.drawText(""+rank,displayConvert*(left+10),displayConvert*(top+25),textPaint);
            }
            else if(rank==11){
                canvas.drawText("J",displayConvert*(left+10),displayConvert*(top+25),textPaint);
            }
            else if(rank==12){
                canvas.drawText("Q",displayConvert*(left+10),displayConvert*(top+25),textPaint);
            }
            else if(rank==13){
                canvas.drawText("K",displayConvert*(left+10),displayConvert*(top+25),textPaint);
            }
            else if(rank==14){
                canvas.drawText("A",displayConvert*(left+10),displayConvert*(top+25),textPaint);
            }
        }
        setBackgroundColor(0xFF31B94D);
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
