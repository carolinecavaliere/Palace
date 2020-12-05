package edu.up.palace.palace;

import android.util.Log;

import edu.up.palace.game.GameComputerPlayer;
import edu.up.palace.game.infoMsg.GameInfo;
import edu.up.palace.game.infoMsg.IllegalMoveInfo;
import edu.up.palace.game.infoMsg.NotYourTurnInfo;

import java.util.ArrayList;

public class PalaceSmartComputerPlayer extends GameComputerPlayer {
    public PalaceSmartComputerPlayer(String name) {
        super(name);
    }

    /**
     * Don't think overriding does anything but I included it because it seemed to make it stable.
     * We can use this method because we have direct access to the computer's thread.
     * @param milliseconds
     */
    @Override
    protected void sleep(int milliseconds) {
        super.sleep(milliseconds);
    }

    /**
     * callback method
     * Note: computer player can only be P2
     * <p>
     * CAVEATS: None
     * <p>
     * <p>
     *
     * Deficiencies: As it stands currently, the Smart AI (and sometimes the dumb AI) will, for
     * no reason, stop working. The program for the class will simply stop running, but will return
     * no errors or crashes. (For reference, if it happens, check the Run logs.) It's nearly
     * impossible to fix this bug with our current knowledge of Android Studio, since all of the
     * fixes that we tried did not work. However, from our experience, this rarely occurs.
     *
     * @param info the information (containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        boolean isMax = true;
        boolean multMax = false;
        boolean isTen = false;
        boolean isTwo = false;
        boolean hasBeenSelected = false;
        PalaceCard cardToSelect = null;
        if (!(info instanceof PalaceGameState)) {
            if (info instanceof IllegalMoveInfo) {
                System.out.println("Illegal Move");
                return;
            }
            if (info instanceof NotYourTurnInfo) {
                System.out.println("Not your turn");
                return;
            }
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
                    if (state.getP2Hand().get(i).getRank() >=
                            state.getPlayPilePalaceCards().
                                    get(state.getPlayPilePalaceCards().size() - 1).getRank()) {
                        System.out.println("isBigger is true");
                        isBigger = true;
                    }
                }
                //if computer player canNOT beat the play pile card, check for 10's or 2's
                if (!isBigger) {
                    for(int i = 0; i < state.getP2Hand().size(); i++) {
                        if (state.getP2Hand().get(i).getRank() == 2) {
                            isTwo = true;
                            hasBeenSelected = true;
                            cardToSelect = state.getP2Hand().get(i);
                            break;
                        }
                        else if (state.getP2Hand().get(i).getRank() == 10) {
                            isTen = true;
                            hasBeenSelected = true;
                            cardToSelect = state.getP2Hand().get(i);
                            break;
                        }
                    }
                    //take pile if no 10 or 2
                    if (!isTen && !isTwo) {
                        PalaceTakePileAction take = new PalaceTakePileAction(this);
                        Log.d("compPlayer", "took the pile");
                        this.game.sendAction(take);
                    }
                }
                //for the top cards
            } else if ((!(state.getP2TopPalaceCards().isEmpty())) &&
                    (!(state.getPlayPilePalaceCards().isEmpty()))) {
                //determine if a card in the top can beat the play pile card
                for (int i = 0; i < state.getP2TopPalaceCards().size(); i++) {
                    if (state.getP2TopPalaceCards().get(i).getRank() >=
                            state.getPlayPilePalaceCards().
                                    get(state.getPlayPilePalaceCards().size() - 1).getRank()) {
                        System.out.println("isBigger is true");
                        isBigger = true;
                    }
                }
                //if computer player canNOT beat the play pile card, check for 10's or 2's
                if (!isBigger) {
                    for (int i = 0; i < state.getP2TopPalaceCards().size(); i++) {
                        if (state.getP2TopPalaceCards().get(i).getRank() == 2) {
                            isTwo = true;
                            hasBeenSelected = true;
                            cardToSelect = state.getP2TopPalaceCards().get(i);
                            break;
                        } else if (state.getP2TopPalaceCards().get(i).getRank() == 10) {
                            isTen = true;
                            hasBeenSelected = true;
                            cardToSelect = state.getP2TopPalaceCards().get(i);
                            break;
                        }
                    }
                    //if isBigger is false and has no 10's or 2's
                    if (!isTen && !isTwo) {
                        PalaceTakePileAction take = new PalaceTakePileAction(this);
                        Log.d("compPlayer", "took the pile");
                        this.game.sendAction(take);
                    }
                }
            }
            //if the computer player CAN beat the play pile card...
            //check if there are cards in the play pile and the hand
            if (!isTen && !isTwo) {
                if (state.getP2Hand().size() != 0) {
                    //when play pile is NOT empty
                    if (!state.getPlayPilePalaceCards().isEmpty()) {
                        hasBeenSelected = true;
                        cardToSelect =
                                state.getP2Hand().
                                        get(findMinPlayable(state.getP2Hand(),
                                                state.getPlayPilePalaceCards()
                                                .get(state.getPlayPilePalaceCards().size() - 1)));
                        System.out.println("compPlayer selected from the hand (w/ play pile) " +
                                cardToSelect);
                    }
                    //when play pile is empty
                    else {
                        hasBeenSelected = true;
                        cardToSelect =
                                state.getP2Hand().
                                        get(findMinPlayable(state.getP2Hand(), null));
                        System.out.println("compPlayer selected from the hand (w/o play pile) " +
                                cardToSelect);
                    }
                }
                //check the top palace cards
                else if (state.getP2Hand().isEmpty() && !(state.getP2TopPalaceCards().isEmpty())) {
                    //when play pile is empty
                    if (state.getPlayPilePalaceCards().isEmpty()) {
                        hasBeenSelected = true;
                        cardToSelect =
                                state.getP2TopPalaceCards().
                                        get(findMinPlayable(state.getP2TopPalaceCards(),
                                                null));
                        System.out.println("compPlayer selected from the top (w/o play pile) " +
                                cardToSelect);
                    }
                    //when play pile is NOT empty
                    else {
                        for (int i = 0; i < state.getP2TopPalaceCards().size(); i++) {
                            if (state.getP2TopPalaceCards().get(i).getRank() >
                                    state.getPlayPilePalaceCards().
                                            get(state.getPlayPilePalaceCards().size() - 1).
                                                getRank()) {
                                isBigger = true;
                            }
                        }
                        //select the card that can beat the play pile card
                        if (isBigger) {
                            hasBeenSelected = true;
                            cardToSelect = state.getP2TopPalaceCards().
                                    get(findMinPlayable(state.getP2TopPalaceCards(),
                                            state.getPlayPilePalaceCards().
                                                    get(state.getPlayPilePalaceCards().
                                                            size() - 1)));

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
                        hasBeenSelected = true;
                        cardToSelect =
                                state.getP2BottomPalaceCards().
                                        get(findMinPlayable(state.getP2BottomPalaceCards(),
                                                state.getPlayPilePalaceCards().
                                                        get(state.getPlayPilePalaceCards().
                                                                size() - 1)));
                        System.out.println("compPlayer selected from Bottom w/ Pile" +
                                cardToSelect);
                    }
                    //when the play pile is empty
                    else {
                        hasBeenSelected = true;
                        cardToSelect =
                                state.getP2BottomPalaceCards().
                                        get(findMinPlayable(state.getP2BottomPalaceCards(),
                                                null));
                        System.out.println("compPlayer selected from Bottom w/o Pile" +
                                cardToSelect);
                    }
                }
                //emergency broad else statement: automatically tries the hand cards
                else {
                    hasBeenSelected = true;
                    cardToSelect =
                            state.getP2Hand().
                                    get(findMinPlayable(state.getP2Hand(), null));
                    System.out.println("compPlayer selected (from else statement) " + cardToSelect);
                }
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
                //for the hand cards
                if (!state.getP2Hand().isEmpty()) {
                    PalaceCard temp = new PalaceCard(1, 2);
                    cardToSelect = state.getP2Hand().
                            get(findMinPlayable(state.getP2Hand(), temp));
                }
                //for the top cards
                else if (state.getP2Hand().isEmpty() && !state.getP2TopPalaceCards().isEmpty()) {
                    PalaceCard temp = new PalaceCard(1, 2);
                    cardToSelect = state.getP2TopPalaceCards().
                            get(findMinPlayable(state.getP2Hand(), temp));
                }
                //for the bottom cards
                else {
                    PalaceCard temp = new PalaceCard(1, 2);
                    cardToSelect = state.getP2BottomPalaceCards().
                            get(findMinPlayable(state.getP2Hand(), temp));
                }

            }

            //selectCardAction with the currently selected card
            System.out.println("Selected Card Action has been sent");
            selectCardAction = new PalaceSelectCardAction(this, cardToSelect,
                    state.getSelectedPalaceCards());
            this.game.sendAction(selectCardAction);

            //determine if the selected card is the biggest card in hand
            for (int i = 0; i < state.getP2Hand().size() - 1; i++) {
                if (state.getP2Hand().get(i).getRank() > cardToSelect.getRank()) {
                    isMax = false;
                }
            }
            //determine if there are more than one biggest card
            for (int i = 0; i < state.getP2Hand().size() - 1; i++) {
                if (state.getP2Hand().get(i).getRank() == cardToSelect.getRank()) {
                    multMax = true;
                }
            }
            //rerun selectCardAction if there are multiple of the same card
            ArrayList<PalaceCard> temp;
            //loop through the hand to find multiple cards of the same rank
            if ((!(state.getP2Hand().isEmpty()))
                    && cardToSelect.getRank() != 10
                    && cardToSelect.getRank() != 2) {
                if (!state.getP2Hand().isEmpty()) {
                    for (int i = 0; i < state.getP2Hand().size(); i++) {
                        if (isMax && multMax) {
                            break;
                        }
                        if (state.getP2Hand().get(i).getRank() == cardToSelect.getRank() &&
                                !cardToSelect.equals(state.getP2Hand().get(i))) {
                            selectCardAction = new PalaceSelectCardAction(this,
                                    state.getP2Hand().get(i),
                                    state.getSelectedPalaceCards());
                            this.game.sendAction(selectCardAction);
                        }
                    }
                }

            }

            sleep(1000);
            //only run if a card has been selected
            if (hasBeenSelected) {
                //play the card
                System.out.println("Play Card Action has been sent");
                PalacePlayCardAction playCardAction = new PalacePlayCardAction(this);
                this.game.sendAction(playCardAction);
            }

        }
    }

    /**
     * finds the minimum value card that's greater than the last card played in the play pile
     * <p>
     * Why is it picking a card that's not sufficient to play?
     * Test: don't shuffle cards
     *
     * @param selectFrom
     * @param playPileTop
     * @return
     */
    private int findMinPlayable(ArrayList<PalaceCard> selectFrom, PalaceCard playPileTop) {
        int indexOfCurrentMin = 0;
        if (playPileTop != null) {
            if (!selectFrom.isEmpty()) {
                while (selectFrom.get(indexOfCurrentMin).getRank() < playPileTop.getRank() &&
                        (selectFrom.get(indexOfCurrentMin).getRank() != 2 &&
                                selectFrom.get(indexOfCurrentMin).getRank() != 10)) {
                    //if statement to prevent out of bounds error
                    if (indexOfCurrentMin < selectFrom.size() - 1) {
                        indexOfCurrentMin++;
                    }
                }
            }
            Log.d("compPlayer", "running min playable");
            //evaluate all the cards in the given ArrayList, finding the min value card
            for (int i = 0; i < selectFrom.size(); i++) {
                if (selectFrom.get(i).getRank() < selectFrom.get(indexOfCurrentMin).getRank()
                        && selectFrom.get(i).getRank() >= playPileTop.getRank() &&
                        (selectFrom.get(indexOfCurrentMin).getRank() != 2 &&
                                selectFrom.get(indexOfCurrentMin).getRank() != 10)) {
                    indexOfCurrentMin = i;
                }
            }
            Log.d("compPlayer", "completed min playable");
            return indexOfCurrentMin;
        }
        else {
            int minRank = 14;
            for (int i = 0; i < selectFrom.size(); i++) {
                if (selectFrom.get(i).getRank() < minRank) {
                    indexOfCurrentMin = i;
                    minRank = selectFrom.get(i).getRank();
                }
            }
            return indexOfCurrentMin;
        }



    }
}
