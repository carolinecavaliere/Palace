package com.example.palace.game.palace;

import com.example.palace.game.GamePlayer;
import com.example.palace.game.actionMsg.GameAction;

import java.util.ArrayList;

/**
 * @Author: Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
 * This class models the player's option to switch any of their three top cards
 * with any of their three hand cards at the beginning of the game.
 */
public class PalaceSwitchBaseCardsAction extends GameAction {

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PalaceSwitchBaseCardsAction(GamePlayer player) {
        super(player);
    }

    /**
     * Switches a selected card from a player's hand and swaps it with a selected
     * card from a player's top card
     *
     * @Author Nathaniel Pon
     * 
     * @param playerID
     * @param palaceGameState
     * @param hand
     * @param bot
     * @return
     */

}
