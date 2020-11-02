package com.example.palace.game;

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
    public boolean switchBaseCards(int playerID, PalaceGameState palaceGameState, PalaceCard hand, PalaceCard bot) {
        int index;
        ArrayList<PalaceCard> handPalaceCards;
        ArrayList<PalaceCard> topPalaceCards;

        if (playerID == palaceGameState.getTurn()) {
            if (playerID == 1) {
                handPalaceCards = new ArrayList<PalaceCard>(palaceGameState.getP1Hand());
                topPalaceCards = new ArrayList<PalaceCard>(palaceGameState.getP1TopPalaceCards());
                index = topPalaceCards.indexOf(bot);
                topPalaceCards.set(index, hand);
                index = handPalaceCards.indexOf(hand);
                handPalaceCards.set(index, bot);
                palaceGameState.setP1Hand(handPalaceCards);
                palaceGameState.setP1TopPalaceCards(topPalaceCards);
                return true;
            }
            else if (playerID == 2) {
                handPalaceCards = palaceGameState.getP2Hand();
                topPalaceCards = palaceGameState.getP2TopPalaceCards();
                index = topPalaceCards.indexOf(bot);
                topPalaceCards.set(index, hand);
                index = handPalaceCards.indexOf(hand);
                handPalaceCards.set(index, bot);
                palaceGameState.setP1Hand(handPalaceCards);
                palaceGameState.setP1TopPalaceCards(topPalaceCards);
                return true;
            }
            else if (playerID == 3) {
                handPalaceCards = palaceGameState.getP3Hand();
                topPalaceCards = palaceGameState.getP3TopPalaceCards();
                index = topPalaceCards.indexOf(bot);
                topPalaceCards.set(index, hand);
                index = handPalaceCards.indexOf(hand);
                handPalaceCards.set(index, bot);
                palaceGameState.setP1Hand(handPalaceCards);
                palaceGameState.setP1TopPalaceCards(topPalaceCards);
                return true;
            }
            else if (playerID == 4) {
                handPalaceCards = palaceGameState.getP4Hand();
                topPalaceCards = palaceGameState.getP4TopPalaceCards();
                index = topPalaceCards.indexOf(bot);
                topPalaceCards.set(index, hand);
                index = handPalaceCards.indexOf(hand);
                handPalaceCards.set(index, bot);
                palaceGameState.setP1Hand(handPalaceCards);
                palaceGameState.setP1TopPalaceCards(topPalaceCards);
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
