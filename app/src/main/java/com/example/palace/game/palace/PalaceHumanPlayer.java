package com.example.palace.game.palace;

import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.graphics.Canvas;


import com.example.palace.game.GameHumanPlayer;
import com.example.palace.game.GameMainActivity;
import com.example.palace.game.R;
import com.example.palace.game.infoMsg.GameInfo;

public class PalaceHumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener {
    private PalaceView view;
    private Button takePile;
    private Button playCard;
    private Button quit;
    private Button restart;

    private PalaceGameState state;
    // card dimensions
    private int cardWidth;
    private int cardHeight;


    /**
     * constructor
     *
     * @param name the name of the player
     */
    private GameMainActivity myActivity;
    public PalaceHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    @Override
    public void receiveInfo(GameInfo info) {
        if(!(info instanceof PalaceGameState))
        {
            flash(Color.RED, 20);
            return;
        }
        else{
            view.setState((PalaceGameState) info);
            view.invalidate();
            state = (PalaceGameState) info;
            cardWidth = view.getCardWidth();
            cardHeight = view.getCardHeight();
        }
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.palace_layout);

        view = (PalaceView)activity.findViewById(R.id.surfaceView);
        takePile = (Button)activity.findViewById(R.id.takepile);
        playCard = (Button)activity.findViewById(R.id.playcard);
        quit = (Button)activity.findViewById(R.id.quit_button);
        restart = (Button)activity.findViewById(R.id.restart_button);

        view.setOnTouchListener(this);
        takePile.setOnClickListener(this);
        playCard.setOnClickListener(this);
        quit.setOnClickListener(this);
        restart.setOnClickListener(this);
    }

    @Override
    public void onClick(View button) {
        if(button.equals(quit)){
            System.exit(0);
        }
        else if(button.equals(restart)){
            flash(Color.RED, 100);
        }
        else if(button.equals(takePile)){
            PalaceTakePileAction takepile = new PalaceTakePileAction(this);
            this.game.sendAction(takepile);
        }
        else if(button.equals(playCard)){
            PalacePlayCardAction playcard = new PalacePlayCardAction(this);
            this.game.sendAction(playcard);
        }
    }

    /**
     * @param palaceView
     * @param motionEvent
     * @return
     *
     * External Citation
     *      Date: 8 November 2020
     *      Problem: How to map a bitmap
     *      Resource:
     * https://stackoverflow.com/questions/18826808/how-to-make-a-bitmap-using-canvas-clickable
     *      Solution: I used the example code from this post and modified it to fit our cards drawn
     */
    @Override
    public boolean onTouch(View palaceView, MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(this);
        Canvas canvas = new Canvas();

        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:  // touch/tap action
                //Check if the x and y position of the touch is inside the bitmap

                // First card shown in the view hand
                if( x > view.getPlayerHandCardCenterX() &&
                        x < view.getPlayerHandCardCenterX() + cardWidth &&
                        y > view.getPlayerHandCardCenterY() &&
                        y < view.getPlayerHandCardCenterY() + cardHeight ) {
                    //Bitmap touched
                    //Q.)how does it know which card is there (rank,value)?
                    //A.)state.getP1TopCards().get(1)
                    if (view.isPlayerHandCardCenterTouched() == false) {
                        view.setPlayerHandCardCenterTouched(true);
                    } else {
                        view.setPlayerHandCardCenterTouched(false);
                    }
                    if(view.isPlayerHandCardCenterTouched() == true) {
                        state.setCardToBeSelected(state.getP1Hand().get(1));
                        this.game.sendAction(selectcard);
                    }
                }

                //second Card shown in the view hand
                if( x > view.getPlayerHandCardLeftX() &&
                        x < view.getPlayerHandCardLeftX() + cardWidth &&
                        y > view.getPlayerHandCardLeftY() &&
                        y < view.getPlayerHandCardLeftY() + cardHeight ) {
                    //Bitmap touched

                    state.setCardToBeSelected(state.getP1Hand().get(0));
                    this.game.sendAction(selectcard);
                }

                //third card shown in the view hand
                if( x > view.getPlayerHandCardRightX() &&
                        x < view.getPlayerHandCardRightX() + cardWidth &&
                        y > view.getPlayerHandCardRightY() &&
                        y < view.getPlayerHandCardRightY() + cardHeight ) {
                    //Bitmap touched
                    state.setCardToBeSelected(state.getP1Hand().get(2));
                    this.game.sendAction(selectcard);
                }

                // First card shown in the view top
                if( x > view.getPlayerTopCardCenterX() &&
                        x < view.getPlayerTopCardCenterX() + cardWidth &&
                        y > view.getPlayerTopCardCenterY() &&
                        y < view.getPlayerTopCardCenterY() + cardHeight ) {
                    //Bitmap touched
                    state.setCardToBeSelected(state.getP1TopPalaceCards().get(1));
                    this.game.sendAction(selectcard);
                }

                //second Card shown in the view top
                if( x > view.getPlayerTopCardLeftX() &&
                        x < view.getPlayerTopCardLeftX() + cardWidth &&
                        y > view.getPlayerTopCardLeftY() &&
                        y < view.getPlayerTopCardLeftY() + cardHeight ) {
                    //Bitmap touched
                    state.setCardToBeSelected(state.getP1TopPalaceCards().get(0));
                    this.game.sendAction(selectcard);
                }

                //third card shown in the view top
                if( x > view.getPlayerTopCardRightX() &&
                        x < view.getPlayerTopCardRightX() + cardWidth &&
                        y > view.getPlayerTopCardRightY() &&
                        y < view.getPlayerTopCardRightY() + cardHeight ) {
                    //Bitmap touched
                    state.setCardToBeSelected(state.getP1TopPalaceCards().get(2));
                    this.game.sendAction(selectcard);
                }
                return true;
        }
        //view.invalidate();
        return false;
    }

    @Override
    protected void flash(int color, int duration){
        // get the top view, ignoring if null
        if (view == null) return;

        // save the original background color; set the new background
        // color
        int savedColor = getBackgroundColor(view);
        view.setBackgroundColor(color);

        // set up a timer event to set the background color back to
        // the original.
        myHandler.postDelayed(new Unflash(savedColor), duration);
    }

    private class Unflash implements Runnable {

        // the original color
        private int oldColor;

        // constructor
        public Unflash(int oldColor) {
            this.oldColor = oldColor;
        }

        // method to run at the appropriate time: sets background color
        // back to the original
        public void run() {
            if (view== null) return;
            view.setBackgroundColor(oldColor);
        }
    }
}
