package edu.up.palace.game;

import org.junit.Test;

import edu.up.palace.palace.PalaceCard;
import edu.up.palace.palace.PalaceGameState;

import static org.junit.Assert.*;

public class PalaceGameStateTest {

    @Test
    public void getNumPlayers() {
        PalaceGameState state = new PalaceGameState(4);
        assertEquals(state.getNumPlayers(), 4);
    }

    @Test
    public void setTurn() {
        PalaceGameState state = new PalaceGameState(4);
        state.setTurn(3);
        assertEquals(state.getTurn(), 3);
    }

    @Test
    public void getTurn() {
        PalaceGameState state = new PalaceGameState(4);
        assertEquals(1, state.getTurn());
    }

    @Test
    public void addToP1Hand() {
        PalaceGameState state = new PalaceGameState(4);
        PalaceCard card = new PalaceCard(1,2);
        state.addToP1Hand(card);
        assertEquals(card, state.getP1Hand().get(3));
    }

    @Test
    public void removeFromPlayPile() {
        PalaceGameState state = new PalaceGameState(4);
        assertEquals(state.getPlayPileNumCards(), 1);
        state.removeFromPlayPile(0);
        assertEquals(state.getPlayPileNumCards(), 0);
    }


    @Test
    public void getDrawPileNumCards() {
        PalaceGameState state = new PalaceGameState(4);
        int total = (52*2) - (state.getP1numCards() + state.getP2numCards() + state.getP3numCards()
                + state.getP4numCards() + state.getP1TopPalaceCards().size()
                + state.getP2TopPalaceCards().size() + state.getP1BottomPalaceCards().size()
                + state.getP2BottomPalaceCards().size() + state.getPlayPileNumCards());
        assertEquals(total, state.getDrawPileNumCards());
    }


    @Test
    public void addToSelectedCards() {
        PalaceGameState state = new PalaceGameState(4);
        PalaceCard card = state.getP1Hand().get(1);
        state.addToSelectedCards(card);
        assertEquals(card, state.getSelectedPalaceCards().get(0));
    }

    @Test
    public void getPlayPileNumCards() {
        PalaceGameState state = new PalaceGameState(4);
        int initNum = state.getPlayPileNumCards();
        assertEquals(1, initNum);
    }

    @Test
    public void setPlayPileNumCards() {
        PalaceGameState state = new PalaceGameState(4);
        PalaceCard card = new PalaceCard(2,3);
        state.addToPlayPile(state.getP1Hand().get(0));
        assertEquals(2, state.getPlayPileNumCards());
    }

    @Test
    public void getNumDisplayHand() {
        PalaceGameState state = new PalaceGameState(4);
        assertEquals(state.getNumDisplayHand(), 0);
    }

    @Test
    public void setNumDisplayHand() {
        PalaceGameState state = new PalaceGameState(4);
        state.setNumDisplayHand(2);
        assertEquals(2, state.getNumDisplayHand());
    }

    @Test
    public void clearPlayPileCards() {
        PalaceGameState state = new PalaceGameState(4);
        assertEquals(1, state.getPlayPileNumCards());
        state.clearPlayPileCards();
        assertEquals(0, state.getPlayPileNumCards());
    }

    @Test
    public void getCardToBeSelected() {
        PalaceGameState state = new PalaceGameState(4);
        PalaceCard card = new PalaceCard(2,12);
        state.setCardToBeSelected(card);
        assertEquals(card, state.getCardToBeSelected());
    }

    /*@Test
    public void addToPlayPile() {
        PalaceGameState state = new PalaceGameState(4);
        PalaceCard card = new PalaceCard(2,12);
        state.addToPlayPile(card);
        assertEquals(card, state.getPlayPileTopPalaceCard());
    }*/

    @Test
    public void getP1BottomCard() {
        PalaceGameState state = new PalaceGameState(4);
        PalaceCard card = new PalaceCard(state.getP1BottomPalaceCards().get(0));
        assertEquals(card, state.getP1BottomPalaceCards().get(0));
    }

    @Test
    public void removeFromP2Hand() {
        PalaceGameState state = new PalaceGameState(4);
        state.removeFromP2Hand(state.getP2Hand().get(0));
        assertEquals(2, state.getP2numCards());
    }
}