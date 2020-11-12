package com.example.palace.game.palace;

import android.util.Log;

import com.example.palace.game.GameComputerPlayer;
import com.example.palace.game.GamePlayer;
import com.example.palace.game.infoMsg.GameInfo;

/**
 * A dumb AI for Palace
 *
 * @author Jimi Hayes, Caroline Cavaliere, Nathaniel Pon, Chloe Gan
 */

public class PalaceComputerPlayer extends GameComputerPlayer {
    public PalaceComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method
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
        }
//        else
//        {
//            double rand = Math.random();
//            boolean isBigger = false;
//            for (int i = 0; i<state.getP2numCards(); i++){
//                if (state.getP2Hand().get(i).getRank()>state.getPlayPilePalaceCards().get(state.getPlayPilePalaceCards().size()-1).getRank()){
//                    isBigger = true;
//                }
//            }
//            if (isBigger == false){
//                rand = 0.1;
//            }
//            if(rand<0.2)//dumb AI randomly takes the pile
//            {
//                PalaceTakePileAction take = new PalaceTakePileAction(this);
//                Log.d("compPlayer", "took the pile");
//                this.game.sendAction(take);
//
//
//
//                if (this.playerNum == 0){
//                    selectCardAction = new PalaceSelectCardAction(this, state.getP1Hand().get(((int)Math.random()*state.getP1numCards())), state.getSelectedPalaceCards());
//                }
//                else if (this.playerNum == 1){
//                    selectCardAction = new PalaceSelectCardAction(this, state.getP2Hand().get(((int)Math.random()*state.getP2numCards())), state.getSelectedPalaceCards());
//                }
//                else if (this.playerNum == 2){
//                    selectCardAction = new PalaceSelectCardAction(this, state.getP3Hand().get(((int)Math.random()*state.getP3numCards())), state.getSelectedPalaceCards());
//                }
//                else if (this.playerNum == 3){
//                    selectCardAction = new PalaceSelectCardAction(this, state.getP4Hand().get(((int)Math.random()*state.getP4numCards())), state.getSelectedPalaceCards());
//                }
//                else {
//                    return;
//                }
//                if (!(state.getPlayPilePalaceCards().isEmpty())) {
//                    if (state.getCardToBeSelected().getRank() > state.getPlayPilePalaceCards().get(state.getPlayPilePalaceCards().size() - 1).getRank()) {
//                        if (this.playerNum == 0) {
//                            selectCardAction = new PalaceSelectCardAction(this, state.getP1Hand().get(((int) Math.random() * state.getP1numCards())), state.getSelectedPalaceCards());
//
//                        } else if (this.playerNum == 1) {
//                            selectCardAction = new PalaceSelectCardAction(this, state.getP2Hand().get(((int) Math.random() * state.getP2numCards())), state.getSelectedPalaceCards());
//                        } else if (this.playerNum == 2) {
//                            selectCardAction = new PalaceSelectCardAction(this, state.getP3Hand().get(((int) Math.random() * state.getP3numCards())), state.getSelectedPalaceCards());
//                        } else if (this.playerNum == 3) {
//                            selectCardAction = new PalaceSelectCardAction(this, state.getP4Hand().get(((int) Math.random() * state.getP4numCards())), state.getSelectedPalaceCards());
//                        } else {
//                            return;
//                        }
//                    }
//                }
//                this.game.sendAction(selectCardAction);
//                PalacePlayCardAction play = new PalacePlayCardAction(this);
//                this.game.sendAction(play);
//
//
//            }
//
//            else {
//                if (this.playerNum == 0){
//                    selectCardAction = new PalaceSelectCardAction(this, state.getP1Hand().get(((int)Math.random()*state.getP1numCards())), state.getSelectedPalaceCards());
//                }
//                else if (this.playerNum == 1){
//                    selectCardAction = new PalaceSelectCardAction(this, state.getP2Hand().get(((int)Math.random()*state.getP2numCards())), state.getSelectedPalaceCards());
//                }
//                else if (this.playerNum == 2){
//                    selectCardAction = new PalaceSelectCardAction(this, state.getP3Hand().get(((int)Math.random()*state.getP3numCards())), state.getSelectedPalaceCards());
//                }
//                else if (this.playerNum == 3){
//                    selectCardAction = new PalaceSelectCardAction(this, state.getP4Hand().get(((int)Math.random()*state.getP4numCards())), state.getSelectedPalaceCards());
//                }
//                else {
//                    return;
//                }
//                while (state.getCardToBeSelected().getRank()<state.getPlayPilePalaceCards().get(state.getPlayPilePalaceCards().size()-1).getRank()) {
//                    if (this.playerNum == 0) {
//                        selectCardAction = new PalaceSelectCardAction(this, state.getP1Hand().get(((int) Math.random() * state.getP1numCards())), state.getSelectedPalaceCards());
//
//                    } else if (this.playerNum == 1) {
//                        selectCardAction = new PalaceSelectCardAction(this, state.getP2Hand().get(((int) Math.random() * state.getP2numCards())), state.getSelectedPalaceCards());
//                    } else if (this.playerNum == 2) {
//                        selectCardAction = new PalaceSelectCardAction(this, state.getP3Hand().get(((int) Math.random() * state.getP3numCards())), state.getSelectedPalaceCards());
//                    } else if (this.playerNum == 3) {
//                        selectCardAction = new PalaceSelectCardAction(this, state.getP4Hand().get(((int) Math.random() * state.getP4numCards())), state.getSelectedPalaceCards());
//                    } else {
//                        return;
//                    }
//                }
//
//                this.game.sendAction(selectCardAction);
//                PalacePlayCardAction play = new PalacePlayCardAction(this);
//                this.game.sendAction(play);
//            }
//        }
        else {
            boolean isBigger = false;
            if (!(state.getP2Hand().isEmpty()) && !state.getPlayPilePalaceCards().isEmpty()) {
                for (int i = 0; i < state.getP2Hand().size(); i++) {
                    if (state.getP2Hand().get(i).getRank() >
                            state.getPlayPilePalaceCards().
                                    get(state.getPlayPilePalaceCards().size() - 1).getRank()) {
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
                while (cardToSelect == null || cardToSelect.getRank() <
                        state.getPlayPilePalaceCards().
                                get(state.getPlayPilePalaceCards().size() - 1).getRank()) {
                    cardToSelect = state.getP2Hand().
                            get((int) (Math.random() * state.getP2Hand().size()));
                }
            } else if (state.getP2Hand().isEmpty()) {
                if (state.getPlayPilePalaceCards().isEmpty()) {
                    cardToSelect = state.getP2TopPalaceCards().
                            get((int) (Math.random() * state.getP2TopPalaceCards().size()));
                } else {
                    for (int i = 0; i < state.getP2TopPalaceCards().size(); i++) {
                        if (state.getP2TopPalaceCards().
                                get(i).getRank() > state.getPlayPilePalaceCards().
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
                            cardToSelect = state.getP2TopPalaceCards().
                                    get((int) (Math.random() * state.getP2TopPalaceCards().size()));
                        }
                    } else {
                        PalaceTakePileAction take = new PalaceTakePileAction(this);
                        Log.d("compPlayer", "took the pile");
                        this.game.sendAction(take);
                    }
                }
            } else if (state.getP2TopPalaceCards().isEmpty()) {
                cardToSelect = state.getP2BottomPalaceCards().
                        get((int) (Math.random() * state.getP2TopPalaceCards().size()));
            } else {
                cardToSelect = state.getP2Hand().
                        get((int) (Math.random() * state.getP2Hand().size()));
            }
            selectCardAction = new PalaceSelectCardAction(this, cardToSelect,
                    state.getSelectedPalaceCards());
            this.game.sendAction(selectCardAction);
            PalacePlayCardAction playCardAction = new PalacePlayCardAction(this);
            this.game.sendAction(playCardAction);
        }

    }
}
