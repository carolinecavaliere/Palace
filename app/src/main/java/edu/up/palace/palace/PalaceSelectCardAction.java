package edu.up.palace.palace;

import edu.up.palace.game.GamePlayer;
import edu.up.palace.game.actionMsg.GameAction;

import java.util.ArrayList;

/**
 * @Author: Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
 * This class models how a player selects a card/cards. (Will be modified once combined with
 * the GUI so that the selected cards are determined by the player's touch actions).
 *
 * CAVEATS: None
 */
public class PalaceSelectCardAction extends GameAction {

    private PalaceCard cardSelected;
    private ArrayList<PalaceCard> cardsSelected;


    /**
     * construct
     * @param player
     * @param c PalaceCard selected
     * @param l ArrayList of PalaceCards that have been selected
     */
    public PalaceSelectCardAction(GamePlayer player, PalaceCard c, ArrayList<PalaceCard> l) {

        super(player);
        this.cardSelected = c;
        this.cardsSelected = l;
    }

    //getters

    public PalaceCard getCardSelected() {
        return cardSelected;
    }

    public ArrayList<PalaceCard> getCardsSelected() {
        return cardsSelected;
    }

}
