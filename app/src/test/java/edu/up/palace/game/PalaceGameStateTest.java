package edu.up.palace.game;

import org.junit.Test;

import edu.up.palace.palace.PalaceCard;
import edu.up.palace.palace.PalaceGameState;

import static org.junit.Assert.*;

public class PalaceGameStateTest {

    @Test
    public void getNumPlayers() {
        PalaceGameState state = new PalaceGameState(2);
        assertEquals(state.getNumPlayers(), 2);
    }

    @Test
    public void setTurn() {
        PalaceGameState state = new PalaceGameState(2);
        state.setTurn(3);
        assertEquals(state.getTurn(), 3);
    }

    @Test
    public void getTurn() {
        PalaceGameState state = new PalaceGameState(2);
        assertEquals(0, state.getTurn());
    }

    @Test
    public void addToP1Hand() {
        PalaceGameState state = new PalaceGameState(2);
        PalaceCard card = new PalaceCard(1,2);
        state.addToP1Hand(card);
        assertEquals(card, state.getP1Hand().get(3));
    }

    @Test
    public void getDrawPileNumCards() {
        PalaceGameState state = new PalaceGameState(2);
        int total = 52 - (state.getP1numCards() + state.getP2numCards() + state.getP3numCards()
                + state.getP4numCards() + state.getP1TopPalaceCards().size()
                + state.getP2TopPalaceCards().size() + state.getP1BottomPalaceCards().size()
                + state.getP2BottomPalaceCards().size() + state.getPlayPileNumCards());
        assertEquals(total, state.getDrawPileNumCards());
    }


    @Test
    public void addToSelectedCards() {
        PalaceGameState state = new PalaceGameState(2);
        PalaceCard card = state.getP1Hand().get(1);
        state.addToSelectedCards(card);
        assertEquals(card, state.getSelectedPalaceCards().get(0));
    }

    @Test
    public void getPlayPileNumCards() {
        PalaceGameState state = new PalaceGameState(2);
        int initNum = state.getPlayPileNumCards();
        assertEquals(1, initNum);
    }

    @Test
    public void getNumDisplayHand() {
        PalaceGameState state = new PalaceGameState(2);
        assertEquals(state.getNumDisplayHand(), 0);
    }

    @Test
    public void setNumDisplayHand() {
        PalaceGameState state = new PalaceGameState(2);
        state.setNumDisplayHand(2);
        assertEquals(2, state.getNumDisplayHand());
    }

    @Test
    public void clearPlayPileCards() {
        PalaceGameState state = new PalaceGameState(2);
        assertEquals(1, state.getPlayPileNumCards());
        state.clearPlayPileCards();
        assertEquals(0, state.getPlayPileNumCards());
    }

    @Test
    public void getCardToBeSelected() {
        PalaceGameState state = new PalaceGameState(2);
        PalaceCard card = new PalaceCard(2,12);
        state.setCardToBeSelected(card);
        assertEquals(card, state.getCardToBeSelected());
    }

    @Test
    public void addToPlayPile() {
        PalaceGameState state = new PalaceGameState(2);
        PalaceCard card = new PalaceCard(2,12);
        state.addToPlayPile(card);
        //get the second card, since there's one underneath already
        assertEquals(card, state.getPlayPilePalaceCards().get(1));
    }

    @Test
    public void getP1BottomCard() {
        PalaceGameState state = new PalaceGameState(2);
        PalaceCard card = new PalaceCard(state.getP1BottomPalaceCards().get(0));
        assertEquals(card, state.getP1BottomPalaceCards().get(0));
    }

    @Test
    public void removeFromP2Hand() {
        PalaceGameState state = new PalaceGameState(2);
        state.removeFromP2Hand(state.getP2Hand().get(0));
        assertEquals(2, state.getP2Hand().size());
    }

    @Test
    public void getPlayPilePalaceCards() {
        PalaceGameState state = new PalaceGameState(2);
        state.removeFromPlayPile(0);
        state.addToPlayPile(new PalaceCard(1, 3));
        PalaceCard card = state.getPlayPilePalaceCards().get(0);
        assertEquals(card, state.getPlayPilePalaceCards().get(0));
        state.removeFromPlayPile(0);
        state.addToPlayPile(new PalaceCard(1, 2));
        assertNotEquals(card, state.getPlayPilePalaceCards().get(0));
    }

    @Test
    public void PalaceGameState() {
        PalaceGameState state = new PalaceGameState(2);
        PalaceGameState newState = new PalaceGameState(state);
        assertEquals(state.getP1Hand(), newState.getP1Hand());
        assertEquals(state.getP2Hand(), newState.getP2Hand());
        assertEquals(state.getP1TopPalaceCards(), newState.getP1TopPalaceCards());
        assertEquals(state.getP2TopPalaceCards(), newState.getP2TopPalaceCards());
        assertEquals(state.getP1BottomPalaceCards(), newState.getP1BottomPalaceCards());
        assertEquals(state.getP2BottomPalaceCards(), newState.getP2BottomPalaceCards());
        assertEquals(state.getNumDisplayHand(), newState.getNumDisplayHand());
        assertEquals(state.getNumPlayers(), newState.getNumPlayers());
        assertEquals(state.getDrawPileNumCards(), newState.getDrawPileNumCards());
        assertEquals(state.getPlayPilePalaceCards(), newState.getPlayPilePalaceCards());
        assertEquals(state.getPlayPileNumCards(), newState.getPlayPileNumCards());
    }
}