package com.example.palace;

/**
 * @Author: Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
 * This class models the instance in which the player chooses to or must take the play pile.
 */
public class PalaceTakePileAction {

    public boolean takePile(int playerID, PalaceGameState palaceGameState) {
        if (palaceGameState.getPlayPileNumCards() > 0 && playerID == palaceGameState.getTurn()) {
            if (playerID == 1) {
                for (int i = 0; i < palaceGameState.getPlayPileNumCards(); i++) {
                    palaceGameState.addToP1Hand(palaceGameState.getPlayPilePalaceCards().get(i));
                    palaceGameState.setP1numCards(palaceGameState.getP1numCards() + 1);
                }
                for (int i = 0; i < palaceGameState.getPlayPileNumCards(); i++) {
                    palaceGameState.removeFromPlayPile(0);
                    palaceGameState.setPlayPileNumCards(palaceGameState.getPlayPileNumCards() - 1);
                }
                palaceGameState.clearPlayPileCards();
                return true;
            }
            else if (playerID == 2) {
                for (int i = 0; i < palaceGameState.getPlayPileNumCards(); i++) {
                    palaceGameState.addToP2Hand(palaceGameState.getPlayPilePalaceCards().get(i));
                }
                while (palaceGameState.getPlayPilePalaceCards().get(0)!=null){
                    palaceGameState.removeFromPlayPile(0);
                }
                return true;
            }
            else if (playerID == 3) {
                for (int i = 0; i < palaceGameState.getPlayPileNumCards(); i++) {
                    palaceGameState.addToP3Hand(palaceGameState.getPlayPilePalaceCards().get(i));
                }
                while (palaceGameState.getPlayPilePalaceCards().get(0)!=null){
                    palaceGameState.removeFromPlayPile(0);
                }
                return true;
            }
            else if (playerID == 4) {
                for (int i = 0; i < palaceGameState.getPlayPileNumCards(); i++) {
                    palaceGameState.addToP4Hand(palaceGameState.getPlayPilePalaceCards().get(i));
                }
                while (palaceGameState.getPlayPilePalaceCards().get(0)!=null){
                    palaceGameState.removeFromPlayPile(0);
                }
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
