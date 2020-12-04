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
 * might be a high card that you can never beat lol.
 *
 * @author Jimi Hayes, Caroline Cavaliere, Nathaniel Pon, Chloe Gan
 */

public class PalaceComputerPlayer extends GameComputerPlayer {
    public PalaceComputerPlayer(String name) {
        super(name);
    }


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
            boolean isBigger = false;
            if (!(state.getP2Hand().isEmpty()) && !state.getPlayPilePalaceCards().isEmpty()) {
                for (int i = 0; i < state.getP2Hand().size(); i++) {
                    if (state.getP2Hand().get(i).getRank() > state.getPlayPilePalaceCards().
                            get(state.getPlayPilePalaceCards().size() - 1).getRank()) {
                        isBigger = true;
                    }
                }
            }
            if (isBigger == false && !(state.getP2Hand().isEmpty())) {
                PalaceTakePileAction take = new PalaceTakePileAction(this);
                Log.d("compPlayer", "took the pile");
                this.game.sendAction(take);
            }
            PalaceCard cardToSelect = null;
            if (!(state.getPlayPilePalaceCards().isEmpty()) && (!(state.getP2Hand().isEmpty()))) {
                // while they haven't selected a card or the card they selected is less than the
                while (cardToSelect == null ||
                        cardToSelect.getRank() <
                                (state.getPlayPilePalaceCards().
                                        get(state.getPlayPilePalaceCards().size() - 1).getRank()) &&
                                state.getPlayPilePalaceCards().
                                        get(state.getPlayPilePalaceCards().size() - 1).getRank() >=0) {
                    cardToSelect =
                            state.getP2Hand().get((int) (Math.random() * state.getP2Hand().size()));
                }
            } else if (state.getP2Hand().isEmpty() && (!(state.getP2TopPalaceCards().isEmpty()))) {
                if (state.getPlayPilePalaceCards().isEmpty()) {
                    cardToSelect =
                            state.getP2TopPalaceCards().
                                    get((int) (Math.random() * state.getP2TopPalaceCards().size()));
                } else {
                    for (int i = 0; i < state.getP2TopPalaceCards().size(); i++) {
                        if (state.getP2TopPalaceCards().get(i).getRank() >
                                state.getPlayPilePalaceCards().
                                        get(state.getPlayPilePalaceCards().size() - 1).getRank() &&
                                state.getPlayPilePalaceCards().
                                        get(state.getPlayPilePalaceCards().size() - 1).getRank() >=0) {
                            isBigger = true;
                        }
                    }
                    if (isBigger) {
                        while (cardToSelect == null ||
                                cardToSelect.getRank() <
                                        state.getPlayPilePalaceCards().
                                                get(state.getPlayPilePalaceCards().size() - 1).
                                                getRank() &&
                                        state.getPlayPilePalaceCards().
                                                get(state.getPlayPilePalaceCards().size() - 1).
                                                getRank() >=0) {
                            cardToSelect =
                                    state.getP2TopPalaceCards().
                                            get((int) (Math.random() * state.
                                                    getP2TopPalaceCards().size()));
                        }
                    } else {
                        PalaceTakePileAction take = new PalaceTakePileAction(this);
                        Log.d("compPlayer", "took the pile");
                        this.game.sendAction(take);
                    }
                }
            } else if (state.getP2TopPalaceCards().isEmpty() ) {
                cardToSelect =
                        state.getP2BottomPalaceCards().
                                get((int) (Math.random() * state.getP2BottomPalaceCards().size()));
            } else {
                cardToSelect =
                        state.getP2Hand().get((int) (Math.random() * state.getP2Hand().size()));
            }
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
            PalacePlayCardAction playCardAction = new PalacePlayCardAction(this);
            this.game.sendAction(playCardAction);
        }


    }
}
