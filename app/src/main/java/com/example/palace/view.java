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
        textPaint.setTextSize(displayConvert * 20f);
        textPaint.setTypeface(Typeface.SANS_SERIF);
        cardOutlinePaint.setColor(Color.BLACK);
        cardOutlinePaint.setStyle(Paint.Style.STROKE);
        cardOutlinePaint.setStrokeWidth(10);
    }

    @Override
    public void onDraw(Canvas canvas){
        setBackgroundColor(0xFF31B94D);
        super.onDraw(canvas);

        float x = (canvas.getWidth()/2f);
        float y = (canvas.getHeight()/2f);

        drawCard(canvas, x, y, 10);

    }

    public void drawCard(Canvas canvas, float left, float top, int rank) {
        canvas.drawRect(left,top, (left + displayConvert*75), (top + displayConvert*100), cardOutlinePaint);
        if(rank==-1){
            canvas.drawRect(left, top, (displayConvert*75) + left, (displayConvert * 100) + top, cardBackPaint);
        }
        else {
            canvas.drawRect(left, top, (displayConvert*75) + left, (displayConvert * 100) + top, cardPaint);
            if(rank>1&&rank<11){//standard cards
                canvas.drawText(""+rank,(displayConvert*10) + left,(displayConvert*25) + top,textPaint);
            }
            else if(rank==11){
                canvas.drawText("J",(displayConvert*10) + left,(displayConvert*25)+top,textPaint);
            }
            else if(rank==12){
                canvas.drawText("Q",(displayConvert*10) + left,(displayConvert*25)+top,textPaint);
            }
            else if(rank==13){
                canvas.drawText("K",(displayConvert*10) + left,(displayConvert*25)+top,textPaint);
            }
            else if(rank==14){
                canvas.drawText("A",(displayConvert*10) + left,(displayConvert*25)+top,textPaint);
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
