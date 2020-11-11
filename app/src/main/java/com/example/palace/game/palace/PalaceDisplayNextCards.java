package com.example.palace.game.palace;

import com.example.palace.game.GamePlayer;
import com.example.palace.game.actionMsg.GameAction;

/**
 * @Author: Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
 * This class models a player playing a card/cards to the play pile. It handles
 * cases in which the player's selected card(s) cannot legally be played, and
 * adds a new card to the player's hand when appropriate after they play a legal card(s)
 */
public class PalaceDisplayNextCards extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PalaceDisplayNextCards(GamePlayer player) {
        super(player);
    }


}
