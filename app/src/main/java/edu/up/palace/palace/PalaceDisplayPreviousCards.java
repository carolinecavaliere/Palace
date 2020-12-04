package edu.up.palace.palace;

import edu.up.palace.game.GamePlayer;
import edu.up.palace.game.actionMsg.GameAction;

/**
 * @Author: Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
 *
 * CAVEATS: None
 *
 * This class displays the previous set of cards in the player's hand, assuming that the player
 * has more than 3 cards in their hand. (Will eventually be switched out with a scrollbar)
 */
public class PalaceDisplayPreviousCards extends GameAction {
    /**
     * constructor
     *
     * @param player the player who created the action
     */
    public PalaceDisplayPreviousCards(GamePlayer player) {
        super(player);
    }
}
