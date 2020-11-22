package com.example.palace.game.palace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceView;

import com.example.palace.game.R;

/**
 * @author Caroline Cavaliere, Chloe Gan, Jimi Hayes, Nathaniel Pon
 * <p>
 * This class dictates how the GUI is displayed.
 *
 * CAVEATS: Rare instances of it crashing the game. Very rare, will be investigated
 */
public class PalaceView extends SurfaceView {
    // card dimensions
    private int displayConvert = (int) getResources().getDisplayMetrics().density;
    private int cardWidth = displayConvert * 110;
    private int cardHeight = displayConvert * 140;

    private float xCenter;
    private float yCenter;
    private float xRight;
    private float yBottom;
    private float xMargin;
    private float yMargin;

    //draw player's cards
    private float playerTopCardCenterX;
    private float playerTopCardCenterY;
    private float playerTopCardRightX;
    private float playerTopCardRightY;
    private float playerTopCardLeftX;
    private float playerTopCardLeftY;

    //draw player's hand
    private float playerHandCardCenterX;
    private float playerHandCardCenterY;
    private float playerHandCardRightX;
    private float playerHandCardRightY;
    private float playerHandCardLeftX;
    private float playerHandCardLeftY;

    private PalaceGameState state;

    //all types of cards as Bitmap objects
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

    private Paint highlightPaint = new Paint();
    private Rect highlightRect;

    /**
     External Citation
     Date: 19 September 2020
     Problem: had to make pixels universal across devices
     Resource:
     https://stackoverflow.com/questions/6391823/drawing-drawables-to-a-canvas-in-dp-units
     Solution: I used the example code from this post.
     */

    /**
     * Constructor for the view.
     * Create the bitmap images for each 52 cards in a deck of cards
     *
     * CAVEATS: None
     *
     * @param context
     * @param attrs
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
        two_diamonds = Bitmap.createScaledBitmap(two_diamonds, cardWidth, cardHeight, true);
        two_hearts = BitmapFactory.decodeResource(getResources(), R.drawable.two_h);
        two_hearts = Bitmap.createScaledBitmap(two_hearts, cardWidth, cardHeight, true);
        two_spades = BitmapFactory.decodeResource(getResources(), R.drawable.two_s);
        two_spades = Bitmap.createScaledBitmap(two_spades, cardWidth, cardHeight, true);

        cardback = BitmapFactory.decodeResource(getResources(), R.drawable.cardback);
        cardback = Bitmap.createScaledBitmap(cardback, cardWidth, cardHeight, true);

        highlightPaint.setColor(0xCC9FFF3);
        highlightPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * draws on a given canvas. In this case, surfaceView
     *
     * CAVEATS: None
     *
     * @param canvas
     * @Jimi Hayes
     */
    @Override
    public void onDraw(Canvas canvas) {
        setBackgroundColor(0xFF31B94D);
        super.onDraw(canvas);

        xCenter = (canvas.getWidth() / 2f);
        yCenter = (canvas.getHeight() / 2f);
        xRight = canvas.getHeight();
        yBottom = canvas.getHeight();
        xMargin = displayConvert * 100;
        yMargin = displayConvert * 200;
        if (state != null) {
            //draw deck and pile
            if (!(state.getDrawPileNumCards() == 0)) {
                drawCard(canvas, xCenter + 120 - cardWidth / 2,
                        yCenter - cardHeight / 2,
                        1,-1);
            }
            if (!(state.getPlayPilePalaceCards().isEmpty())) {
                drawCard(canvas, xCenter - 120 - cardWidth / 2, yCenter - cardHeight / 2,
                        state.getPlayPilePalaceCards().
                                get(state.getPlayPilePalaceCards().size() - 1).getSuit(),
                        state.getPlayPilePalaceCards().
                                get(state.getPlayPilePalaceCards().size() - 1).getRank());
            }

            //draw opponent's cards

            drawCard(canvas, xCenter - cardWidth / 2,
                    yMargin - displayConvert * 150,
                    1, -1);
            if (state.getP2TopPalaceCards().size() == 3) {
                drawCard(canvas, xCenter - cardWidth / 2, yMargin,
                        state.getP2TopPalaceCards().get(0).getSuit(),
                        state.getP2TopPalaceCards().get(0).getRank());
                drawCard(canvas, xCenter + cardWidth * 2 - cardWidth / 2, yMargin,
                        state.getP2TopPalaceCards().get(1).getSuit(),
                        state.getP2TopPalaceCards().get(1).getRank());
                drawCard(canvas, xCenter - cardWidth * 2 - cardWidth / 2, yMargin,
                        state.getP2TopPalaceCards().get(2).getSuit(),
                        state.getP2TopPalaceCards().get(2).getRank());
            } else if (state.getP2TopPalaceCards().size() == 2) {
                drawCard(canvas, xCenter - cardWidth / 2, yMargin,
                        state.getP2TopPalaceCards().get(0).getSuit(),
                        state.getP2TopPalaceCards().get(0).getRank());
                drawCard(canvas, xCenter + cardWidth * 2 - cardWidth / 2, yMargin,
                        state.getP2TopPalaceCards().get(1).getSuit(),
                        state.getP2TopPalaceCards().get(1).getRank());
            } else if (state.getP2TopPalaceCards().size() == 1) {
                drawCard(canvas, xCenter - cardWidth / 2, yMargin,
                        state.getP2TopPalaceCards().get(0).getSuit(),
                        state.getP2TopPalaceCards().get(0).getRank());
            }
            //draw player's cards
            playerTopCardCenterX = xCenter - cardWidth / 2;
            playerTopCardCenterY = yBottom - (yMargin) - displayConvert * 130 - cardHeight;
            playerTopCardRightX = xCenter + cardWidth * 2 - cardWidth / 2;
            playerTopCardRightY = yBottom - (yMargin) - displayConvert * 130 - cardHeight;
            playerTopCardLeftX = xCenter - cardWidth * 2 - cardWidth / 2;
            playerTopCardLeftY = yBottom - (yMargin) - displayConvert * 130 - cardHeight;

            if (state.getP1TopPalaceCards().size() == 3) {
                drawCard(canvas, playerTopCardLeftX, playerTopCardLeftY,
                        state.getP1TopPalaceCards().get(0).getSuit(),
                        state.getP1TopPalaceCards().get(0).getRank());
                        if(state.getSelectedPalaceCards().contains(state.getP1TopPalaceCards().get(0))){
                            highlightRect = new Rect((int)playerTopCardLeftX,
                                                     (int)playerTopCardLeftY,
                                                (int)playerTopCardLeftX+cardWidth,
                                              (int)playerTopCardLeftY+cardHeight);
                            canvas.drawRect(highlightRect, highlightPaint);
                        }
                drawCard(canvas, playerTopCardCenterX, playerTopCardCenterY,
                        state.getP1TopPalaceCards().get(1).getSuit(),
                        state.getP1TopPalaceCards().get(1).getRank());
                        if(state.getSelectedPalaceCards().contains(state.getP1TopPalaceCards().get(1))) {
                            highlightRect = new Rect((int) playerTopCardCenterX,
                                    (int) playerTopCardCenterY,
                                    (int) playerTopCardCenterX + cardWidth,
                                    (int) playerTopCardCenterY + cardHeight);
                            canvas.drawRect(highlightRect, highlightPaint);
                        }
                drawCard(canvas, playerTopCardRightX, playerTopCardRightY,
                        state.getP1TopPalaceCards().get(2).getSuit(),
                        state.getP1TopPalaceCards().get(2).getRank());
                        if(state.getSelectedPalaceCards().contains(state.getP1TopPalaceCards().get(2))){
                            highlightRect = new Rect((int)playerTopCardRightX,
                            (int)playerTopCardRightY,
                            (int)playerTopCardRightX+cardWidth,
                            (int)playerTopCardRightY+cardHeight);
                            canvas.drawRect(highlightRect, highlightPaint);}

            } else if (state.getP1TopPalaceCards().size() == 2) {
                drawCard(canvas, playerTopCardLeftX, playerTopCardLeftY,
                        state.getP1TopPalaceCards().get(0).getSuit(),
                        state.getP1TopPalaceCards().get(0).getRank());
                        if(state.getSelectedPalaceCards().contains(state.getP1TopPalaceCards().get(0))){
                        highlightRect = new Rect((int)playerTopCardLeftX,
                            (int)playerTopCardLeftY,
                            (int)playerTopCardLeftX+cardWidth,
                            (int)playerTopCardLeftY+cardHeight);
                            canvas.drawRect(highlightRect, highlightPaint);}
                drawCard(canvas, playerTopCardCenterX, playerTopCardCenterY,
                        state.getP1TopPalaceCards().get(1).getSuit(),
                        state.getP1TopPalaceCards().get(1).getRank());
                        if(state.getSelectedPalaceCards().contains(state.getP1TopPalaceCards().get(1))){
                         highlightRect = new Rect((int)playerTopCardCenterX,
                            (int)playerTopCardCenterY,
                            (int)playerTopCardCenterX+cardWidth,
                            (int)playerTopCardCenterY+cardHeight);
                            canvas.drawRect(highlightRect, highlightPaint);}
            } else if (state.getP1TopPalaceCards().size() == 1) {
                drawCard(canvas, playerTopCardLeftX, playerTopCardLeftY,
                        state.getP1TopPalaceCards().get(0).getSuit(),
                        state.getP1TopPalaceCards().get(0).getRank());
                        if(state.getSelectedPalaceCards().contains(state.getP1TopPalaceCards().get(0))){
                            highlightRect = new Rect((int)playerTopCardLeftX,
                            (int)playerTopCardLeftY,
                            (int)playerTopCardLeftX+cardWidth,
                            (int)playerTopCardLeftY+cardHeight);
                            canvas.drawRect(highlightRect, highlightPaint);}
            }

            //draw the face down versions of player's bottom cards
            if (state.getP1BottomPalaceCards().size() == 3) {
                drawCard(canvas, playerTopCardLeftX, playerTopCardLeftY,
                        state.getP1BottomPalaceCards().get(0).getSuit(),
                        state.getP1BottomPalaceCards().get(0).getRank());
                drawCard(canvas, playerTopCardCenterX, playerTopCardCenterY,
                        state.getP1BottomPalaceCards().get(1).getSuit(),
                        state.getP1BottomPalaceCards().get(1).getRank());
                drawCard(canvas, playerTopCardRightX, playerTopCardRightY,
                        state.getP1BottomPalaceCards().get(2).getSuit(),
                        state.getP1BottomPalaceCards().get(2).getRank());
            } else if (state.getP1BottomPalaceCards().size() == 2) {
                drawCard(canvas, playerTopCardLeftX, playerTopCardLeftY,
                        state.getP1BottomPalaceCards().get(0).getSuit(),
                        state.getP1BottomPalaceCards().get(0).getRank());
                drawCard(canvas, playerTopCardCenterX, playerTopCardCenterY,
                        state.getP1BottomPalaceCards().get(1).getSuit(),
                        state.getP1BottomPalaceCards().get(1).getRank());
            } else if (state.getP1BottomPalaceCards().size() == 1) {
                drawCard(canvas, playerTopCardLeftX, playerTopCardLeftY,
                        state.getP1BottomPalaceCards().get(0).getSuit(),
                        state.getP1BottomPalaceCards().get(0).getRank());
            }

            //draw player's hand
            playerHandCardCenterX = xCenter - cardWidth / 2;
            playerHandCardCenterY = yBottom - yMargin - displayConvert * 130;
            playerHandCardRightX = xCenter + cardWidth * 2 - cardWidth / 2;
            playerHandCardRightY = yBottom - yMargin - displayConvert * 130;
            playerHandCardLeftX = xCenter - cardWidth * 2 - cardWidth / 2;
            playerHandCardLeftY = yBottom - yMargin - displayConvert * 130;

            int cardToGet = state.getNumDisplayHand() * 3;
            if (state.getP1Hand().size() == 2) {
                drawCard(canvas, playerHandCardLeftX, playerHandCardLeftY,
                        state.getP1Hand().get(0).getSuit(), state.getP1Hand().get(0).getRank());
                        if(state.getSelectedPalaceCards().contains(state.getP1Hand().get(0))){
                        highlightRect = new Rect((int)playerHandCardLeftX,
                            (int)playerHandCardLeftY,
                            (int)playerHandCardLeftX+cardWidth,
                            (int)playerHandCardLeftY+cardHeight);
                            canvas.drawRect(highlightRect, highlightPaint);}
                drawCard(canvas, playerHandCardCenterX, playerHandCardCenterY,
                        state.getP1Hand().get(1).getSuit(), state.getP1Hand().get(1).getRank());
                        if(state.getSelectedPalaceCards().contains(state.getP1Hand().get(1))){
                        highlightRect = new Rect((int)playerHandCardCenterX,
                            (int)playerHandCardCenterY,
                            (int)playerHandCardCenterX+cardWidth,
                            (int)playerHandCardCenterY+cardHeight);
                            canvas.drawRect(highlightRect, highlightPaint);}
            } else if (state.getP1Hand().size() == 1) {
                drawCard(canvas, playerHandCardLeftX, playerHandCardLeftY,
                        state.getP1Hand().get(0).getSuit(), state.getP1Hand().get(0).getRank());
                        if(state.getSelectedPalaceCards().contains(state.getP1Hand().get(0))){
                        highlightRect = new Rect((int)playerHandCardRightX,
                            (int)playerHandCardRightY,
                            (int)playerHandCardRightX+cardWidth,
                            (int)playerHandCardRightY+cardHeight);
                            canvas.drawRect(highlightRect, highlightPaint);}
            } else if (state.getP1Hand().size() == 0) {

            } else if (state.getNumDisplayHand() < state.getP1Hand().size() / 3) {
                drawCard(canvas, playerHandCardCenterX, playerHandCardCenterY,
                        state.getP1Hand().get(cardToGet + 1).getSuit(),
                        state.getP1Hand().get(cardToGet + 1).getRank());
                        if(state.getSelectedPalaceCards().contains(state.getP1Hand().get(cardToGet+1))){
                        highlightRect = new Rect((int)playerHandCardCenterX,
                            (int)playerHandCardCenterY,
                            (int)playerHandCardCenterX+cardWidth,
                            (int)playerHandCardCenterY+cardHeight);
                            canvas.drawRect(highlightRect, highlightPaint);}
                drawCard(canvas, playerHandCardRightX, playerHandCardRightY,
                        state.getP1Hand().get(cardToGet + 2).getSuit(),
                        state.getP1Hand().get(cardToGet + 2).getRank());
                        if(state.getSelectedPalaceCards().contains(state.getP1Hand().get(cardToGet+2))){
                        highlightRect = new Rect((int)playerHandCardRightX,
                            (int)playerHandCardRightY,
                            (int)playerHandCardRightX+cardWidth,
                            (int)playerHandCardRightY+cardHeight);
                            canvas.drawRect(highlightRect, highlightPaint);}
                drawCard(canvas, playerHandCardLeftX, playerHandCardLeftY,
                        state.getP1Hand().get(cardToGet).getSuit(),
                        state.getP1Hand().get(cardToGet).getRank());
                        if(state.getSelectedPalaceCards().contains(state.getP1Hand().get(cardToGet))){
                        highlightRect = new Rect((int)playerHandCardLeftX,
                            (int)playerHandCardLeftY,
                            (int)playerHandCardLeftX+cardWidth,
                            (int)playerHandCardLeftY+cardHeight);
                            canvas.drawRect(highlightRect, highlightPaint);}
            } else if (state.getP1Hand().size() % 3 == 1) {
                drawCard(canvas, playerHandCardLeftX, playerHandCardLeftY,
                        state.getP1Hand().get(cardToGet).getSuit(),
                        state.getP1Hand().get(cardToGet).getRank());
                        if(state.getSelectedPalaceCards().contains(state.getP1Hand().get(cardToGet))){
                        highlightRect = new Rect((int)playerHandCardLeftX,
                            (int)playerHandCardLeftY,
                            (int)playerHandCardLeftX+cardWidth,
                            (int)playerHandCardLeftY+cardHeight);
                            canvas.drawRect(highlightRect, highlightPaint);}
            } else if (state.getP1Hand().size() % 3 == 2) {
                drawCard(canvas, playerHandCardLeftX, playerHandCardLeftY,
                        state.getP1Hand().get(cardToGet).getSuit(),
                        state.getP1Hand().get(cardToGet).getRank());
                        if(state.getSelectedPalaceCards().contains(state.getP1Hand().get(cardToGet))){
                        highlightRect = new Rect((int)playerHandCardLeftX,
                            (int)playerHandCardLeftY,
                            (int)playerHandCardLeftX+cardWidth,
                            (int)playerHandCardLeftY+cardHeight);
                            canvas.drawRect(highlightRect, highlightPaint);}
                drawCard(canvas, playerHandCardCenterX, playerHandCardCenterY,
                        state.getP1Hand().get(cardToGet + 1).getSuit(),
                        state.getP1Hand().get(cardToGet + 1).getRank());
                        if(state.getSelectedPalaceCards().contains(state.getP1Hand().get(cardToGet+1))){
                        highlightRect = new Rect((int)playerHandCardCenterX,
                            (int)playerHandCardCenterY,
                            (int)playerHandCardCenterX+cardWidth,
                            (int)playerHandCardCenterY+cardHeight);
                            canvas.drawRect(highlightRect, highlightPaint);}
            }
        }

    }

    /**
     * draws a card with a value. If the card value is between 1 and 14, it will display the values
     * Ace through King accordingly. -1 will draw a facedown card.
     *
     * CAVEATS: None
     *
     * @param canvas
     * @param left
     * @param top
     * @param rank
     * @param suit
     */
    public void drawCard(Canvas canvas, float left, float top, int suit, int rank) {
        if (rank == -1) {
            canvas.drawBitmap(cardback, left, top, null);
        } else if (suit == 3) {
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

        } else if (suit == 1) {
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
        } else if (suit == 2) {
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
        } else if (suit == 4) {
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

    //getters and setters
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

    public float getPlayerTopCardCenterX() {
        return playerTopCardCenterX;
    }

    public void setPlayerTopCardCenterX(float playerTopCardCenterX) {
        this.playerTopCardCenterX = playerTopCardCenterX;
    }

    public float getPlayerTopCardCenterY() {
        return playerTopCardCenterY;
    }

    public void setPlayerTopCardCenterY(float playerTopCardCenterY) {
        this.playerTopCardCenterY = playerTopCardCenterY;
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

    public float getPlayerHandCardCenterX() {
        return playerHandCardCenterX;
    }

    public void setPlayerHandCardCenterX(float playerHandCardCenterX) {
        this.playerHandCardCenterX = playerHandCardCenterX;
    }

    public float getPlayerHandCardCenterY() {
        return playerHandCardCenterY;
    }

    public void setPlayerHandCardCenterY(float playerHandCardCenterY) {
        this.playerHandCardCenterY = playerHandCardCenterY;
    }

    public float getPlayerHandCardRightX() {
        return playerHandCardRightX;
    }

    public void setPlayerHandCardRightX(float playerHandCardRightX) {
        this.playerHandCardRightX = playerHandCardRightX;
    }

    public float getPlayerHandCardRightY() {
        return playerHandCardRightY;
    }

    public void setPlayerHandCardRightY(float playerHandCardRightY) {
        this.playerHandCardRightY = playerHandCardRightY;
    }

    public float getPlayerHandCardLeftX() {
        return playerHandCardLeftX;
    }

    public void setPlayerHandCardLeftX(float playerHandCardLeftX) {
        this.playerHandCardLeftX = playerHandCardLeftX;
    }

    public float getPlayerHandCardLeftY() {
        return playerHandCardLeftY;
    }

    public void setPlayerHandCardLeftY(float playerHandCardLeftY) {
        this.playerHandCardLeftY = playerHandCardLeftY;
    }

    public PalaceGameState getState() {
        return state;
    }

    public void setState(PalaceGameState state) {
        this.state = state;
    }
}
