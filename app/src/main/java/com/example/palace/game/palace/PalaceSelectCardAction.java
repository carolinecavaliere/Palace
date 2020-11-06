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

}
