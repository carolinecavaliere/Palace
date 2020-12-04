package edu.up.palace.palace;

import edu.up.palace.game.GamePlayer;
import edu.up.palace.game.actionMsg.GameAction;

/**
 * @Author: Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
 * This class models the instance in which the player chooses to or must take the play pile.
 *
 * CAVEATS: None
 */
public class PalaceTakePileAction extends GameAction {

    /**
     * constructor
     *
     * @param player the player who created the action
     */
    public PalaceTakePileAction(GamePlayer player) {
        super(player);
    }

}
