
package com.example.palace.game;

import com.example.palace.game.actionMsg.GameAction;

/**
 * @Author: Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
 * This class models a player playing a card/cards to the play pile. It handles
 * cases in which the player's selected card(s) cannot legally be played, and
 * adds a new card to the player's hand when appropriate after they play a legal card(s)
 */
public class PalacePlayCardAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PalacePlayCardAction(GamePlayer player) {
        super(player);
    }

    public boolean playCard(int playerId, PalaceGameState state) {
        if (state.getTurn() != playerId) {//not a valid move if it is not that player's turn
            return false;
        }
        else if (state.getSelectedPalaceCards().isEmpty()) {//not a valid move if a card isn't selected
            return false;
        }
        else if (state.getSelectedPalaceCards().size() > 1) {//not a valid move if multiple cards are selected but not the same rank
            for (int i = 0; i < state.getSelectedPalaceCards().size() - 1; i++) {
                if (state.getSelectedPalaceCards().get(i).getRank() != state.getSelectedPalaceCards().get(i + 1).getRank()) {
                    return false;
                }
            }
        }
        else {
            for (int i = 0; i < state.getSelectedPalaceCards().size(); i++) {
                if (playerId == 1) {
                    state.addToPlayPile(state.getSelectedPalaceCards().get(i));
                    state.setPlayPileTopPalaceCard(state.getSelectedPalaceCards().get(i));
                    state.removeFromP1Hand(state.getSelectedPalaceCards().get(i));
                    state.setPlayPileNumCards(state.getPlayPileNumCards() + 1);
                    state.setP1numCards(state.getP1numCards() - 1);
                    if (state.getP1Hand().size() < 3) {//adds new card to the player's hand
                        for (int j = state.getP1numCards(); j < 3; j++) {
                            state.getDeck().drawCard(1);
                        }
                        state.setP1numCards(3);
                    }
                    state.clearSelectedCards();
                }
                else if (playerId == 2) {
                    state.addToPlayPile(state.getSelectedPalaceCards().get(i));
                    state.setPlayPileTopPalaceCard(state.getSelectedPalaceCards().get(i));
                    state.removeFromP2Hand(state.getSelectedPalaceCards().get(i));
                    state.setPlayPileNumCards(state.getPlayPileNumCards() + 1);
                    state.setP2numCards(state.getP2numCards() - 1);
                    if (state.getP2Hand().size() < 3) {
                        for (int j = state.getP2numCards(); j < 3; j++) {
                            state.addToP2Hand(state.getDeck().getNextCard());
                        }
                        state.setP2numCards(3);
                    }
                    state.clearSelectedCards();
                }
                else if (playerId == 3) {
                    state.addToPlayPile(state.getSelectedPalaceCards().get(i));
                    state.setPlayPileTopPalaceCard(state.getSelectedPalaceCards().get(i));
                    state.removeFromP3Hand(state.getSelectedPalaceCards().get(i));
                    state.setPlayPileNumCards(state.getPlayPileNumCards() + 1);
                    state.setP3numCards(state.getP3numCards() - 1);
                    if (state.getP3Hand().size() < 3) {
                        for (int j = state.getP3numCards(); j < 3; j++) {
                            state.addToP3Hand(state.getDeck().getNextCard());
                        }
                        state.setP3numCards(3);
                    }
                    state.clearSelectedCards();
                }
                else if (playerId == 4) {
                    state.addToPlayPile(state.getSelectedPalaceCards().get(i));
                    state.setPlayPileTopPalaceCard(state.getSelectedPalaceCards().get(i));
                    state.removeFromP4Hand(state.getSelectedPalaceCards().get(i));
                    state.setPlayPileNumCards(state.getPlayPileNumCards() + 1);
                    state.setP4numCards(state.getP4numCards() - 1);
                    if (state.getP4Hand().size() < 3) {
                        for (int j = state.getP4numCards(); j < 3; j++) {
                            state.addToP4Hand(state.getDeck().getNextCard());
                        }
                        state.setP4numCards(3);
                    }
                    state.clearSelectedCards();
                }
                return true;
            }
        }
        return false;
    }
}
