package edu.up.palace.palace;

import android.util.Log;

import edu.up.palace.game.GameComputerPlayer;
import edu.up.palace.game.GameMainActivity;
import edu.up.palace.game.infoMsg.GameInfo;

/**
 * A dumb AI for Palace. It will always play the least value card that can beat the play pile so
 * it won't make any smart moves to take the pile if they wanted to build up their strong cards.
 * <p>
 * CAVEATS: Sometimes it's hard to beat b/c it always plays the card that it can and sometimes it
 * might be a high card that you can never beat. Sometimes it crashes due to an index out of bounds
 * exception. We are very unsure why, as this happens on line 85 where it should not even be entering
 * this if block if the play pile is empty.
 *
 * @author Jimi Hayes, Caroline Cavaliere, Nathaniel Pon, Chloe Gan
 */

public class PalaceComputerPlayer extends GameComputerPlayer {
    public PalaceComputerPlayer(String name) {
        super(name);
    }


    /**
     * Don't think overriding does anything but i included it because it seemed to make it stable.
     * We can use this method because we have direct access to the computer's thread.
     * @param milliseconds
     */
    @Override
    protected void sleep(int milliseconds) {
        super.sleep(milliseconds);
    }

    /**
     * callback method
     * <p>
     * CAVEATS: None
     *
     * @param info the information (containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        if (!(info instanceof PalaceGameState)) {
            return;
        }
        PalaceGameState state = new PalaceGameState((PalaceGameState) info);
        PalaceSelectCardAction selectCardAction;
        if (state.getTurn() != this.playerNum) {
            return;
        } else {
            sleep(1000); // make the computer "think"
            boolean isBigger = false;// variable to check if they have a valid card

            //determines if the cards are valid
            if (!(state.getP2Hand().isEmpty()) && !state.getPlayPilePalaceCards().isEmpty()) {
                for (int i = 0; i < state.getP2Hand().size(); i++) {
                    if (state.getP2Hand().get(i).getRank() > state.getPlayPilePalaceCards().
                            get(state.getPlayPilePalaceCards().size() - 1).getRank()) {
                        isBigger = true;
                    }
                }
            }

            //takes pile if they have no hand cards to play
            if (isBigger == false && !(state.getP2Hand().isEmpty())) {
                PalaceTakePileAction take = new PalaceTakePileAction(this);
                Log.d("compPlayer", "took the pile");
                this.game.sendAction(take);
            }

            //begins searching  for a card to select
            PalaceCard cardToSelect = null;

            // check  in  the case when the play pile and their hand are both not empty
            if (!(state.getPlayPilePalaceCards().isEmpty()) && (!(state.getP2Hand().isEmpty()))) {

                // while they haven't selected a card or the card they selected is less than the top card
                // another card is selected at random from their hand
                while (cardToSelect == null ||
                        (cardToSelect.getRank() <
                                (state.getPlayPilePalaceCards().
                                        get(state.getPlayPilePalaceCards().size() - 1).getRank()) &&
                                state.getPlayPilePalaceCards().
                                        get(state.getPlayPilePalaceCards().size() - 1).getRank() >=0)) {
                    cardToSelect =
                            state.getP2Hand().get((int) (Math.random() * state.getP2Hand().size()));
                }

                // selects a card when  they are playing from their top cards
            } else if (state.getP2Hand().isEmpty() && (!(state.getP2TopPalaceCards().isEmpty()))) {

                //pick any random card if the play pile is empty
                if (state.getPlayPilePalaceCards().isEmpty()) {
                    cardToSelect =
                            state.getP2TopPalaceCards().
                                    get((int) (Math.random() * state.getP2TopPalaceCards().size()));
                }

                //play pile is not empty so find a valid card
                else {

                    //checks to see if  there is a valid card
                    for (int i = 0; i < state.getP2TopPalaceCards().size(); i++) {
                        if (state.getP2TopPalaceCards().get(i).getRank() >
                                state.getPlayPilePalaceCards().
                                        get(state.getPlayPilePalaceCards().size() - 1).getRank() &&
                                state.getPlayPilePalaceCards().
                                        get(state.getPlayPilePalaceCards().size() - 1).getRank() >= 0) {
                            isBigger = true;
                        }
                    }

                    //finds a random valid card because there is one that is valid
                    if (isBigger) {
                        while (cardToSelect == null ||
                                (cardToSelect.getRank() <
                                        state.getPlayPilePalaceCards().
                                                get(state.getPlayPilePalaceCards().size() - 1).
                                                getRank() &&
                                        state.getPlayPilePalaceCards().
                                                get(state.getPlayPilePalaceCards().size() - 1).
                                                getRank() >= 0)) {
                            cardToSelect =
                                    state.getP2TopPalaceCards().
                                            get((int) (Math.random() * state.
                                                    getP2TopPalaceCards().size()));
                        }
                    }

                    //no card to be played so take the pile
                    else {
                        PalaceTakePileAction take = new PalaceTakePileAction(this);
                        Log.d("compPlayer", "took the pile");
                        this.game.sendAction(take);
                    }
                }
            }

            //they only have bottom cards left, all are valid to be played so pick a random one
            else if (state.getP2TopPalaceCards().isEmpty()&&state.getP2Hand().isEmpty()) {
                cardToSelect =
                        state.getP2BottomPalaceCards().
                                get((int) (Math.random() * state.getP2BottomPalaceCards().size()));
            }

            //the play pile is empty and they only have hand cards, pick one at random
            else {
                cardToSelect =
                        state.getP2Hand().get((int) (Math.random() * state.getP2Hand().size()));
            }

            //create and send the select card action with the selected  card
            selectCardAction = new PalaceSelectCardAction(this, cardToSelect,
                    state.getSelectedPalaceCards());
            this.game.sendAction(selectCardAction);

            //rerun selectCardAction if there are multiple of the same card
            if (!state.getP2Hand().isEmpty()) {
                for (int i = 0; i < state.getP2Hand().size(); i++) {
                    if (state.getP2Hand().get(i).getRank() == cardToSelect.getRank() &&
                            !cardToSelect.equals(state.getP2Hand().get(i))) {
                        selectCardAction = new PalaceSelectCardAction(this, state.getP2Hand().get(i),
                                state.getSelectedPalaceCards());
                        this.game.sendAction(selectCardAction);
                    }
                }
            }
            else if (state.getP2Hand().isEmpty() && !state.getP2TopPalaceCards().isEmpty()) {
                for (int i = 0; i < state.getP2Hand().size(); i++) {
                    if (state.getP2Hand().get(i).getRank() == cardToSelect.getRank() &&
                            !cardToSelect.equals(state.getP2TopPalaceCards().get(i))) {
                        selectCardAction = new PalaceSelectCardAction(this, state.getP2TopPalaceCards().get(i),
                                state.getSelectedPalaceCards());
                        this.game.sendAction(selectCardAction);
                    }
                }
            }

            //creates and sends the playCardAction
            PalacePlayCardAction playCardAction = new PalacePlayCardAction(this);
            this.game.sendAction(playCardAction);
        }


    }
}
