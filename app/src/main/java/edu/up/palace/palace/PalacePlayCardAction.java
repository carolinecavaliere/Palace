
package edu.up.palace.palace;

import edu.up.palace.game.GamePlayer;
import edu.up.palace.game.actionMsg.GameAction;

/**
 * @author Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
 * This class models a player playing a card/cards to the play pile. It handles
 * cases in which the player's selected card(s) cannot legally be played, and
 * adds a new card to the player's hand when appropriate after they play a legal card(s)
 *
 * CAVEATS: None
 */
public class PalacePlayCardAction extends GameAction {
    /**
     * constructor
     *
     * @param player the player who created the action
     */
    public PalacePlayCardAction(GamePlayer player) {
        super(player);
    }

}
