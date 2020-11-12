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

/**
 * @author Caroline Cavaliere, Jimi Hayes, Chloe Gan, Nathaniel Pon
 * This class allows for human player to play Palace by interacting with the GUI.
 */
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

    private GameMainActivity myActivity;//android activity being run

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public PalaceHumanPlayer(String name) {
        super(name);
    }

    /**
     * @return GUI's top view object
     */
    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * callback method for when a message is sent
     *
     * @param info
     */
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

    /**
     * callback method for setting the GUI
     *
     * @param activity the activity being run
     */
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

        //listen for button presses and card taps
        view.setOnTouchListener(this);
        takePile.setOnClickListener(this);
        playCard.setOnClickListener(this);
        quit.setOnClickListener(this);
        restart.setOnClickListener(this);
        nextCards.setOnClickListener(this);
        previousCards.setOnClickListener(this);

    }

    /**
     * this method is called when the user clicks any of the buttons on screen and
     * creates a new action depending on which button is pressed. The action is then
     * sent to the game.
     *
     * @param button the button that was clicked
     */
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
     * this method is called when the user taps on the card they would like to
     * select to play. When the view is tapped, a new PalaceSelectCardAction is sent
     * to the game.
     *
     * @param palaceView
     * @param motionEvent
     * @return
     *
     * External Citation
     *      Date: 8 November 2020
     *      Problem: How to map a bitmap
     *      Resource:
     *      https://stackoverflow.com/questions/18826808/how-to-make-a-bitmap-using-canvas-clickable
     *      Solution: I used the example code from this post and modified it to fit our cards drawn
     */
    @Override
    public boolean onTouch(View palaceView, MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int cardToGet = state.getNumDisplayHand()*3;

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
                    PalaceSelectCardAction selectcard = new PalaceSelectCardAction(this, state.getP1Hand().get(state.getNumDisplayHand()+1), state.getSelectedPalaceCards());
                    this.game.sendAction(selectcard);
                }

                //second Card shown in the view hand
                if( x > view.getPlayerHandCardLeftX() &&
                        x < view.getPlayerHandCardLeftX() + cardWidth &&
                        y > view.getPlayerHandCardLeftY() &&
                        y < view.getPlayerHandCardLeftY() + cardHeight ) {
                    //Bitmap touched
                    PalaceSelectCardAction selectcard = new PalaceSelectCardAction(this, state.getP1Hand().get(state.getNumDisplayHand()), state.getSelectedPalaceCards());
                    this.game.sendAction(selectcard);
                }

                //third card shown in the view hand
                if( x > view.getPlayerHandCardRightX() &&
                        x < view.getPlayerHandCardRightX() + cardWidth &&
                        y > view.getPlayerHandCardRightY() &&
                        y < view.getPlayerHandCardRightY() + cardHeight ) {
                    //Bitmap touched
                    PalaceSelectCardAction selectcard = new PalaceSelectCardAction(this, state.getP1Hand().get(state.getNumDisplayHand() + 2), state.getSelectedPalaceCards());
                    this.game.sendAction(selectcard);
                }

                // First card shown in the view top
                if( x > view.getPlayerTopCardCenterX() &&
                        x < view.getPlayerTopCardCenterX() + cardWidth &&
                        y > view.getPlayerTopCardCenterY() &&
                        y < view.getPlayerTopCardCenterY() + cardHeight ) {
                    //Bitmap touched
                    PalaceSelectCardAction selectcard = new PalaceSelectCardAction(this, state.getP1TopPalaceCards().get(1), state.getSelectedPalaceCards());
                    this.game.sendAction(selectcard);
                }

                //second Card shown in the view top
                if( x > view.getPlayerTopCardLeftX() &&
                        x < view.getPlayerTopCardLeftX() + cardWidth &&
                        y > view.getPlayerTopCardLeftY() &&
                        y < view.getPlayerTopCardLeftY() + cardHeight ) {
                    //Bitmap touched
                    PalaceSelectCardAction selectcard = new PalaceSelectCardAction(this, state.getP1TopPalaceCards().get(0), state.getSelectedPalaceCards());
                    this.game.sendAction(selectcard);
                }

                //third card shown in the view top
                if( x > view.getPlayerTopCardRightX() &&
                        x < view.getPlayerTopCardRightX() + cardWidth &&
                        y > view.getPlayerTopCardRightY() &&
                        y < view.getPlayerTopCardRightY() + cardHeight ) {
                    //Bitmap touched
                    PalaceSelectCardAction selectcard = new PalaceSelectCardAction(this, state.getP1TopPalaceCards().get(2), state.getSelectedPalaceCards());
                    this.game.sendAction(selectcard);
                }
                return true;
        }
        return false;
    }

    /**
     * overriden to flash the background of the PalaceView red.
     *
     * @param color
     * 			the color to flash
     * @param duration
     *          how long the flash lasts
     */
    @Override
    protected void flash(int color, int duration){
        if (view == null) return;
        int savedColor = getBackgroundColor(view);
        view.setBackgroundColor(color);

        myHandler.postDelayed(new Unflash(savedColor), duration);
    }

    private class Unflash implements Runnable {
        private int oldColor;

        public Unflash(int oldColor) {
            this.oldColor = oldColor;
        }

        public void run() {
            if (view== null) return;
            view.setBackgroundColor(oldColor);
        }
    }
}
