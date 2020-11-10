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
        PalaceSelectCardAction selectCardAction;
        if(state.getTurn()!=this.playerNum)
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
                while (state.getCardToBeSelected().getRank()<state.getPlayPileTopPalaceCard().getRank()){
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
                }

                this.game.sendAction(selectCardAction);
                PalacePlayCardAction play = new PalacePlayCardAction(this);
                this.game.sendAction(play);
            }
        }
    }
}
