package com.example.palace.game.palace;

import android.util.Log;

import com.example.palace.game.GameComputerPlayer;
import com.example.palace.game.infoMsg.GameInfo;

public class PalaceSmartComputerPlayer extends GameComputerPlayer {
    public PalaceSmartComputerPlayer(String name){super(name);}
    /**
     * callback method
     *
     * CAVEATS: None
     *
     * @param info the information (containing the game's state)
     *
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
        }
        else {
            boolean isBigger = false;
            if (!(state.getP2Hand().isEmpty()) && !state.getPlayPilePalaceCards().isEmpty()) {
                for (int i = 0; i < state.getP2Hand().size(); i++) {
                    if (state.getP2Hand().get(i).getRank() > state.getPlayPilePalaceCards().get(state.getPlayPilePalaceCards().size() - 1).getRank()) {
                        isBigger = true;
                    }
                }
            }
            if (isBigger == false) {
                PalaceTakePileAction take = new PalaceTakePileAction(this);
                Log.d("compPlayer", "took the pile");
                this.game.sendAction(take);
            }
            PalaceCard cardToSelect = null;
            if (!(state.getPlayPilePalaceCards().isEmpty())) {
                while (cardToSelect == null ||
                        cardToSelect.getRank() <
                                state.getPlayPilePalaceCards().
                                        get(state.getPlayPilePalaceCards().size() - 1).getRank()) {
                    cardToSelect =
                            state.getP2Hand().get((int) (Math.random() * state.getP2Hand().size()));
                }
            } else if (state.getP2Hand().isEmpty()) {
                if (state.getPlayPilePalaceCards().isEmpty()) {
                    cardToSelect =
                            state.getP2TopPalaceCards().
                                    get((int) (Math.random() * state.getP2TopPalaceCards().size()));
                } else {
                    for (int i = 0; i < state.getP2TopPalaceCards().size(); i++) {
                        if (state.getP2TopPalaceCards().get(i).getRank() >
                                state.getPlayPilePalaceCards().
                                        get(state.getPlayPilePalaceCards().size() - 1).getRank()) {
                            isBigger = true;
                        }
                    }
                    if (isBigger) {
                        while (cardToSelect == null ||
                                cardToSelect.getRank() <
                                        state.getPlayPilePalaceCards().
                                                get(state.getPlayPilePalaceCards().size() - 1).
                                                getRank()) {
                            cardToSelect =
                                    state.getP2TopPalaceCards().
                                            get((int) (Math.random()*state.
                                                    getP2TopPalaceCards().size()));
                        }
                    } else {
                        PalaceTakePileAction take = new PalaceTakePileAction(this);
                        Log.d("compPlayer", "took the pile");
                        this.game.sendAction(take);
                    }
                }
            } else if (state.getP2TopPalaceCards().isEmpty()) {
                cardToSelect =
                        state.getP2BottomPalaceCards().
                                get((int) (Math.random() * state.getP2TopPalaceCards().size()));
            } else {
                cardToSelect =
                        state.getP2Hand().get((int) (Math.random() * state.getP2Hand().size()));
            }
            selectCardAction = new PalaceSelectCardAction(this, cardToSelect,
                    state.getSelectedPalaceCards());
            this.game.sendAction(selectCardAction);
            PalacePlayCardAction playCardAction = new PalacePlayCardAction(this);
            this.game.sendAction(playCardAction);
        }

    }
}
