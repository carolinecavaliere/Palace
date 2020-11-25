package com.example.palace.game.palace;

import android.util.Log;

import com.example.palace.game.GameComputerPlayer;
import com.example.palace.game.infoMsg.GameInfo;
import com.example.palace.game.infoMsg.IllegalMoveInfo;
import com.example.palace.game.infoMsg.NotYourTurnInfo;

import java.nio.file.ClosedFileSystemException;
import java.util.ArrayList;

public class PalaceSmartComputerPlayer extends GameComputerPlayer {
    public PalaceSmartComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method
     * Note: temporarily, computer player will be P2
     * <p>
     * CAVEATS: None
     *
     *
     * 1. Computer player is not possibly sending an action?
     * 2. The smart AI might want to do ALL the actions. --> They need to send exactly one action
     * @param info the information (containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        if (!(info instanceof PalaceGameState)) {
            if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
                System.out.println("Reached Here");
            }
            return;
        }
        PalaceGameState state = new PalaceGameState((PalaceGameState) info);
        PalaceSelectCardAction selectCardAction;
        if (state.getTurn() != this.playerNum) {
            return;
        } else {
            boolean isBigger = false;
            //check to see if computer player hand has cards and the play pile has cards
            if (!(state.getP2Hand().isEmpty()) && !state.getPlayPilePalaceCards().isEmpty()) {
                //determine if a card in the hand can beat the play pile card
                for (int i = 0; i < state.getP2Hand().size(); i++) {
                    if (state.getP2Hand().get(i).getRank() >= state.getPlayPilePalaceCards().get(state.getPlayPilePalaceCards().size() - 1).getRank()) {
                        System.out.println("isBigger is true");
                        isBigger = true;
                    }
                }
                //if computer player canNOT beat the play pile card, take the pile
                if (isBigger == false) {
                    PalaceTakePileAction take = new PalaceTakePileAction(this);
                    Log.d("compPlayer", "took the pile");
                    this.game.sendAction(take);
                }
            }
            else if ((!(state.getP2TopPalaceCards().isEmpty()))  && (!(state.getPlayPilePalaceCards().isEmpty()))){
                for (int i = 0; i < state.getP2TopPalaceCards().size(); i++) {
                    if (state.getP2TopPalaceCards().get(i).getRank() >= state.getPlayPilePalaceCards().get(state.getPlayPilePalaceCards().size() - 1).getRank()) {
                        System.out.println("isBigger is true");
                        isBigger = true;
                    }
                }
                //if computer player canNOT beat the play pile card, take the pile
                if (isBigger == false && !(state.getP2TopPalaceCards().isEmpty())) {
                    PalaceTakePileAction take = new PalaceTakePileAction(this);
                    Log.d("compPlayer", "took the pile");
                    this.game.sendAction(take);
                }
            }


            //if the computer player CAN beat the play pile card...
            PalaceCard cardToSelect = null;
            //check if there are cards in the play pile and the hand
            if (!(state.getPlayPilePalaceCards().isEmpty()) && (!(state.getP2Hand().isEmpty()))) {
                cardToSelect =
                        state.getP2Hand().
                                get(findMinPlayable(state.getP2Hand(), state.getPlayPilePalaceCards()
                                        .get(state.getPlayPilePalaceCards().size() - 1)));
                System.out.println("compPlayer selected from the hand (w/ play pile) " + cardToSelect);
            }
            //check the top palace cards
            else if (state.getP2Hand().isEmpty() && (!(state.getP2TopPalaceCards().isEmpty()))) {
                //when play pile is empty
                if (state.getPlayPilePalaceCards().isEmpty()) {
                    cardToSelect =
                            state.getP2TopPalaceCards().
                                    get(findMinPlayable(state.getP2TopPalaceCards(), (new PalaceCard(1, 2))));
                    System.out.println("compPlayer selected from the top (w/o play pile) " + cardToSelect);
                }
                //when play pile is NOT empty
                else {
                    for (int i = 0; i < state.getP2TopPalaceCards().size(); i++) {
                        if (state.getP2TopPalaceCards().get(i).getRank() >
                                state.getPlayPilePalaceCards().
                                        get(state.getPlayPilePalaceCards().size() - 1).getRank()) {
                            isBigger = true;
                        }
                    }
                    //select the card that can beat the play pile card
                    if (isBigger) {
                        cardToSelect =
                                state.getP2TopPalaceCards().
                                        get(findMinPlayable(state.getP2TopPalaceCards(), state.getPlayPilePalaceCards().get(state.getPlayPilePalaceCards().size() - 1)));
                        System.out.println("compPlayer selected from the top " + cardToSelect);
                    }
                    //take the pile if no card can beat the play pile card
                    else {
                        PalaceTakePileAction take = new PalaceTakePileAction(this);
                        Log.d("compPlayer", "took the pile");
                        this.game.sendAction(take);
                    }
                }
            }
            //check the bottom palace cards
            else if (state.getP2TopPalaceCards().isEmpty() && state.getP2Hand().isEmpty()) {
                //when the play pile is NOT empty
                if (!state.getPlayPilePalaceCards().isEmpty()) {
                    cardToSelect =
                            state.getP2BottomPalaceCards().
                                    get(findMinPlayable(state.getP2BottomPalaceCards(),
                                            state.getPlayPilePalaceCards().
                                                    get(state.getPlayPilePalaceCards().size() - 1)));
                    System.out.println("compPlayer selected " + cardToSelect);
                }
                //when the play pile is empty
                else {
                    cardToSelect =
                            state.getP2BottomPalaceCards().
                                    get(findMinPlayable(state.getP2BottomPalaceCards(), (new PalaceCard(1, 2))));
                    System.out.println("compPlayer selected " + cardToSelect);
                }
            }
            //emergency broad else statement: automatically tries the hand cards
            else {
                cardToSelect =
                        state.getP2Hand().
                                get(findMinPlayable(state.getP2Hand(), (new PalaceCard(1, 2))));
                System.out.println("compPlayer selected (from else statement) " + cardToSelect);
            }
            //special exception for 10 because of it's special properties
            if (cardToSelect.getRank() == 10) {
                //select and play the ten to clear the pile
                selectCardAction = new PalaceSelectCardAction(this, cardToSelect,
                        state.getSelectedPalaceCards());
                this.game.sendAction(selectCardAction);
                PalacePlayCardAction playCardAction = new PalacePlayCardAction(this);
                this.game.sendAction(playCardAction);
                System.out.println("compPlayer selected (a 10) " + cardToSelect);

                //immediately select the next card to be played and continue through the method
                if (!state.getP2Hand().isEmpty()) {
                    PalaceCard temp = new PalaceCard(1,2);
                    cardToSelect = state.getP2Hand().
                            get(findMinPlayable(state.getP2Hand(), temp));
                }
                else if (state.getP2Hand().isEmpty() && !state.getP2TopPalaceCards().isEmpty()) {
                    PalaceCard temp = new PalaceCard(1,2);
                    cardToSelect = state.getP2TopPalaceCards().
                            get(findMinPlayable(state.getP2Hand(), temp));
                }
                else {
                    PalaceCard temp = new PalaceCard(1,2);
                    cardToSelect = state.getP2BottomPalaceCards().
                            get(findMinPlayable(state.getP2Hand(), temp));
                }

            }

            //selectCardAction with the currently selected card
            selectCardAction = new PalaceSelectCardAction(this, cardToSelect,
                    state.getSelectedPalaceCards());
            this.game.sendAction(selectCardAction);

            //rerun selectCardAction if there are multiple of the same card
            ArrayList<PalaceCard> temp;
            //loop through the hand
            if ((!(state.getP2Hand().isEmpty()))
                    && cardToSelect.getRank() != 10
                    && cardToSelect.getRank() != 2) {
                for (int i = 0; i < state.getP2Hand().size(); i++) {
                    if (state.getP2Hand().get(i).getRank() == cardToSelect.getRank() &&
                            !cardToSelect.equals(state.getP2Hand().get(i))) {
                        selectCardAction = new PalaceSelectCardAction(this, state.getP2Hand().get(i),
                                state.getSelectedPalaceCards());
                        this.game.sendAction(selectCardAction);
                    }
                }
            }

            //play the card
            PalacePlayCardAction playCardAction = new PalacePlayCardAction(this);
            this.game.sendAction(playCardAction);
        }
    }

    /**
     * finds the minimum value card that's greater than the last card played in the play pile
     *
     * Why is it picking a card that's not sufficient to play?
     * Test: don't shuffle cards
     * @param selectFrom
     * @param playPileTop
     * @return
     */
    private int findMinPlayable(ArrayList<PalaceCard> selectFrom, PalaceCard playPileTop) {
        int indexOfCurrMax = 0;
        while(selectFrom.get(indexOfCurrMax).getRank()<playPileTop.getRank()){
            indexOfCurrMax++;
        }
        Log.d("compPlayer", "running min playable");
        //evaluate all the cards in the given ArrayList, finding the min value card
        for (int i = 0; i < selectFrom.size(); i++) {
            if (selectFrom.get(i).getRank() < selectFrom.get(indexOfCurrMax).getRank()
                    && selectFrom.get(i).getRank() >= playPileTop.getRank()) {
                indexOfCurrMax = i;
            }
        }
        Log.d("compPlayer", "completed min playable");
        return indexOfCurrMax;
    }
}
