package com.example.palace.game.palace;

import android.util.Log;

import com.example.palace.game.GameComputerPlayer;
import com.example.palace.game.infoMsg.GameInfo;

import java.util.ArrayList;

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
        } else {
            boolean isBigger = false;
            if (!(state.getP2Hand().isEmpty()) && !state.getPlayPilePalaceCards().isEmpty()) {
                for (int i = 0; i < state.getP2Hand().size(); i++) {
                    if (state.getP2Hand().get(i).getRank() >= state.getPlayPilePalaceCards().get(state.getPlayPilePalaceCards().size() - 1).getRank()) {
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
                while (cardToSelect == null) {
                    cardToSelect =
                            state.getP2Hand().
                                    get(findMinPlayable(state.getP2Hand(), state.getPlayPilePalaceCards().get(state.getPlayPilePalaceCards().size()-1)));
                }
            } else if (state.getP2Hand().isEmpty() && (!(state.getP2TopPalaceCards().isEmpty()))) {
                if (state.getPlayPilePalaceCards().isEmpty()) {
                    cardToSelect =
                            state.getP2TopPalaceCards().
                                    get(findMinPlayable(state.getP2TopPalaceCards(), (new PalaceCard(1,2))));
                } else {
                    for (int i = 0; i < state.getP2TopPalaceCards().size(); i++) {
                        if (state.getP2TopPalaceCards().get(i).getRank() >
                                state.getPlayPilePalaceCards().
                                        get(state.getPlayPilePalaceCards().size() - 1).getRank()) {
                            isBigger = true;
                        }
                    }
                    if (isBigger) {
                        while (cardToSelect == null) {
                            cardToSelect =
                                    state.getP2TopPalaceCards().
                                            get(findMinPlayable(state.getP2TopPalaceCards(), state.getPlayPilePalaceCards().get(state.getPlayPilePalaceCards().size()-1)));
                        }
                    } else {
                        PalaceTakePileAction take = new PalaceTakePileAction(this);
                        Log.d("compPlayer", "took the pile");
                        this.game.sendAction(take);
                    }
                }
            } else if (state.getP2TopPalaceCards().isEmpty()&&state.getP2Hand().isEmpty()) {
                if (!state.getPlayPilePalaceCards().isEmpty()) {
                    cardToSelect =
                            state.getP2BottomPalaceCards().
                                    get(findMinPlayable(state.getP2BottomPalaceCards(), state.getPlayPilePalaceCards().get(state.getPlayPilePalaceCards().size() - 1)));
                }
                else {
                    cardToSelect =
                            state.getP2BottomPalaceCards().
                                    get(findMinPlayable(state.getP2BottomPalaceCards(), (new PalaceCard(1, 2))));
                }
            } else {
                cardToSelect =
                        state.getP2Hand().
                                get(findMinPlayable(state.getP2Hand(), (new PalaceCard(1,2))));

            }
            if (cardToSelect.getRank() == 10) {
            }
            selectCardAction = new PalaceSelectCardAction(this, cardToSelect,
                    state.getSelectedPalaceCards());
            this.game.sendAction(selectCardAction);

            //rerun selectCardAction if there are multiple of the same card
            boolean selectedAllCards = false;
            int iterations = 0;
            int playerPile = 0;
            ArrayList<PalaceCard> temp;
            if ((!(state.getP2Hand().isEmpty()))
                    &&cardToSelect.getRank()!=10
                        &&cardToSelect.getRank()!=2) {
                for (int i = 0; i < state.getP2Hand().size(); i++) {
                    if (state.getP2Hand().get(i).getRank() == cardToSelect.getRank() &&
                            !cardToSelect.equals(state.getP2Hand().get(i))) {
                        selectCardAction = new PalaceSelectCardAction(this, state.getP2Hand().get(i),
                                state.getSelectedPalaceCards());
                        this.game.sendAction(selectCardAction);
                    }
                }
            }

            PalacePlayCardAction playCardAction = new PalacePlayCardAction(this);
            this.game.sendAction(playCardAction);
        }

    }

    private int findMinPlayable(ArrayList<PalaceCard> selectFrom, PalaceCard playPileTop){
        int indexOfCurrMax = 0;
        for (int i=0; i<selectFrom.size(); i++){
            if (selectFrom.get(i).getRank()<selectFrom.get(indexOfCurrMax).getRank()
                    &&selectFrom.get(i).getRank()>=playPileTop.getRank()){
                indexOfCurrMax = i;
            }
        }
        return indexOfCurrMax;
    }
}
