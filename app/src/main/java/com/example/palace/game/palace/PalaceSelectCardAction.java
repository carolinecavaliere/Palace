package com.example.palace.game.palace;

import com.example.palace.game.GamePlayer;
import com.example.palace.game.actionMsg.GameAction;

/**
 * @Author: Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
 * This class models how a player selects a card/cards. (Will be modified once combined with
 * the GUI so that the selected cards are determined by the player's touch actions).
 */
public class PalaceSelectCardAction extends GameAction {

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PalaceSelectCardAction(GamePlayer player) {
        super(player);
    }

    public boolean selectCard(int playerId, PalaceGameState state, PalaceCard palaceCard) {
        if (palaceCard.getRank() >= state.getPlayPileTopPalaceCard().getRank()) {
            if (playerId != state.getTurn()) {
                return false;
            } else if (playerId == 1 && state.getP1Hand().contains(palaceCard)) {  // looks at player 1's hand
                state.addToSelectedCards(palaceCard); // put arraylist into GameState variable
                return true;
            } else if (playerId == 2 && state.getP2Hand().contains(palaceCard)) {  // looks at player 2's hand
                state.addToSelectedCards(palaceCard); // put into GameState variable
                return true;
            } else if (playerId == 3 && state.getP3Hand().contains(palaceCard)) {  // looks at player 3's hand
                state.addToSelectedCards(palaceCard); // put into GameState variable
                return true;
            } else if (playerId == 4 && state.getP4Hand().contains(palaceCard)) {  // looks at player 4's hand
                state.addToSelectedCards(palaceCard); // put into GameState variable
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

}
