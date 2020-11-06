package com.example.palace.game.palace;

import com.example.palace.game.GameComputerPlayer;
import com.example.palace.game.GamePlayer;
import com.example.palace.game.infoMsg.GameInfo;

public class PalaceComputerPlayer extends GameComputerPlayer {
    public PalaceComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        PalaceGameState state = new PalaceGameState((PalaceGameState)info);
        if(state.getTurn()!=this.playerNum-1)
        {
            return;
        }
        else
        {
            double rand = Math.random();
            if(rand<0.2)
            {
                PalaceTakePileAction take = new PalaceTakePileAction(this);
                this.game.sendAction(take);
            }
            else
            {
                if (this.playerNum == 0){
                    state.setCardToBeSelected(state.getP1Hand().get(((int)Math.random()*state.getP1numCards())));
                }
                else if (this.playerNum == 1){
                    state.setCardToBeSelected(state.getP2Hand().get(((int)Math.random()*state.getP2numCards())));
                }
                else if (this.playerNum == 2){
                    state.setCardToBeSelected(state.getP3Hand().get(((int)Math.random()*state.getP3numCards())));
                }
                else if (this.playerNum == 3){
                    state.setCardToBeSelected(state.getP4Hand().get(((int)Math.random()*state.getP4numCards())));
                }
                else {
                    return;
                }
                while (state.getCardToBeSelected().getRank()<state.getPlayPileTopPalaceCard().getRank()){
                    if (this.playerNum == 0){
                        state.setCardToBeSelected(state.getP1Hand().get(((int)Math.random()*state.getP1numCards())));
                    }
                    else if (this.playerNum == 1){
                        state.setCardToBeSelected(state.getP2Hand().get(((int)Math.random()*state.getP2numCards())));
                    }
                    else if (this.playerNum == 2){
                        state.setCardToBeSelected(state.getP3Hand().get(((int)Math.random()*state.getP3numCards())));
                    }
                    else if (this.playerNum == 3){
                        state.setCardToBeSelected(state.getP4Hand().get(((int)Math.random()*state.getP4numCards())));
                    }
                    else {
                        return;
                    }
                }
                PalaceSelectCardAction selectCardAction = new PalaceSelectCardAction(this);
                this.game.sendAction(selectCardAction);
                PalacePlayCardAction play = new PalacePlayCardAction(this);
                this.game.sendAction(play);
            }
        }
    }
}
