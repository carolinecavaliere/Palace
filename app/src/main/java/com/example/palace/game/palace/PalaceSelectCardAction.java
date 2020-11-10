package com.example.palace.game.palace;

import com.example.palace.game.GamePlayer;
import com.example.palace.game.actionMsg.GameAction;

import java.util.ArrayList;

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

    private PalaceCard cardSelected;
    private ArrayList<PalaceCard> cardsSelected;

    public PalaceCard getCardSelected() {
        return cardSelected;
    }

    public ArrayList<PalaceCard> getCardsSelected() {
        return cardsSelected;
    }

    public PalaceSelectCardAction(GamePlayer player, PalaceCard c, ArrayList<PalaceCard> l) {

        super(player);
        this.cardSelected = c;
        this.cardsSelected = l;
    }

    //add instance variable/parameter, getter method
}
