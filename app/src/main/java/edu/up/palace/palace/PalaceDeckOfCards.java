package edu.up.palace.palace;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
 *
 * CAVEATS: Sometimes it creates duplicates of cards and we dont know why yet :(
 *
 * This class models a deck of cards and handles actions such as shuffling and dealing
 * the deck(s)
 */
public class PalaceDeckOfCards {
    static ArrayList<PalaceCard> deck = new ArrayList<PalaceCard>();//actual array
    static PalaceGameState state;
    protected static int cardCount;
    PalaceDeckOfCards(int numDecks, PalaceGameState palaceGameState){
        state = palaceGameState;
        state.setDrawPileNumCards(0);

        // add a card to the arrayList that represents a deck of cards. Do this twice if
        // the number of decks we want is 2
        for(int i = 0; i<numDecks; i++){
            deck.add(new PalaceCard(1, 14));
            deck.add(new PalaceCard(2, 14));
            deck.add(new PalaceCard(3, 14));
            deck.add(new PalaceCard(4, 14));

            deck.add(new PalaceCard(1, 13));
            deck.add(new PalaceCard(2, 13));
            deck.add(new PalaceCard(3, 13));
            deck.add(new PalaceCard(4, 13));

            deck.add(new PalaceCard(1, 12));
            deck.add(new PalaceCard(2, 12));
            deck.add(new PalaceCard(3, 12));
            deck.add(new PalaceCard(4, 12));

            deck.add(new PalaceCard(1, 11));
            deck.add(new PalaceCard(2, 11));
            deck.add(new PalaceCard(3, 11));
            deck.add(new PalaceCard(4, 11));

            deck.add(new PalaceCard(1, 10));
            deck.add(new PalaceCard(2, 10));
            deck.add(new PalaceCard(3, 10));
            deck.add(new PalaceCard(4, 10));

            deck.add(new PalaceCard(1, 9));
            deck.add(new PalaceCard(2, 9));
            deck.add(new PalaceCard(3, 9));
            deck.add(new PalaceCard(4, 9));

            deck.add(new PalaceCard(1, 8));
            deck.add(new PalaceCard(2, 8));
            deck.add(new PalaceCard(3, 8));
            deck.add(new PalaceCard(4, 8));

            deck.add(new PalaceCard(1, 7));
            deck.add(new PalaceCard(2, 7));
            deck.add(new PalaceCard(3, 7));
            deck.add(new PalaceCard(4, 7));

            deck.add(new PalaceCard(1, 6));
            deck.add(new PalaceCard(2, 6));
            deck.add(new PalaceCard(3, 6));
            deck.add(new PalaceCard(4, 6));

            deck.add(new PalaceCard(1, 5));
            deck.add(new PalaceCard(2, 5));
            deck.add(new PalaceCard(3, 5));
            deck.add(new PalaceCard(4, 5));

            deck.add(new PalaceCard(1, 4));
            deck.add(new PalaceCard(2, 4));
            deck.add(new PalaceCard(3, 4));
            deck.add(new PalaceCard(4, 4));

            deck.add(new PalaceCard(1, 3));
            deck.add(new PalaceCard(2, 3));
            deck.add(new PalaceCard(3, 3));
            deck.add(new PalaceCard(4, 3));

            deck.add(new PalaceCard(1, 2));
            deck.add(new PalaceCard(2, 2));
            deck.add(new PalaceCard(3, 2));
            deck.add(new PalaceCard(4, 2));

            // initialize the int number of cards in the deck
            state.setDrawPileNumCards(state.getDrawPileNumCards() + 52);
        }

        ShuffleDeck(deck); // shuffle the deck of cards in a random order
    }

    /**
     * copy constructor for the deck of cards
     * @param orig
     */
    public PalaceDeckOfCards(PalaceDeckOfCards orig){
        ArrayList<PalaceCard> copyDeck = new ArrayList<PalaceCard>();
        for(PalaceCard cardHere: orig.deck){
            copyDeck.add(cardHere);
        }
        this.state = orig.state;
    }

    /**
     * shuffles the deck of cards
     * @param myDeck
     */
    public static void ShuffleDeck(ArrayList<PalaceCard> myDeck){
        Collections.shuffle(myDeck);
    }

    /**
     * Set up the game by dealing cards to the computer and user.
     * User and computer gets 3 cards in their initial hand, 3 cards in their visible top hand,
     *  and 3 non-visible cards in their bottom hand
     */
    public static void DealDeck(){
        for (int i=0; i<3; i++){
            if(state.getNumPlayers()==2){
                state.addToP1Bottom(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.addToP2Bottom(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
            }
        }
        for (int i = 0; i<3; i++){
            if(state.getNumPlayers()==2){
                state.addToP1Hand(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.setP1numCards(state.getP1numCards()+1);
                state.addToP2Hand(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.setP2numCards(state.getP2numCards()+1);
            }
        }
        for (int i = 0; i<3; i++){
            if(state.getNumPlayers()==2){
                state.addToP1TopCards(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.addToP2TopCards(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
            }
        }
        state.addToPlayPile(deck.get(0));
        deck.remove(0);
        state.setPlayPileNumCards(1);
    }

    /**
     * This method draws a card from the "deck" arraylist of card objects
     * @return a card object
     */
   /** public PalaceCard getNextCard() {
        cardCount++;
        return deck.get(cardCount);
    }*/

    /**
     * Draws a card from the deck and puts it in the players hand
     * CAVEATS: Not used in code yet
     * @param player to know which hand to put a card into
     */
    public void drawCard(int player) {
        if (!(this.deck.isEmpty())) {
            if (player == 0) {
                state.addToP1Hand(this.deck.get(0));
            } else if (player == 1) {
                state.addToP2Hand(this.deck.get(0));
            }
            deck.remove(0);

        }
    }

    /**
     * Get the size of the deck of cards
     * CAVEATS: Not used in code yet
     * @return number of cards in the deck
     */
    public int getCardCount() {
        return deck.size();
    }

    public ArrayList<PalaceCard> getDeck(){
        return deck;
    }
}
