package edu.up.palace.palace;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.up.palace.game.GameHumanPlayer;
import edu.up.palace.game.GameMainActivity;
import com.example.palace.game.R;
import edu.up.palace.game.infoMsg.GameInfo;

/**
 * @author Caroline Cavaliere, Jimi Hayes, Chloe Gan, Nathaniel Pon
 * This class allows for human player to play Palace by interacting with the GUI.
 */
public class PalaceHumanPlayer extends GameHumanPlayer implements View.OnClickListener,
        View.OnTouchListener {
    private PalaceView view;
    private Button takePile;
    private Button playCard;
    private Button quit;
    private Button restart;
    private Button nextCards;
    private Button previousCards;
    private Button switchTopCards;
    private Button help;
    private TextView handCount = null; // cards in the user's hand
    private TextView playerTurn = null;
    private PalaceGameState state;

    private int currentPage = 1; // keeps track of which page in the hand we're in

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
        if (!(info instanceof PalaceGameState)) {
            flash(Color.RED, 20);
            return;
        } else {
            view.setState((PalaceGameState) info);
            view.invalidate();
            state = (PalaceGameState) info;
            cardWidth = view.getCardWidth();
            cardHeight = view.getCardHeight();
            handCount.setText("" + "Cards in Hand: " + state.getP1Hand().size());

            if (((PalaceGameState) info).getSelectedPalaceCards().isEmpty()) {
                playCard.setText("Select Card(s)");
            } else {
                playCard.setText("Play it!");
            }


            if (state.getTurn() == 0) {
                playerTurn.setText("" + "Your turn!");
            } else {
                playerTurn.setText("" + "Computers is thinking...");
            }


            if (((PalaceGameState) info).getP1Hand().size() > 3) {
                nextCards.setVisibility(View.VISIBLE);

                //
            } else if (((PalaceGameState) info).getP1Hand().size() <= 3 && currentPage == 1) {
                nextCards.setVisibility(View.INVISIBLE);
                previousCards.setVisibility(View.INVISIBLE);
            }

            if (currentPage == 1) {
                previousCards.setVisibility(View.INVISIBLE);
            }
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

        view = (PalaceView) activity.findViewById(R.id.surfaceView);
        takePile = (Button) activity.findViewById(R.id.takepile);
        playCard = (Button) activity.findViewById(R.id.playcard);
        quit = (Button) activity.findViewById(R.id.quit_button);
        restart = (Button) activity.findViewById(R.id.restart_button);
        nextCards = (Button) activity.findViewById(R.id.nextThreeCards);
        previousCards = (Button) activity.findViewById(R.id.previousThreeCards);
        switchTopCards = (Button) activity.findViewById(R.id.switchtop);
        help = (Button)activity.findViewById(R.id.help);
        handCount = (TextView)activity.findViewById(R.id.cardsInHand);
        playerTurn = (TextView)activity.findViewById(R.id.playerTurn);


        //listen for button presses and card taps
        view.setOnTouchListener(this);
        takePile.setOnClickListener(this);
        playCard.setOnClickListener(this);
        quit.setOnClickListener(this);
        restart.setOnClickListener(this);
        nextCards.setOnClickListener(this);
        previousCards.setOnClickListener(this);
        switchTopCards.setOnClickListener(this);
        help.setOnClickListener(this);

    }

    /**
     * this method is called when the user clicks any of the buttons on screen and
     * creates a new action depending on which button is pressed. The action is then
     * sent to the game.
     * <p>
     * CAVEATS: None
     *
     * @param button the button that was clicked
     * <p>
     * External Citation:
     * Date: 11 November 2020
     * Problem: How do we reset the game?
     * Resource:
     * https://stackoverflow.com/questions/22213357/making-a-restart-button-for-an-android-app-game
     * Solution: Used code snippet from the forum
     */
    @Override
    public void onClick(View button) {
        if (button.equals(quit)) {
            System.exit(0);
        } else if (button.equals(restart)) {
            myActivity.recreate(); // restart the game!
            //flash(Color.RED, 100);
        } else if (button.equals(takePile)) {
            PalaceTakePileAction takepile = new PalaceTakePileAction(this);
            this.game.sendAction(takepile);
        } else if (button.equals(playCard)) {
            PalacePlayCardAction playcard = new PalacePlayCardAction(this);
            this.game.sendAction(playcard);
        } else if (button.equals(nextCards)) {
            PalaceDisplayNextCards nextcards = new PalaceDisplayNextCards(this);
            this.game.sendAction(nextcards);
            // we want to stop incrementing the page we're when we've hit the max page. But how?
            if (currentPage < state.getP1Hand().size()-2) {
                currentPage++;
            }
            // the user moved to a new page so set previous button visible
            previousCards.setVisibility(View.VISIBLE);
        } else if (button.equals((previousCards))) {
            PalaceDisplayPreviousCards previousCards = new PalaceDisplayPreviousCards(this);
            this.game.sendAction(previousCards);
            // if we're not on the first page of the cards in the hand, decrement!
            if (currentPage > 0) {
                currentPage--;
            }
        } else if (button.equals(switchTopCards)) {
            PalaceSwitchBaseCardsAction switchCards = new PalaceSwitchBaseCardsAction(this);
            this.game.sendAction(switchCards);
            button.setVisibility(View.GONE);
            Toast.makeText(myActivity, "You can't switch your top cards out anymore!",
                    Toast.LENGTH_LONG).show();
        } else if (button.equals(help)) {
            PalaceDisplayHelp displayHelp = new PalaceDisplayHelp();
            displayHelp.showPopupWindow(button);
        }

    }

    /**
     * this method is called when the user taps on the card they would like to
     * select to play. When the view is tapped, a new PalaceSelectCardAction is sent
     * to the game.
     * <p>
     * CAVEATS: None
     *
     * @param palaceView  the view we're editing
     * @param motionEvent the motion that was done.
     * @return External Citation
     * Date: 8 November 2020
     * Problem: How to map a bitmap
     * Resource:
     * https://stackoverflow.com/questions/18826808/how-to-make-a-bitmap-using-canvas-clickable
     * Solution: I used the example code from this post and modified it to fit our cards drawn
     */
    @Override
    public boolean onTouch(View palaceView, MotionEvent motionEvent) {
        if (state == null) {
            return true;
        }
        float x = motionEvent.getX(); // x position on the screen of the motion event
        float y = motionEvent.getY(); // y position on the screen of motion event
        int cardToGet = state.getNumDisplayHand() * 3; // index of the card we want, 0-2
        //int playerTurn = state.getTurn() + 1;
        //playPileCount.setText("Player " + playerTurn + "'s Turn");

        if (state.getP1Hand().size() > cardToGet || cardToGet == 0 && state.getP1Hand().size() == 0) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:  // touch/tap action

                    //Check if the x and y position of the touch is inside the bitmap
                    // Middle card of the user hand shown in the view
                    if (x > view.getPlayerHandCardCenterX() &&
                            x < view.getPlayerHandCardCenterX() + cardWidth &&
                            y > view.getPlayerHandCardCenterY() &&
                            y < view.getPlayerHandCardCenterY() + cardHeight &&
                            state.getP1Hand().size() > 1 && !(state.getP1Hand().isEmpty())) {
                        //Bitmap touched
                        //Q.)how does it know which card is there (rank,value)?
                        //A.)ex: state.getP1TopCards().get(index)
                        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(
                                this,
                                state.getP1Hand().get(cardToGet + 1),
                                state.getSelectedPalaceCards());

                        this.game.sendAction(selectcard);
                    }

                    // First (left-most) card of the user hand shown in the view
                    if (x > view.getPlayerHandCardLeftX() &&
                            x < view.getPlayerHandCardLeftX() + cardWidth &&
                            y > view.getPlayerHandCardLeftY() &&
                            y < view.getPlayerHandCardLeftY() + cardHeight &&
                            state.getP1Hand().size() > 0 && !(state.getP1Hand().isEmpty())) {
                        //Bitmap touched
                        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(
                                this,
                                state.getP1Hand().get(cardToGet),
                                state.getSelectedPalaceCards());
                        this.game.sendAction(selectcard);
                    }

                    //third card (right-most) of the user hand shown in the view
                    if (x > view.getPlayerHandCardRightX() &&
                            x < view.getPlayerHandCardRightX() + cardWidth &&
                            y > view.getPlayerHandCardRightY() &&
                            y < view.getPlayerHandCardRightY() + cardHeight &&
                            state.getP1Hand().size() > 2 && !(state.getP1Hand().isEmpty())) {
                        //Bitmap touched
                        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(
                                this,
                                state.getP1Hand().get(cardToGet + 2),
                                state.getSelectedPalaceCards());
                        this.game.sendAction(selectcard);
                    }

                    //int localCardToGet = state.getNumDisplayHand()*3;
                    // Middle card of the top hand shown in the view
                    if (x > view.getPlayerTopCardCenterX() &&
                            x < view.getPlayerTopCardCenterX() + cardWidth &&
                            y > view.getPlayerTopCardCenterY() &&
                            y < view.getPlayerTopCardCenterY() + cardHeight &&
                            state.getP1TopPalaceCards().size() >= 2 &&
                            state.getP1Hand().isEmpty()) {
                        //Bitmap touched
                        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(
                                this,
                                state.getP1TopPalaceCards().get(1),
                                state.getSelectedPalaceCards());

                        this.game.sendAction(selectcard);
                    }

                    // First (left-most) card of the top hand shown in the view
                    if (x > view.getPlayerTopCardLeftX() &&
                            x < view.getPlayerTopCardLeftX() + cardWidth &&
                            y > view.getPlayerTopCardLeftY() &&
                            y < view.getPlayerTopCardLeftY() + cardHeight &&
                            state.getP1TopPalaceCards().size() >= 1 &&
                            state.getP1Hand().isEmpty()) {
                        //Bitmap touched
                        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(
                                this,
                                state.getP1TopPalaceCards().get(0),
                                state.getSelectedPalaceCards());

                        this.game.sendAction(selectcard);
                    }

                    //third card (right-most) of the user hand shown in the view
                    if (x > view.getPlayerTopCardRightX() &&
                            x < view.getPlayerTopCardRightX() + cardWidth &&
                            y > view.getPlayerTopCardRightY() &&
                            y < view.getPlayerTopCardRightY() + cardHeight &&
                            state.getP1TopPalaceCards().size() == 3 &&
                            state.getP1Hand().isEmpty()) {
                        //Bitmap touched
                        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(
                                this,
                                state.getP1TopPalaceCards().get(2),
                                state.getSelectedPalaceCards());

                        this.game.sendAction(selectcard);
                    }
                    // for Bottom Cards
                    // Middle card of the top hand shown in the view
                    if (x > view.getPlayerTopCardCenterX() &&
                            x < view.getPlayerTopCardCenterX() + cardWidth &&
                            y > view.getPlayerTopCardCenterY() &&
                            y < view.getPlayerTopCardCenterY() + cardHeight &&
                            state.getP1BottomPalaceCards().size() >= 2 &&
                            state.getP1TopPalaceCards().isEmpty() &&
                            state.getP1Hand().isEmpty()) {
                        //Bitmap touched
                        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(
                                this,
                                state.getP1BottomPalaceCards().get(1),
                                state.getSelectedPalaceCards());

                        this.game.sendAction(selectcard);
                    }

                    // First (left-most) card of the top hand shown in the view
                    if (x > view.getPlayerTopCardLeftX() &&
                            x < view.getPlayerTopCardLeftX() + cardWidth &&
                            y > view.getPlayerTopCardLeftY() &&
                            y < view.getPlayerTopCardLeftY() + cardHeight &&
                            state.getP1BottomPalaceCards().size() >= 1 &&
                            state.getP1TopPalaceCards().isEmpty() &&
                            state.getP1Hand().isEmpty()) {
                        //Bitmap touched
                        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(
                                this,
                                state.getP1BottomPalaceCards().get(0),
                                state.getSelectedPalaceCards());

                        this.game.sendAction(selectcard);
                    }

                    //third card (right-most) of the user hand shown in the view
                    if (x > view.getPlayerTopCardRightX() &&
                            x < view.getPlayerTopCardRightX() + cardWidth &&
                            y > view.getPlayerTopCardRightY() &&
                            y < view.getPlayerTopCardRightY() + cardHeight &&
                            state.getP1BottomPalaceCards().size() == 3 &&
                            state.getP1TopPalaceCards().isEmpty() &&
                            state.getP1Hand().isEmpty()) {
                        //Bitmap touched
                        PalaceSelectCardAction selectcard = new PalaceSelectCardAction(
                                this,
                                state.getP1BottomPalaceCards().get(2),
                                state.getSelectedPalaceCards());

                        this.game.sendAction(selectcard);
                    }


                    view.invalidate();
                    return true;
            }
        }
        return false;
    }

    /**
     * overriden to flash the background of the PalaceView red.
     * <p>
     * CAVEATS: None
     *
     * @param color    the color to flash
     * @param duration how long the flash lasts
     */
    @Override
    protected void flash(int color, int duration) {
        if (view == null) return;
        int savedColor = getBackgroundColor(view);
        view.setBackgroundColor(color);

        myHandler.postDelayed(new Unflash(savedColor), duration);
    }

    /**
     * @author Caroline Cavaliere, Jimi Hayes, Chloe Gan, Nathaniel Pon
     * <p>
     * CAVEATS: None
     * <p>
     * Class that will revert back to the old background color after flashing the screen
     */
    private class Unflash implements Runnable {
        private int oldColor;

        public Unflash(int oldColor) {
            this.oldColor = oldColor;
        }

        public void run() {
            if (view == null) return;
            view.setBackgroundColor(oldColor);
        }
    }
}
