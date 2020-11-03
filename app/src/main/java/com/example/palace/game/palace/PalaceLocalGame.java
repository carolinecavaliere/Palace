package com.example.palace.game.palace;

import com.example.palace.game.GamePlayer;
import com.example.palace.game.LocalGame;
import com.example.palace.game.actionMsg.GameAction;
import com.example.palace.game.infoMsg.GameState;

public class PalaceLocalGame extends LocalGame {
    private PalaceGameState palaceGame;

    public PalaceLocalGame(){
        palaceGame = new PalaceGameState(players.length);
    }
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PalaceGameState game = new PalaceGameState(palaceGame);
        p.sendInfo(game);
    }

    @Override
    protected boolean canMove(int playerIdx) {
        return false;
    }

    @Override
    protected String checkIfGameOver() {
        if(palaceGame.getP1BottomPalaceCards().isEmpty()){
            return ""+this.playerNames[0]+ " has won!!";
        }
        else if(palaceGame.getP2BottomPalaceCards().isEmpty()){
            return ""+this.playerNames[1]+ " has won!!";
        }
        else if(palaceGame.getP3BottomPalaceCards().isEmpty()){
            return ""+this.playerNames[2]+ " has won!!";
        }
        else if(palaceGame.getP4BottomPalaceCards().isEmpty()){
            return ""+this.playerNames[3]+ " has won!!";
        }
        else{
            return null;
        }
    }

    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }
}
