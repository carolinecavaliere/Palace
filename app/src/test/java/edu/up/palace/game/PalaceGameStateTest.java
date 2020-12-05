package edu.up.palace.game;

import org.junit.Test;

import edu.up.palace.palace.PalaceCard;
import edu.up.palace.palace.PalaceGameState;

import static org.junit.Assert.*;

/**
 * Test class to test our getters and setters in the game state
 */
public class PalaceGameStateTest {

    /**
     * Get the correct number of players in the game (most likely be 2)
     */
    @Test
    public void getNumPlayers() {
        PalaceGameState state = new PalaceGameState(2);
        assertEquals(state.getNumPlayers(), 2);
    }

    /**
     * changing the turn
     */
    @Test
    public void setTurn() {
        PalaceGameState state = new PalaceGameState(2);
        state.setTurn(3);
        assertEquals(state.getTurn(), 3);
    }

    /**
     * getting the correct player's turn
     */
    @Test
    public void getTurn() {
        PalaceGameState state = new PalaceGameState(2);
        assertEquals(0, state.getTurn());
    }

    /**
     * add cards to P1 hand (for taking the pile and drawing cards)
     */
    @Test
    public void addToP1Hand() {
        PalaceGameState state = new PalaceGameState(2);
        PalaceCard card = new PalaceCard(1,2);
        state.addToP1Hand(card);
        assertEquals(card, state.getP1Hand().get(3));
    }

    /**
     * The number of cards in the deck should be correct
     */
    @Test
    public void getDrawPileNumCards() {
        PalaceGameState state = new PalaceGameState(2);
        int total = 52 - (state.getP1Hand().size() + state.getP2Hand().size()
                + state.getP1TopPalaceCards().size()
                + state.getP2TopPalaceCards().size() + state.getP1BottomPalaceCards().size()
                + state.getP2BottomPalaceCards().size() + state.getPlayPileNumCards());
        assertEquals(total, state.getDrawPileNumCards());
    }

    /**
     * add a card to the selected cards Array List
     */
    @Test
    public void addToSelectedCards() {
        PalaceGameState state = new PalaceGameState(2);
        PalaceCard card = state.getP1Hand().get(1);
        state.addToSelectedCards(card);
        assertEquals(card, state.getSelectedPalaceCards().get(0));
    }

    /**
     * get the number of cards in the play pile
     */
    @Test
    public void getPlayPileNumCards() {
        PalaceGameState state = new PalaceGameState(2);
        int initNum = state.getPlayPileNumCards();
        assertEquals(1, initNum);
    }

    /**
     * A integer variable to display the correct 3 cards in the player's hand
     */
    @Test
    public void getNumDisplayHand() {
        PalaceGameState state = new PalaceGameState(2);
        assertEquals(state.getNumDisplayHand(), 0);
    }

    /**
     * to set the correct page of cards to display
     */
    @Test
    public void setNumDisplayHand() {
        PalaceGameState state = new PalaceGameState(2);
        state.setNumDisplayHand(2);
        assertEquals(2, state.getNumDisplayHand());
    }

    /**
     * if a 10 or 4 of a kind is in play, the play pile must be cleared correctly
     */
    @Test
    public void clearPlayPileCards() {
        PalaceGameState state = new PalaceGameState(2);
        assertEquals(1, state.getPlayPileNumCards());
        state.clearPlayPileCards();
        assertEquals(0, state.getPlayPileNumCards());
    }

    /**
     * Get the card that is going to be selected
     */
    @Test
    public void getCardToBeSelected() {
        PalaceGameState state = new PalaceGameState(2);
        PalaceCard card = new PalaceCard(2,12);
        state.setCardToBeSelected(card);
        assertEquals(card, state.getCardToBeSelected());
    }

    /**
     * Play a card (to the play pile)
     */
    @Test
    public void addToPlayPile() {
        PalaceGameState state = new PalaceGameState(2);
        PalaceCard card = new PalaceCard(2,12);
        state.addToPlayPile(card);
        //get the second card, since there's one underneath already
        assertEquals(card, state.getPlayPilePalaceCards().get(1));
    }

    /**
     * get P1's bottom card
     */
    @Test
    public void getP1BottomCard() {
        PalaceGameState state = new PalaceGameState(2);
        PalaceCard card = new PalaceCard(state.getP1BottomPalaceCards().get(0));
        assertEquals(card, state.getP1BottomPalaceCards().get(0));
    }

    /**
     * remove a card from P2's hand
     */
    @Test
    public void removeFromP2Hand() {
        PalaceGameState state = new PalaceGameState(2);
        state.removeFromP2Hand(state.getP2Hand().get(0));
        assertEquals(2, state.getP2Hand().size());
    }

    /**
     * get the palace cards that have been played and not "burned" yet
     */
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

    /**
     * test the copy constructor
     */
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