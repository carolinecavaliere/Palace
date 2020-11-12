package com.example.palace.game.palace;

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
     * @param info
     * 		the information (containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        if(!(info instanceof PalaceGameState)){
            return;
        }
        PalaceGameState state = new PalaceGameState((PalaceGameState)info);
        PalaceSelectCardAction selectCardAction;
        if(state.getTurn()!=this.playerNum)
        {
            return;
        }
        else
        {
            double rand = Math.random();
            //for (int i = 0; i<state.getP2numCards(); i++)
            if(rand<0.2)//dumb AI randomly takes the pile
            {
                PalaceTakePileAction take = new PalaceTakePileAction(this);
                this.game.sendAction(take);
            }

            else {
                if (this.playerNum == 0){
                    selectCardAction = new PalaceSelectCardAction(this, state.getP1Hand().get(((int)Math.random()*state.getP1numCards())), state.getSelectedPalaceCards());
                }
                else if (this.playerNum == 1){
                    selectCardAction = new PalaceSelectCardAction(this, state.getP2Hand().get(((int)Math.random()*state.getP2numCards())), state.getSelectedPalaceCards());
                }
                else if (this.playerNum == 2){
                    selectCardAction = new PalaceSelectCardAction(this, state.getP3Hand().get(((int)Math.random()*state.getP3numCards())), state.getSelectedPalaceCards());
                }
                else if (this.playerNum == 3){
                    selectCardAction = new PalaceSelectCardAction(this, state.getP4Hand().get(((int)Math.random()*state.getP4numCards())), state.getSelectedPalaceCards());
                }
                else {
                    return;
                }
                while (state.getCardToBeSelected().getRank()<state.getPlayPilePalaceCards().get(state.getPlayPilePalaceCards().size()-1).getRank()) {
                    if (this.playerNum == 0) {
                        selectCardAction = new PalaceSelectCardAction(this, state.getP1Hand().get(((int) Math.random() * state.getP1numCards())), state.getSelectedPalaceCards());

                    } else if (this.playerNum == 1) {
                        selectCardAction = new PalaceSelectCardAction(this, state.getP2Hand().get(((int) Math.random() * state.getP2numCards())), state.getSelectedPalaceCards());
                    } else if (this.playerNum == 2) {
                        selectCardAction = new PalaceSelectCardAction(this, state.getP3Hand().get(((int) Math.random() * state.getP3numCards())), state.getSelectedPalaceCards());
                    } else if (this.playerNum == 3) {
                        selectCardAction = new PalaceSelectCardAction(this, state.getP4Hand().get(((int) Math.random() * state.getP4numCards())), state.getSelectedPalaceCards());
                    } else {
                        return;
                    }
                }

                this.game.sendAction(selectCardAction);
                PalacePlayCardAction play = new PalacePlayCardAction(this);
                this.game.sendAction(play);
            }
        }
    }
}
