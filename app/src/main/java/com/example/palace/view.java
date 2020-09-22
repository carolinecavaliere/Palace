package com.example.palace;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * This class draws the players' cards onto the screen in the proper format for Palace.
 */
public class view extends SurfaceView {

    Paint cardPaint = new Paint();
    Paint cardBackPaint = new Paint();
    Paint textPaint = new Paint();
    Paint cardOutlinePaint = new Paint();
    float displayConvert = getResources().getDisplayMetrics().density;
    float cardWidth = displayConvert * 75;
    float cardHeight = displayConvert * 100;
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
        textPaint.setTextSize(displayConvert *20f);
        textPaint.setTypeface(Typeface.SANS_SERIF);
        cardOutlinePaint.setColor(Color.BLACK);
        cardOutlinePaint.setStyle(Paint.Style.STROKE);
        cardOutlinePaint.setStrokeWidth(10);
    }

    @Override
    public void onDraw(Canvas canvas){
        setBackgroundColor(0xFF31B94D);//"pool table" green
        super.onDraw(canvas);

        float xCenter = (canvas.getWidth()/2f);
        float yCenter = (canvas.getHeight()/2f);
        float xRight = canvas.getHeight();
        float yBottom = canvas.getHeight();
        float xMargin = displayConvert*100;
        float yMargin = displayConvert*160;

        //draw deck and pile
        drawCard(canvas, xCenter + 120 - cardWidth/2, yCenter, -50);
        drawCard(canvas, xCenter - 120 - cardWidth/2, yCenter, 10);


        //draw opponent's cards
        drawCard(canvas, xCenter - cardWidth/2, yMargin, 10);
        drawCard(canvas, xCenter + cardWidth *2 - cardWidth/2, yMargin, 10);
        drawCard(canvas, xCenter - cardWidth *2 - cardWidth/2, yMargin, 10);
        drawCard(canvas, xCenter + cardWidth * 4 - cardWidth/2, yMargin, -3);

        //draw player's cards
        drawCard(canvas, xCenter - cardWidth/2, yBottom - yMargin, 10);
        drawCard(canvas, xCenter + cardWidth *2 - cardWidth/2, yBottom - yMargin, 10);
        drawCard(canvas, xCenter - cardWidth *2 - cardWidth/2, yBottom - yMargin, 10);
    }

    public void drawCard(Canvas canvas, float left, float top, int rank) {
        canvas.drawRect(left,top, (left + displayConvert*75), (top + displayConvert*100), cardOutlinePaint);
        if(rank <= -1){//if the rank is -1 the card is flipped over
            canvas.drawRect(left, top, cardWidth + left, cardHeight + top, cardBackPaint);
            textPaint.setTextSize(displayConvert*40f);
            canvas.drawText(""+ (-1)*rank, left + cardWidth/3,top + cardHeight/2, textPaint);
            textPaint.setTextSize(displayConvert *20f);
        }
        else {
            canvas.drawRect(left, top, cardWidth + left, cardHeight + top, cardPaint);
            if(rank>1&&rank<11){//standard cards
                canvas.drawText(""+ rank,displayConvert*10 + left,displayConvert*25 + top,textPaint);
            }
            else if(rank==11){//jack
                canvas.drawText("J",displayConvert*(left+10),displayConvert*(top+25),textPaint);
            }
            else if(rank==12){//queen
                canvas.drawText("Q",displayConvert*(left+10),displayConvert*(top+25),textPaint);
            }
            else if(rank==13){//king
                canvas.drawText("K",displayConvert*(left+10),displayConvert*(top+25),textPaint);
            }
            else if(rank==14){//ace
                canvas.drawText("A",displayConvert*(left+10),displayConvert*(top+25),textPaint);
            }
        }


    }

}
