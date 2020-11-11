package com.example.palace.game.palace;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Author: Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
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
            state.setDrawPileNumCards(state.getDrawPileNumCards() + 52);
        }
        ShuffleDeck(deck);
    }

    public PalaceDeckOfCards(PalaceDeckOfCards orig){
        deck = new ArrayList<PalaceCard>();
        for(int i = 0; i<orig.deck.size(); i++){
            deck.add(new PalaceCard(orig.deck.get(i)));
            this.state = orig.state;
        }
    }
    public static void ShuffleDeck(ArrayList<PalaceCard> myDeck){
        Collections.shuffle(myDeck);
    }

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
            else if (state.getNumPlayers()==3){
                state.addToP1Bottom(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.addToP2Bottom(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.addToP3Bottom(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
            }
            else{
                state.addToP1Bottom(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.addToP2Bottom(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.addToP3Bottom(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.addToP4Bottom(deck.get(0));
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
            else if (state.getNumPlayers()==3){
                state.addToP1Hand(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.setP1numCards(state.getP1numCards()+1);
                state.addToP2Hand(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.setP2numCards(state.getP2numCards()+1);
                state.addToP3Hand(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.setP3numCards(state.getP3numCards()+1);
            }
            else{
                state.addToP1Hand(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.setP1numCards(state.getP1numCards()+1);
                state.addToP2Hand(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.setP2numCards(state.getP2numCards()+1);
                state.addToP3Hand(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.setP3numCards(state.getP3numCards()+1);
                state.addToP4Hand(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.setP3numCards(state.getP3numCards()+1);
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
            else if (state.getNumPlayers()==3){
                state.addToP1TopCards(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.addToP2TopCards(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.addToP3TopCards(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
            }
            else{
                state.addToP1TopCards(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.addToP2TopCards(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.addToP3TopCards(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
                state.addToP4TopCards(deck.get(0));
                deck.remove(0);
                state.setDrawPileNumCards(state.getDrawPileNumCards()-1);
            }
        }
        state.addToPlayPile(deck.get(0));
        deck.remove(0);
        state.setPlayPileNumCards(1);
        state.setPlayPileTopPalaceCard(state.getPlayPilePalaceCards().get(0));
    }

    public PalaceCard getNextCard() {
        cardCount++;
        state.setDrawPileNumCards(state.getDrawPileNumCards() - 1);
        return deck.get(cardCount);
    }

    public void drawCard(int player) {
        if (player == 1) {
            state.addToP1Hand(state.getDrawPileTopPalaceCard());
        } else if (player == 2) {
            state.addToP2Hand(state.getDrawPileTopPalaceCard());
        } else if (player == 3) {
            state.addToP3Hand(state.getDrawPileTopPalaceCard());
        } else if (player == 4) {
            state.addToP4Hand(state.getDrawPileTopPalaceCard());
        }
        deck.remove(0);
        state.setDrawPileTopPalaceCard(deck.get(0));
    }
    public int getCardCount() {
        return deck.size();
    }
}
