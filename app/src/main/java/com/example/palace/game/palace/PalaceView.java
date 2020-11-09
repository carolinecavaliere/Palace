package com.example.palace.game.palace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.SurfaceView;

import com.example.palace.game.R;

//nonsense
public class PalaceView extends SurfaceView {
    private int displayConvert = (int)getResources().getDisplayMetrics().density;
    private int cardWidth = displayConvert * 110;
    private int cardHeight = displayConvert * 140;

    private float xCenter;
    private float yCenter;
    private float xRight;
    private float yBottom;
    private float xMargin;
    private float yMargin;

    //draw player's cards
    private float playerTopCardLeftX = xCenter - cardWidth / 2;
    private float playerTopCardLeftY = yBottom - (yMargin) - displayConvert * 130 - cardHeight;
    private float playerTopCardRightX = xCenter + cardWidth * 2 - cardWidth / 2;
    private float playerTopCardRightY = yBottom - (yMargin) - displayConvert * 130 - cardHeight;
    private float playerCenterTopCardX = xCenter - cardWidth * 2 - cardWidth / 2;
    private float playerCenterTopCardY = yBottom - (yMargin) - displayConvert * 130 - cardHeight;

    //draw player's hand
    private float handTopCardLeftX = xCenter - cardWidth / 2;
    private float handTopCardLeftY = yBottom - yBottom - yMargin - displayConvert * 130;
    private float handTopCardRightX = xCenter + cardWidth * 2 - cardWidth / 2;
    private float handTopCardRightY = yBottom - yMargin - displayConvert * 130;
    private float handCenterTopCardX = xCenter - cardWidth * 2 - cardWidth / 2;

    public int getDisplayConvert() {
        return displayConvert;
    }

    public void setDisplayConvert(int displayConvert) {
        this.displayConvert = displayConvert;
    }

    public float getxCenter() {
        return xCenter;
    }

    public void setxCenter(float xCenter) {
        this.xCenter = xCenter;
    }

    public float getyCenter() {
        return yCenter;
    }

    public void setyCenter(float yCenter) {
        this.yCenter = yCenter;
    }

    public float getxRight() {
        return xRight;
    }

    public void setxRight(float xRight) {
        this.xRight = xRight;
    }

    public float getyBottom() {
        return yBottom;
    }

    public void setyBottom(float yBottom) {
        this.yBottom = yBottom;
    }

    public float getxMargin() {
        return xMargin;
    }

    public void setxMargin(float xMargin) {
        this.xMargin = xMargin;
    }

    public float getyMargin() {
        return yMargin;
    }

    public void setyMargin(float yMargin) {
        this.yMargin = yMargin;
    }

    public float getPlayerTopCardLeftX() {
        return playerTopCardLeftX;
    }

    public void setPlayerTopCardLeftX(float playerTopCardLeftX) {
        this.playerTopCardLeftX = playerTopCardLeftX;
    }

    public float getPlayerTopCardLeftY() {
        return playerTopCardLeftY;
    }

    public void setPlayerTopCardLeftY(float playerTopCardLeftY) {
        this.playerTopCardLeftY = playerTopCardLeftY;
    }

    public float getPlayerTopCardRightX() {
        return playerTopCardRightX;
    }

    public void setPlayerTopCardRightX(float playerTopCardRightX) {
        this.playerTopCardRightX = playerTopCardRightX;
    }

    public float getPlayerTopCardRightY() {
        return playerTopCardRightY;
    }

    public void setPlayerTopCardRightY(float playerTopCardRightY) {
        this.playerTopCardRightY = playerTopCardRightY;
    }

    public float getPlayerCenterTopCardX() {
        return playerCenterTopCardX;
    }

    public void setPlayerCenterTopCardX(float playerCenterTopCardX) {
        this.playerCenterTopCardX = playerCenterTopCardX;
    }

    public float getPlayerCenterTopCardY() {
        return playerCenterTopCardY;
    }

    public void setPlayerCenterTopCardY(float playerCenterTopCardY) {
        this.playerCenterTopCardY = playerCenterTopCardY;
    }

    public float getHandTopCardLeftX() {
        return handTopCardLeftX;
    }

    public void setHandTopCardLeftX(float handTopCardLeftX) {
        this.handTopCardLeftX = handTopCardLeftX;
    }

    public float getHandTopCardLeftY() {
        return handTopCardLeftY;
    }

    public void setHandTopCardLeftY(float handTopCardLeftY) {
        this.handTopCardLeftY = handTopCardLeftY;
    }

    public float getHandTopCardRightX() {
        return handTopCardRightX;
    }

    public void setHandTopCardRightX(float handTopCardRightX) {
        this.handTopCardRightX = handTopCardRightX;
    }

    public float getHandTopCardRightY() {
        return handTopCardRightY;
    }

    public void setHandTopCardRightY(float handTopCardRightY) {
        this.handTopCardRightY = handTopCardRightY;
    }

    public float getHandCenterTopCardX() {
        return handCenterTopCardX;
    }

    public void setHandCenterTopCardX(float handCenterTopCardX) {
        this.handCenterTopCardX = handCenterTopCardX;
    }

    public float getHandCenterTopCardY() {
        return handCenterTopCardY;
    }

    public void setHandCenterTopCardY(float handCenterTopCardY) {
        this.handCenterTopCardY = handCenterTopCardY;
    }

    private float handCenterTopCardY = yBottom - yMargin - displayConvert * 130;

    public PalaceGameState getState() {
        return state;
    }

    public void setState(PalaceGameState state) {
        this.state = state;
    }

    private PalaceGameState state;

    private Bitmap cardback = null;

    private Bitmap ace_clubs = null;
    private Bitmap ace_diamonds = null;
    private Bitmap ace_hearts = null;
    private Bitmap ace_spades = null;

    private Bitmap king_clubs = null;
    private Bitmap king_diamonds = null;
    private Bitmap king_hearts = null;
    private Bitmap king_spades = null;

    private Bitmap queen_clubs = null;
    private Bitmap queen_diamonds = null;
    private Bitmap queen_hearts = null;
    private Bitmap queen_spades = null;

    private Bitmap jack_clubs = null;
    private Bitmap jack_diamonds = null;
    private Bitmap jack_hearts = null;
    private Bitmap jack_spades = null;

    private Bitmap ten_clubs = null;
    private Bitmap ten_diamonds = null;
    private Bitmap ten_hearts = null;
    private Bitmap ten_spades = null;

    private Bitmap nine_clubs = null;
    private Bitmap nine_diamonds = null;
    private Bitmap nine_hearts = null;
    private Bitmap nine_spades = null;

    private Bitmap eight_clubs = null;
    private Bitmap eight_diamonds = null;
    private Bitmap eight_hearts = null;
    private Bitmap eight_spades = null;

    private Bitmap seven_clubs = null;
    private Bitmap seven_diamonds = null;
    private Bitmap seven_hearts = null;
    private Bitmap seven_spades = null;

    private Bitmap six_clubs = null;
    private Bitmap six_diamonds = null;
    private Bitmap six_hearts = null;
    private Bitmap six_spades = null;

    private Bitmap five_clubs = null;
    private Bitmap five_diamonds = null;
    private Bitmap five_hearts = null;
    private Bitmap five_spades = null;

    private Bitmap four_clubs = null;
    private Bitmap four_diamonds = null;
    private Bitmap four_hearts = null;
    private Bitmap four_spades = null;

    private Bitmap three_clubs = null;
    private Bitmap three_diamonds = null;
    private Bitmap three_hearts = null;
    private Bitmap three_spades = null;

    private Bitmap two_clubs = null;
    private Bitmap two_diamonds = null;
    private Bitmap two_hearts = null;
    private Bitmap two_spades = null;


    /**
     External Citation
     Date: 19 September 2020
     Problem: had to make pixels universal across devices
     Resource:
     https://stackoverflow.com/questions/6391823/drawing-drawables-to-a-canvas-in-dp-units
     Solution: I used the example code from this post.
     */

    public PalaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        ace_clubs = BitmapFactory.decodeResource(getResources(), R.drawable.a_c);
        ace_clubs = Bitmap.createScaledBitmap(ace_clubs, cardWidth, cardHeight, true);
        ace_diamonds = BitmapFactory.decodeResource(getResources(), R.drawable.a_d);
        ace_diamonds = Bitmap.createScaledBitmap(ace_diamonds, cardWidth, cardHeight, true);
        ace_hearts = BitmapFactory.decodeResource(getResources(), R.drawable.a_h);
        ace_hearts = Bitmap.createScaledBitmap(ace_hearts, cardWidth, cardHeight, true);
        ace_spades = BitmapFactory.decodeResource(getResources(), R.drawable.a_s);
        ace_spades = Bitmap.createScaledBitmap(ace_spades, cardWidth, cardHeight, true);

        king_clubs = BitmapFactory.decodeResource(getResources(), R.drawable.kc);
        king_clubs = Bitmap.createScaledBitmap(king_clubs, cardWidth, cardHeight, true);
        king_diamonds = BitmapFactory.decodeResource(getResources(), R.drawable.kd);
        king_diamonds = Bitmap.createScaledBitmap(king_diamonds, cardWidth, cardHeight, true);
        king_hearts = BitmapFactory.decodeResource(getResources(), R.drawable.kh);
        king_hearts = Bitmap.createScaledBitmap(king_hearts, cardWidth, cardHeight, true);
        king_spades = BitmapFactory.decodeResource(getResources(), R.drawable.ks);
        king_spades = Bitmap.createScaledBitmap(king_spades, cardWidth, cardHeight, true);

        queen_clubs = BitmapFactory.decodeResource(getResources(), R.drawable.qc);
        queen_clubs = Bitmap.createScaledBitmap(queen_clubs, cardWidth, cardHeight, true);
        queen_diamonds = BitmapFactory.decodeResource(getResources(), R.drawable.qd);
        queen_diamonds = Bitmap.createScaledBitmap(queen_diamonds, cardWidth, cardHeight, true);
        queen_hearts = BitmapFactory.decodeResource(getResources(), R.drawable.qh);
        queen_hearts = Bitmap.createScaledBitmap(queen_hearts, cardWidth, cardHeight, true);
        queen_spades = BitmapFactory.decodeResource(getResources(), R.drawable.qs);
        queen_spades = Bitmap.createScaledBitmap(queen_spades, cardWidth, cardHeight, true);

        jack_clubs = BitmapFactory.decodeResource(getResources(), R.drawable.jc);
        jack_clubs = Bitmap.createScaledBitmap(jack_clubs, cardWidth, cardHeight, true);
        jack_diamonds = BitmapFactory.decodeResource(getResources(), R.drawable.jd);
        jack_diamonds = Bitmap.createScaledBitmap(jack_diamonds, cardWidth, cardHeight, true);
        jack_hearts = BitmapFactory.decodeResource(getResources(), R.drawable.jh);
        jack_hearts = Bitmap.createScaledBitmap(jack_hearts, cardWidth, cardHeight, true);
        jack_spades = BitmapFactory.decodeResource(getResources(), R.drawable.js);
        jack_spades = Bitmap.createScaledBitmap(jack_spades, cardWidth, cardHeight, true);

        ten_clubs = BitmapFactory.decodeResource(getResources(), R.drawable.ten_c);
        ten_clubs = Bitmap.createScaledBitmap(ten_clubs, cardWidth, cardHeight, true);
        ten_diamonds = BitmapFactory.decodeResource(getResources(), R.drawable.ten_d);
        ten_diamonds = Bitmap.createScaledBitmap(ten_diamonds, cardWidth, cardHeight, true);
        ten_hearts = BitmapFactory.decodeResource(getResources(), R.drawable.ten_h);
        ten_hearts = Bitmap.createScaledBitmap(ten_hearts, cardWidth, cardHeight, true);
        ten_spades = BitmapFactory.decodeResource(getResources(), R.drawable.ten_s);
        ten_spades = Bitmap.createScaledBitmap(ten_spades, cardWidth, cardHeight, true);

        nine_clubs = BitmapFactory.decodeResource(getResources(), R.drawable.nine_c);
        nine_clubs = Bitmap.createScaledBitmap(nine_clubs, cardWidth, cardHeight, true);
        nine_diamonds = BitmapFactory.decodeResource(getResources(), R.drawable.nine_d);
        nine_diamonds = Bitmap.createScaledBitmap(nine_diamonds, cardWidth, cardHeight, true);
        nine_hearts = BitmapFactory.decodeResource(getResources(), R.drawable.nine_h);
        nine_hearts = Bitmap.createScaledBitmap(nine_hearts, cardWidth, cardHeight, true);
        nine_spades = BitmapFactory.decodeResource(getResources(), R.drawable.nine_s);
        nine_spades = Bitmap.createScaledBitmap(nine_spades, cardWidth, cardHeight, true);

        eight_clubs = BitmapFactory.decodeResource(getResources(), R.drawable.eight_h);
        eight_clubs = Bitmap.createScaledBitmap(eight_clubs, cardWidth, cardHeight, true);
        eight_diamonds = BitmapFactory.decodeResource(getResources(), R.drawable.eight_d);
        eight_diamonds = Bitmap.createScaledBitmap(eight_diamonds, cardWidth, cardHeight, true);
        eight_hearts = BitmapFactory.decodeResource(getResources(), R.drawable.eight_h);
        eight_hearts = Bitmap.createScaledBitmap(eight_hearts, cardWidth, cardHeight, true);
        eight_spades = BitmapFactory.decodeResource(getResources(), R.drawable.eight_s);
        eight_spades = Bitmap.createScaledBitmap(eight_spades, cardWidth, cardHeight, true);

        seven_clubs = BitmapFactory.decodeResource(getResources(), R.drawable.seven_c);
        seven_clubs = Bitmap.createScaledBitmap(seven_clubs, cardWidth, cardHeight, true);
        seven_diamonds = BitmapFactory.decodeResource(getResources(), R.drawable.seven_d);
        seven_diamonds = Bitmap.createScaledBitmap(seven_diamonds, cardWidth, cardHeight, true);
        seven_hearts = BitmapFactory.decodeResource(getResources(), R.drawable.seven_h);
        seven_hearts = Bitmap.createScaledBitmap(seven_hearts, cardWidth, cardHeight, true);
        seven_spades = BitmapFactory.decodeResource(getResources(), R.drawable.seven_s);
        seven_spades = Bitmap.createScaledBitmap(seven_spades, cardWidth, cardHeight, true);

        six_clubs = BitmapFactory.decodeResource(getResources(), R.drawable.six_c);
        six_clubs = Bitmap.createScaledBitmap(six_clubs, cardWidth, cardHeight, true);
        six_diamonds = BitmapFactory.decodeResource(getResources(), R.drawable.six_d);
        six_diamonds = Bitmap.createScaledBitmap(six_diamonds, cardWidth, cardHeight, true);
        six_hearts = BitmapFactory.decodeResource(getResources(), R.drawable.six_h);
        six_hearts = Bitmap.createScaledBitmap(six_hearts, cardWidth, cardHeight, true);
        six_spades = BitmapFactory.decodeResource(getResources(), R.drawable.six_s);
        six_spades = Bitmap.createScaledBitmap(six_spades, cardWidth, cardHeight, true);

        five_clubs = BitmapFactory.decodeResource(getResources(), R.drawable.five_c);
        five_clubs = Bitmap.createScaledBitmap(five_clubs, cardWidth, cardHeight, true);
        five_diamonds = BitmapFactory.decodeResource(getResources(), R.drawable.five_d);
        five_diamonds = Bitmap.createScaledBitmap(five_diamonds, cardWidth, cardHeight, true);
        five_hearts = BitmapFactory.decodeResource(getResources(), R.drawable.five_h);
        five_hearts = Bitmap.createScaledBitmap(five_hearts, cardWidth, cardHeight, true);
        five_spades = BitmapFactory.decodeResource(getResources(), R.drawable.five_s);
        five_spades = Bitmap.createScaledBitmap(five_spades, cardWidth, cardHeight, true);

        four_clubs = BitmapFactory.decodeResource(getResources(), R.drawable.four_c);
        four_clubs = Bitmap.createScaledBitmap(four_clubs, cardWidth, cardHeight, true);
        four_diamonds = BitmapFactory.decodeResource(getResources(), R.drawable.four_d);
        four_diamonds = Bitmap.createScaledBitmap(four_diamonds, cardWidth, cardHeight, true);
        four_hearts = BitmapFactory.decodeResource(getResources(), R.drawable.four_h);
        four_hearts = Bitmap.createScaledBitmap(four_hearts, cardWidth, cardHeight, true);
        four_spades = BitmapFactory.decodeResource(getResources(), R.drawable.four_s);
        four_spades = Bitmap.createScaledBitmap(four_spades, cardWidth, cardHeight, true);

        three_clubs = BitmapFactory.decodeResource(getResources(), R.drawable.three_c);
        three_clubs = Bitmap.createScaledBitmap(three_clubs, cardWidth, cardHeight, true);
        three_diamonds = BitmapFactory.decodeResource(getResources(), R.drawable.three_d);
        three_diamonds = Bitmap.createScaledBitmap(three_diamonds, cardWidth, cardHeight, true);
        three_hearts = BitmapFactory.decodeResource(getResources(), R.drawable.three_h);
        three_hearts = Bitmap.createScaledBitmap(three_hearts, cardWidth, cardHeight, true);
        three_spades = BitmapFactory.decodeResource(getResources(), R.drawable.three_s);
        three_spades = Bitmap.createScaledBitmap(three_spades, cardWidth, cardHeight, true);

        two_clubs = BitmapFactory.decodeResource(getResources(), R.drawable.two_c);
        two_clubs = Bitmap.createScaledBitmap(two_clubs, cardWidth, cardHeight, true);
        two_diamonds = BitmapFactory.decodeResource(getResources(), R.drawable.two_d);
        two_diamonds= Bitmap.createScaledBitmap(two_diamonds, cardWidth, cardHeight, true);
        two_hearts = BitmapFactory.decodeResource(getResources(), R.drawable.two_h);
        two_hearts = Bitmap.createScaledBitmap(two_hearts, cardWidth, cardHeight, true);
        two_spades = BitmapFactory.decodeResource(getResources(), R.drawable.two_s);
        two_spades = Bitmap.createScaledBitmap(two_spades, cardWidth, cardHeight, true);

        cardback = BitmapFactory.decodeResource(getResources(), R.drawable.cardback);
        cardback = Bitmap.createScaledBitmap(cardback, cardWidth, cardHeight, true);

    }

    /**
     * Draws on a given canvas. In this case, surfaceView
     *
     * @Jimi Hayes
     *
     * @param canvas
     */
    @Override
    public void onDraw(Canvas canvas){
        setBackgroundColor(0xFF31B94D);
        super.onDraw(canvas);

        xCenter = (canvas.getWidth()/2f);
        yCenter = (canvas.getHeight()/2f);
        xRight = canvas.getHeight();
        yBottom = canvas.getHeight();
        xMargin = displayConvert*100;
        yMargin = displayConvert*200;
        if(state!=null) {
        //draw deck and pile
        drawCard(canvas, xCenter + 120 - cardWidth/2, yCenter - cardHeight/2, 1,  -1);
        if(!(state.getPlayPileTopPalaceCard()==null)){
            drawCard(canvas, xCenter - 120 - cardWidth/2, yCenter - cardHeight/2, state.getPlayPileTopPalaceCard().getSuit(), state.getPlayPileTopPalaceCard().getRank());
        }

            //draw opponent's cards
            drawCard(canvas, xCenter - cardWidth / 2, yMargin, state.getP2TopPalaceCards().get(0).getSuit(), state.getP2TopPalaceCards().get(0).getRank());
            drawCard(canvas, xCenter + cardWidth * 2 - cardWidth / 2, yMargin, state.getP2TopPalaceCards().get(1).getSuit(), state.getP2TopPalaceCards().get(1).getRank());
            drawCard(canvas, xCenter - cardWidth * 2 - cardWidth / 2, yMargin, state.getP2TopPalaceCards().get(2).getSuit(), state.getP2TopPalaceCards().get(2).getRank());
            drawCard(canvas, xCenter - cardWidth / 2, yMargin - displayConvert * 150, 1, -1);

            //draw player's cards
             playerTopCardLeftX = xCenter - cardWidth / 2;
             playerTopCardLeftY = yBottom - (yMargin) - displayConvert * 130 - cardHeight;
             playerTopCardRightX = xCenter + cardWidth * 2 - cardWidth / 2;
             playerTopCardRightY = yBottom - (yMargin) - displayConvert * 130 - cardHeight;
             playerCenterTopCardX = xCenter - cardWidth * 2 - cardWidth / 2;
             playerCenterTopCardY = yBottom - (yMargin) - displayConvert * 130 - cardHeight;

            drawCard(canvas, playerTopCardLeftX, playerTopCardLeftY, state.getP1TopPalaceCards().get(0).getSuit(), state.getP1TopPalaceCards().get(0).getRank());
            drawCard(canvas, playerTopCardRightX , playerTopCardRightY , state.getP1TopPalaceCards().get(1).getSuit(), state.getP1TopPalaceCards().get(1).getRank());
            drawCard(canvas, playerCenterTopCardX, playerCenterTopCardY , state.getP1TopPalaceCards().get(2).getSuit(), state.getP1TopPalaceCards().get(2).getRank());

            //draw player's hand
             handTopCardLeftX = xCenter - cardWidth / 2;
             handTopCardLeftY = yBottom - yBottom - yMargin - displayConvert * 130;
             handTopCardRightX = xCenter + cardWidth * 2 - cardWidth / 2;
             handTopCardRightY = yBottom - yMargin - displayConvert * 130;
             handCenterTopCardX = xCenter - cardWidth * 2 - cardWidth / 2;
             handCenterTopCardY = yBottom - yMargin - displayConvert * 130;

            drawCard(canvas, handTopCardLeftX, handTopCardLeftY, state.getP1Hand().get(0).getSuit(), state.getP1Hand().get(0).getRank());
            drawCard(canvas, handTopCardRightX, handTopCardRightY, state.getP1Hand().get(1).getSuit(), state.getP1Hand().get(1).getRank());
            drawCard(canvas, handCenterTopCardX, handCenterTopCardY , state.getP1Hand().get(2).getSuit(), state.getP1Hand().get(2).getRank());

            //drawCard(canvas, xCenter - cardWidth/2, yBottom - yMargin + displayConvert*50, 10);
            //drawCard(canvas, xCenter + cardWidth *2 - cardWidth/2, yBottom - yMargin + displayConvert*50, 2);
            //drawCard(canvas, xCenter - cardWidth *2 - cardWidth/2, yBottom - yMargin + displayConvert*50, 8);
        }

    }

    /**
     * Draws a card with a value. If the card value is between 1 and 14, it will display the values
     * Ace through King accordingly. Any negative number will draw a facedown card with that positive
     * of that number. 0 will draw a blank face down card.
     *
     * @Jimi Hayes
     *
     * @param canvas
     * @param left
     * @param top
     * @param rank
     * @param suit
     */
    public void drawCard(Canvas canvas, float left, float top, int suit, int rank) {
        //canvas.drawRect(left, top, (left + displayConvert * 75), (top + displayConvert * 100), cardOutlinePaint);
        if (rank == -1) {
            canvas.drawBitmap(cardback, left, top, null);
        }
        else if(suit==3) {
            if (rank == 2) {
                canvas.drawBitmap(two_clubs, left, top, null);
            } else if (rank == 3) {
                canvas.drawBitmap(three_clubs, left, top, null);
            } else if (rank == 4) {
                canvas.drawBitmap(four_clubs, left, top, null);
            } else if (rank == 5) {
                canvas.drawBitmap(five_clubs, left, top, null);
            } else if (rank == 6) {
                canvas.drawBitmap(six_clubs, left, top, null);
            } else if (rank == 7) {
                canvas.drawBitmap(seven_clubs, left, top, null);
            } else if (rank == 8) {
                canvas.drawBitmap(eight_clubs, left, top, null);
            } else if (rank == 9) {
                canvas.drawBitmap(nine_clubs, left, top, null);
            } else if (rank == 10) {
                canvas.drawBitmap(ten_clubs, left, top, null);
            } else if (rank == 11) {
                canvas.drawBitmap(jack_clubs, left, top, null);
            } else if (rank == 12) {
                canvas.drawBitmap(queen_clubs, left, top, null);
            } else if (rank == 13) {
                canvas.drawBitmap(king_clubs, left, top, null);
            } else if (rank == 14) {
                canvas.drawBitmap(ace_clubs, left, top, null);
            }
        }
        else if(suit==1) {
            if (rank == 2) {
                canvas.drawBitmap(two_diamonds, left, top, null);
            } else if (rank == 3) {
                canvas.drawBitmap(three_diamonds, left, top, null);
            } else if (rank == 4) {
                canvas.drawBitmap(four_diamonds, left, top, null);
            } else if (rank == 5) {
                canvas.drawBitmap(five_diamonds, left, top, null);
            } else if (rank == 6) {
                canvas.drawBitmap(six_diamonds, left, top, null);
            } else if (rank == 7) {
                canvas.drawBitmap(seven_diamonds, left, top, null);
            } else if (rank == 8) {
                canvas.drawBitmap(eight_diamonds, left, top, null);
            } else if (rank == 9) {
                canvas.drawBitmap(nine_diamonds, left, top, null);
            } else if (rank == 10) {
                canvas.drawBitmap(ten_diamonds, left, top, null);
            } else if (rank == 11) {
                canvas.drawBitmap(jack_diamonds, left, top, null);
            } else if (rank == 12) {
                canvas.drawBitmap(queen_diamonds, left, top, null);
            } else if (rank == 13) {
                canvas.drawBitmap(king_diamonds, left, top, null);
            } else if (rank == 14) {
                canvas.drawBitmap(ace_diamonds, left, top, null);
            }
        }
        else if(suit==2){
            if (rank == 2) {
                canvas.drawBitmap(two_hearts, left, top, null);
            } else if (rank == 3) {
                canvas.drawBitmap(three_hearts, left, top, null);
            } else if (rank == 4) {
                canvas.drawBitmap(four_hearts, left, top, null);
            } else if (rank == 5) {
                canvas.drawBitmap(five_hearts, left, top, null);
            } else if (rank == 6) {
                canvas.drawBitmap(six_hearts, left, top, null);
            } else if (rank == 7) {
                canvas.drawBitmap(seven_hearts, left, top, null);
            } else if (rank == 8) {
                canvas.drawBitmap(eight_hearts, left, top, null);
            } else if (rank == 9) {
                canvas.drawBitmap(nine_hearts, left, top, null);
            } else if (rank == 10) {
                canvas.drawBitmap(ten_hearts, left, top, null);
            } else if (rank == 11) {
                canvas.drawBitmap(jack_hearts, left, top, null);
            } else if (rank == 12) {
                canvas.drawBitmap(queen_hearts, left, top, null);
            } else if (rank == 13) {
                canvas.drawBitmap(king_hearts, left, top, null);
            } else if (rank == 14) {
                canvas.drawBitmap(ace_hearts, left, top, null);
            }
        }
        else if(suit==4){
            if (rank == 2) {
                canvas.drawBitmap(two_spades, left, top, null);
            } else if (rank == 3) {
                canvas.drawBitmap(three_spades, left, top, null);
            } else if (rank == 4) {
                canvas.drawBitmap(four_spades, left, top, null);
            } else if (rank == 5) {
                canvas.drawBitmap(five_spades, left, top, null);
            } else if (rank == 6) {
                canvas.drawBitmap(six_spades, left, top, null);
            } else if (rank == 7) {
                canvas.drawBitmap(seven_spades, left, top, null);
            } else if (rank == 8) {
                canvas.drawBitmap(eight_spades, left, top, null);
            } else if (rank == 9) {
                canvas.drawBitmap(nine_spades, left, top, null);
            } else if (rank == 10) {
                canvas.drawBitmap(ten_spades, left, top, null);
            } else if (rank == 11) {
                canvas.drawBitmap(jack_spades, left, top, null);
            } else if (rank == 12) {
                canvas.drawBitmap(queen_spades, left, top, null);
            } else if (rank == 13) {
                canvas.drawBitmap(king_spades, left, top, null);
            } else if (rank == 14) {
                canvas.drawBitmap(ace_spades, left, top, null);
            }
        }
        }

    public int getCardWidth() {
        return cardWidth;
    }

    public void setCardWidth(int cardWidth) {
        this.cardWidth = cardWidth;
    }

    public int getCardHeight() {
        return cardHeight;
    }

    public void setCardHeight(int cardHeight) {
        this.cardHeight = cardHeight;
    }
}
