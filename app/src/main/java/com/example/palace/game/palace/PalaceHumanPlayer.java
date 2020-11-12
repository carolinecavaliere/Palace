package com.example.palace.game.palace;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


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
    private Button nextCards;
    private Button previousCards;

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
        nextCards = (Button)activity.findViewById(R.id.nextThreeCards);
        previousCards = (Button)activity.findViewById(R.id.previousThreeCards);

        view.setOnTouchListener(this);
        takePile.setOnClickListener(this);
        playCard.setOnClickListener(this);
        quit.setOnClickListener(this);
        restart.setOnClickListener(this);
        nextCards.setOnClickListener(this);
        previousCards.setOnClickListener(this);

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
        else if (button.equals(nextCards)){
            PalaceDisplayNextCards nextcards = new PalaceDisplayNextCards(this);
            this.game.sendAction(nextcards);
        }
        else if (button.equals((previousCards))) {
            PalaceDisplayPreviousCards previousCards = new PalaceDisplayPreviousCards(this);
            this.game.sendAction(previousCards);
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

        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:  // touch/tap action
                //Check if the x and y position of the touch is inside the bitmap

                // First card shown in the view hand
                if( x > view.getPlayerHandCardCenterX() &&
                        x < view.getPlayerHandCardCenterX() + cardWidth &&
                        y > view.getPlayerHandCardCenterY() &&
                        y < view.getPlayerHandCardCenterY() + cardHeight ) {
                    //Bitmap touched
                    if (state.isPlayerHandCardCenterTouched() == false) {
                        state.setPlayerHandCardCenterTouched(true);
                    } else {
                        state.setPlayerHandCardCenterTouched(false);
                    }

                    //Q.)how does it know which card is there (rank,value)?
                    //A.)state.getP1TopCards().get(1)

                    //if that bitmap is touched and true and the selected cards don't have that card yet,
                    // we want to add it to the arraylist of cards that are selected
                    if (state.isPlayerHandCardCenterTouched() == true
                    && !state.getSelectedPalaceCards().contains(state.getP1Hand().get(state.getNumDisplayHand() +1))) {

                        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(this,
                                state.getP1Hand().get(state.getNumDisplayHand() + 1),
                                state.getSelectedPalaceCards());

                        this.game.sendAction(selectcard);

                        // else if that bitmap is touched and false (deselected),
                        // and has that card, we want to remove it from the cards to be selected...
                    } else if (state.isPlayerHandCardCenterTouched() == false
                            && state.getSelectedPalaceCards().contains(state.getP1Hand().get(state.getNumDisplayHand() +1))) {
                        state.removeFromSelectedCards(state.getP1Hand().get(state.getNumDisplayHand() +1));
                    }
                }

                //second Card shown in the view hand
                if( x > view.getPlayerHandCardLeftX() &&
                        x < view.getPlayerHandCardLeftX() + cardWidth &&
                        y > view.getPlayerHandCardLeftY() &&
                        y < view.getPlayerHandCardLeftY() + cardHeight ) {
                    //Bitmap touched
                    if (state.isPlayerHandCardLeftTouched() == false) {
                        state.setPlayerHandCardLeftTouched(true);
                    } else {
                        state.setPlayerHandCardLeftTouched(false);
                    }

                    if (state.isPlayerHandCardLeftTouched() == true
                            && !state.getSelectedPalaceCards().contains(state.getP1Hand().get(state.getNumDisplayHand()))) {
                        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(this,
                                state.getP1Hand().get(state.getNumDisplayHand()),
                                state.getSelectedPalaceCards());
                        this.game.sendAction(selectcard);
                    } else if (state.isPlayerHandCardCenterTouched() == false
                            && state.getSelectedPalaceCards().contains(state.getP1Hand().get(state.getNumDisplayHand()))) {
                        state.removeFromSelectedCards(state.getP1Hand().get(state.getNumDisplayHand()));
                    }

                }

                //third card shown in the view hand
                if( x > view.getPlayerHandCardRightX() &&
                        x < view.getPlayerHandCardRightX() + cardWidth &&
                        y > view.getPlayerHandCardRightY() &&
                        y < view.getPlayerHandCardRightY() + cardHeight ) {
                    //Bitmap touched
                    if (state.isPlayerHandCardRightTouched() == false) {
                        state.setPlayerHandCardRightTouched(true);
                    } else {
                        state.setPlayerHandCardRightTouched(false);
                    }

                    if (state.isPlayerHandCardRightTouched() == true
                            && !state.getSelectedPalaceCards().contains(state.getP1Hand().get(state.getNumDisplayHand() + 2))) {
                        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(this,
                                state.getP1Hand().get(state.getNumDisplayHand() + 2),
                                state.getSelectedPalaceCards());
                        this.game.sendAction(selectcard);
                    } else if (state.isPlayerHandCardCenterTouched() == false
                            && state.getSelectedPalaceCards().contains(state.getP1Hand().get(state.getNumDisplayHand() +2))) {
                        state.removeFromSelectedCards(state.getP1Hand().get(state.getNumDisplayHand() +2));
                    }

                }

                // First card shown in the view top
                if( x > view.getPlayerTopCardCenterX() &&
                        x < view.getPlayerTopCardCenterX() + cardWidth &&
                        y > view.getPlayerTopCardCenterY() &&
                        y < view.getPlayerTopCardCenterY() + cardHeight ) {
                    //Bitmap touched
                    if (state.isPlayerTopCardCenterTouched() == false) {
                        state.setPlayerTopCardCenterTouched(true);
                    } else {
                        state.setPlayerTopCardCenterTouched(false);
                    }

                    if (state.isPlayerTopCardCenterTouched() == true
                            && !state.getSelectedPalaceCards().contains(state.getP1TopPalaceCards().get(1))) {
                        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(this, state.getP1TopPalaceCards().get(1), state.getSelectedPalaceCards());
                        this.game.sendAction(selectcard);
                    }else if (state.isPlayerTopCardCenterTouched() == false
                            && state.getSelectedPalaceCards().contains(state.getP1TopPalaceCards().get(1))) {
                        state.removeFromSelectedCards(state.getP1TopPalaceCards().get(1));
                    }

                }

                //second Card shown in the view top
                if( x > view.getPlayerTopCardLeftX() &&
                        x < view.getPlayerTopCardLeftX() + cardWidth &&
                        y > view.getPlayerTopCardLeftY() &&
                        y < view.getPlayerTopCardLeftY() + cardHeight ) {
                    //Bitmap touched
                    if (state.isPlayerTopCardLeftTouched() == false) {
                        state.setPlayerTopCardLeftTouched(true);
                    } else {
                        state.setPlayerTopCardLeftTouched(false);
                    }

                    if (state.isPlayerTopCardLeftTouched() == true
                            && !state.getSelectedPalaceCards().contains(state.getP1TopPalaceCards().get(0))) {
                        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(this, state.getP1TopPalaceCards().get(0), state.getSelectedPalaceCards());
                        this.game.sendAction(selectcard);
                    }else if (state.isPlayerTopCardLeftTouched() == false
                            && state.getSelectedPalaceCards().contains(state.getP1TopPalaceCards().get(0))) {
                        state.removeFromSelectedCards(state.getP1TopPalaceCards().get(0));
                    }
                }

                //third card shown in the view top
                if( x > view.getPlayerTopCardRightX() &&
                        x < view.getPlayerTopCardRightX() + cardWidth &&
                        y > view.getPlayerTopCardRightY() &&
                        y < view.getPlayerTopCardRightY() + cardHeight ) {
                    //Bitmap touched
                    if (state.isPlayerTopCardRightTouched() == false) {
                        state.setPlayerTopCardRightTouched(true);
                    } else {
                        state.setPlayerTopCardRightTouched(false);
                    }

                    if (state.isPlayerTopCardRightTouched() == true
                            && !state.getSelectedPalaceCards().contains(state.getP1TopPalaceCards().get(2))) {
                        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(this, state.getP1TopPalaceCards().get(2), state.getSelectedPalaceCards());
                        this.game.sendAction(selectcard);
                    }else if (state.isPlayerTopCardRightTouched() == false
                            && state.getSelectedPalaceCards().contains(state.getP1TopPalaceCards().get(2))) {
                        state.removeFromSelectedCards(state.getP1TopPalaceCards().get(2));
                    }
                }
                return true;
        }
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
