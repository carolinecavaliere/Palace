package edu.up.palace.palace;

import android.util.Log;

import edu.up.palace.game.infoMsg.GameState;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
 *
 * CAVEATS: None
 *
 * The state of the Palace game. This class holds all necessary information about the state of
 * the game.
 */
public class PalaceGameState extends GameState {

    private boolean flipped;

    private int numDisplayHand;
    private int numPlayers;
    private int p1numCards;
    private int p2numCards;
    private int p3numCards;
    private int p4numCards;

    private ArrayList<PalaceCard> p1Hand;

    //cards displayed in user's hand
    private PalaceCard displayCard1P1;
    private PalaceCard displayCard2P1;
    private PalaceCard displayCard3P1;

    private ArrayList<PalaceCard> p2Hand;
    private PalaceCard displayCard1P2;
    private PalaceCard displayCard2P2;
    private PalaceCard displayCard3P2;

    private int turn;//keeps track of whose turn it is

    private PalaceDeckOfCards deck;

    private ArrayList<PalaceCard> p1TopPalaceCards;
    private ArrayList<PalaceCard> p1BottomPalaceCards;

    private ArrayList<PalaceCard> p2TopPalaceCards;
    private ArrayList<PalaceCard> p2BottomPalaceCards;

    private ArrayList<PalaceCard> playPilePalaceCards;

    private ArrayList<PalaceCard> selectedPalaceCards; // array of selected cards
    private PalaceCard cardToBeSelected; //individual card selected by player
    private PalaceCard topCardSelected; //card chosen to swap with hand card

    private PalaceCard drawPileTopPalaceCard;
    private int drawPileNumCards;
    private int playPileNumCards;

    /**
     * PalaceGameState constructor
     *
     * @param numPlayers
     */
    public PalaceGameState(int numPlayers) {
        numDisplayHand = 0;
        this.numPlayers = numPlayers;
        turn = 0;
        playPilePalaceCards = new ArrayList<PalaceCard>();

        p1TopPalaceCards = new ArrayList<PalaceCard>();
        p1BottomPalaceCards = new ArrayList<PalaceCard>();

        p2TopPalaceCards = new ArrayList<PalaceCard>();
        p2BottomPalaceCards = new ArrayList<PalaceCard>();

        p1Hand = new ArrayList<PalaceCard>();

        p2Hand = new ArrayList<PalaceCard>();

        selectedPalaceCards = new ArrayList<PalaceCard>();

        if (this.numPlayers <= 2) {
            deck = new PalaceDeckOfCards(1, this);
        } else {
            deck = new PalaceDeckOfCards(2, this);
        }

        deck.DealDeck();
        displayCard1P1 = p1Hand.get(0);
        displayCard2P1 = p1Hand.get(1);
        displayCard3P1 = p1Hand.get(2);
        displayCard1P2 = p2Hand.get(0);
        displayCard2P2 = p2Hand.get(1);
        displayCard3P2 = p2Hand.get(2);

        //tempDeck = 10;
        cardToBeSelected = new PalaceCard(4, 14);//default is ace of spades
        topCardSelected = new PalaceCard(4, 14);

        Collections.sort(p1Hand);
    }

    /**
     * copy constructor for PalaceGameState
     *
     * @param orig
     */
    public PalaceGameState(PalaceGameState orig) {
        this.numDisplayHand = orig.getNumDisplayHand();
        this.numPlayers = orig.getNumPlayers();
        this.p1numCards = orig.getP1numCards();
        this.p2numCards = orig.getP2numCards();
        this.p3numCards = orig.getP3numCards();
        this.p4numCards = orig.getP4numCards();

        this.p1Hand = new ArrayList<PalaceCard>();
        displayCard1P1 = new PalaceCard(orig.getDisplayCard1P1());
        displayCard2P1 = new PalaceCard(orig.getDisplayCard2P1());
        displayCard3P1 = new PalaceCard(orig.getDisplayCard3P1());
        this.p2Hand = new ArrayList<PalaceCard>();
        displayCard1P2 = new PalaceCard(orig.getDisplayCard1P2());
        displayCard2P2 = new PalaceCard(orig.getDisplayCard2P2());
        displayCard3P2 = new PalaceCard(orig.getDisplayCard3P2());

        this.p1TopPalaceCards = new ArrayList<PalaceCard>();
        this.p2TopPalaceCards = new ArrayList<PalaceCard>();

        this.p1BottomPalaceCards = new ArrayList<PalaceCard>();
        this.p2BottomPalaceCards = new ArrayList<PalaceCard>();

        this.selectedPalaceCards = new ArrayList<PalaceCard>(orig.getSelectedPalaceCards().size());

        for (int i = 0; i < orig.getP1Hand().size(); i++) {
            this.p1Hand.add(new PalaceCard(orig.getP1Hand().get(i)));
        }
        for (int i = 0; i < orig.getP2Hand().size(); i++) {
            this.p2Hand.add(new PalaceCard(orig.getP2Hand().get(i)));
        }

        this.turn = orig.getTurn();
        this.deck = new PalaceDeckOfCards(orig.deck);

        for (int i = 0; i < orig.getP1TopPalaceCards().size(); i++) {
            this.p1TopPalaceCards.add(new PalaceCard(orig.getP1TopPalaceCards().get(i)));
        }
        for (int i = 0; i < orig.getP2TopPalaceCards().size(); i++) {
            this.p2TopPalaceCards.add(new PalaceCard(orig.getP2TopPalaceCards().get(i)));
        }

        for (int i = 0; i < orig.getP1BottomPalaceCards().size(); i++) {
            this.p1BottomPalaceCards.add(new PalaceCard(orig.getP1BottomPalaceCards().get(i)));
        }
        for (int i = 0; i < orig.getP2BottomPalaceCards().size(); i++) {
            this.p2BottomPalaceCards.add(new PalaceCard(orig.getP2BottomPalaceCards().get(i)));
        }

        for (int i = 0; i < orig.getSelectedPalaceCards().size(); i++) {
            this.selectedPalaceCards.add(new PalaceCard(orig.getSelectedPalaceCards().get(i)));
        }

        cardToBeSelected = new PalaceCard(orig.getCardToBeSelected());
        topCardSelected = new PalaceCard(orig.getTopCardSelected());
        playPilePalaceCards = orig.getPlayPilePalaceCards();
        this.drawPileNumCards = orig.getDrawPileNumCards();
        this.playPileNumCards = orig.getPlayPileNumCards();

        Collections.sort(p1Hand);


        Log.d("GameState", "Gamestate successfully created.");
    }


    //getters and setters

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public int getNumDisplayHand() {
        return numDisplayHand;
    }

    public void setNumDisplayHand(int numDisplayHand) {
        this.numDisplayHand = numDisplayHand;
    }

    public ArrayList<PalaceCard> getSelectedPalaceCards() {
        return selectedPalaceCards;
    }

    public void setSelectedPalaceCards(ArrayList<PalaceCard> selectedPalaceCards) {
        this.selectedPalaceCards = new ArrayList<PalaceCard>(selectedPalaceCards);
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getP1numCards() {
        return p1numCards;
    }

    public void setP1numCards(int p1numCards) {
        this.p1numCards = p1numCards;
    }

    public int getP2numCards() {
        return p2numCards;
    }

    public void setP2numCards(int p2numCards) {
        this.p2numCards = p2numCards;
    }

    public int getP3numCards() {
        return p3numCards;
    }

    public int getP4numCards() {
        return p4numCards;
    }

    public ArrayList<PalaceCard> getP1Hand() {
        return p1Hand;
    }

    public void setP1Hand(ArrayList<PalaceCard> p1Hand) {
        this.p1Hand = new ArrayList<PalaceCard>(p1Hand);
    }

    public ArrayList<PalaceCard> getP2Hand() {
        return p2Hand;
    }

    public void setP2Hand(ArrayList<PalaceCard> p2Hand) {
        this.p2Hand = new ArrayList<PalaceCard>(p2Hand);
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public PalaceDeckOfCards getDeck() {
        return deck;
    }

    public ArrayList<PalaceCard> getPlayPilePalaceCards() {
        return playPilePalaceCards;
    }

    public void setPlayPilePalaceCards(ArrayList<PalaceCard> playPilePalaceCards) {
        this.playPilePalaceCards = new ArrayList<PalaceCard>(playPilePalaceCards);
    }

    public void setDeck(PalaceDeckOfCards deck) {
        this.deck = deck;
    }

    public ArrayList<PalaceCard> getP1TopPalaceCards() {
        return p1TopPalaceCards;
    }

    public void setP1TopPalaceCards(ArrayList<PalaceCard> p1TopPalaceCards) {
        this.p1TopPalaceCards = new ArrayList<PalaceCard>(p1TopPalaceCards);
    }

    public ArrayList<PalaceCard> getP1BottomPalaceCards() {
        return p1BottomPalaceCards;
    }

    public void setP1BottomPalaceCards(ArrayList<PalaceCard> p1BottomPalaceCards) {
        this.p1BottomPalaceCards = new ArrayList<PalaceCard>(p1BottomPalaceCards);
    }

    public ArrayList<PalaceCard> getP2TopPalaceCards() {
        return p2TopPalaceCards;
    }

    public void setP2TopPalaceCards(ArrayList<PalaceCard> p2TopPalaceCards) {
        this.p2TopPalaceCards = new ArrayList<PalaceCard>(p2TopPalaceCards);
    }

    public ArrayList<PalaceCard> getP2BottomPalaceCards() {
        return p2BottomPalaceCards;
    }

    public void setP2BottomPalaceCards(ArrayList<PalaceCard> p2BottomPalaceCards) {
        this.p2BottomPalaceCards = new ArrayList<PalaceCard>(p2BottomPalaceCards);
    }

    public PalaceCard getDrawPileTopPalaceCard() {
        return drawPileTopPalaceCard;
    }

    public void setDrawPileTopPalaceCard(PalaceCard drawPileTopPalaceCard) {
        this.drawPileTopPalaceCard = drawPileTopPalaceCard;
    }

    public int getDrawPileNumCards() {
        return drawPileNumCards;
    }

    public void setDrawPileNumCards(int drawPileNumCards) {
        this.drawPileNumCards = drawPileNumCards;
    }

    public int getPlayPileNumCards() {
        return playPileNumCards;
    }

    public void setPlayPileNumCards(int playPileNumCards) {
        this.playPileNumCards = playPileNumCards;
    }

    public PalaceCard getCardToBeSelected() {
        return cardToBeSelected;
    }

    public void setCardToBeSelected(PalaceCard card) {
        this.cardToBeSelected = card;
    }

    public PalaceCard getTopCardSelected() {
        return topCardSelected;
    }

    public void setTopCardSelected(PalaceCard topCardSelected) {
        this.topCardSelected = topCardSelected;
    }

    public PalaceCard getDisplayCard1P1() {
        return displayCard1P1;
    }

    public void setDisplayCard1P1(PalaceCard displayCard1P1) {
        this.displayCard1P1 = displayCard1P1;
    }

    public PalaceCard getDisplayCard2P1() {
        return displayCard2P1;
    }

    public void setDisplayCard2P1(PalaceCard displayCard2P1) {
        this.displayCard2P1 = displayCard2P1;
    }

    public PalaceCard getDisplayCard3P1() {
        return displayCard3P1;
    }

    public void setDisplayCard3P1(PalaceCard displayCard3P1) {
        this.displayCard3P1 = displayCard3P1;
    }

    public PalaceCard getDisplayCard1P2() {
        return displayCard1P2;
    }

    public void setDisplayCard1P2(PalaceCard displayCard1P2) {
        this.displayCard1P2 = displayCard1P2;
    }

    public PalaceCard getDisplayCard2P2() {
        return displayCard2P2;
    }

    public void setDisplayCard2P2(PalaceCard displayCard2P2) {
        this.displayCard2P2 = displayCard2P2;
    }

    public PalaceCard getDisplayCard3P2() {
        return displayCard3P2;
    }

    public void setDisplayCard3P2(PalaceCard displayCard3P2) {
        this.displayCard3P2 = displayCard3P2;
    }

    //clear methods for selected cards and play pile cards ArrayLists

    public void clearSelectedCards() {
        this.selectedPalaceCards.clear();
    }

    public void clearPlayPileCards() {
        this.playPilePalaceCards.clear();
        playPileNumCards = 0;
    }

    //adders and removers for Palace card ArrayLists

    public ArrayList<PalaceCard> addToPlayPile(PalaceCard add) {
        playPilePalaceCards.add(add);
        ArrayList<PalaceCard> temp = new ArrayList<PalaceCard>();
        for (int i = 0; i < playPilePalaceCards.size(); i++) {
            temp.add(new PalaceCard(playPilePalaceCards.get(i)));
        }
        return temp;
    }

    public ArrayList<PalaceCard> addToP1Hand(PalaceCard add) {
        p1Hand.add(add);
        ArrayList<PalaceCard> temp = new ArrayList<PalaceCard>();
        for (int i = 0; i < p1Hand.size(); i++) {
            temp.add(new PalaceCard(p1Hand.get(i)));
        }
        return temp;
    }

    public void addToP2Hand(PalaceCard add) {
        p2Hand.add(new PalaceCard(add));
    }

    public void addToP1TopCards(PalaceCard add) {
        p1TopPalaceCards.add(new PalaceCard(add));
    }

    public void addToP2TopCards(PalaceCard add) {
        p2TopPalaceCards.add(new PalaceCard(add));
    }

    public void addToP1Bottom(PalaceCard add) {
        p1BottomPalaceCards.add(new PalaceCard(add));
    }

    public void addToP2Bottom(PalaceCard add) {
        p2BottomPalaceCards.add(new PalaceCard(add));
    }

    public ArrayList<PalaceCard> addToSelectedCards(PalaceCard add) {
        selectedPalaceCards.add(add);
        ArrayList<PalaceCard> temp = new ArrayList<PalaceCard>();
        for (int i = 0; i < selectedPalaceCards.size(); i++) {
            temp.add(new PalaceCard(selectedPalaceCards.get(i)));
        }
        return temp;
    }

    public ArrayList<PalaceCard> removeFromPlayPile(PalaceCard remove) {
        playPilePalaceCards.remove(remove);
        ArrayList<PalaceCard> temp = new ArrayList<PalaceCard>();
        for (int i = 0; i < playPilePalaceCards.size(); i++) {
            temp.add(new PalaceCard(playPilePalaceCards.get(i)));
        }
        return temp;
    }

    public ArrayList<PalaceCard> removeFromPlayPile(int remove) {
        playPilePalaceCards.remove(remove);
        ArrayList<PalaceCard> temp = new ArrayList<PalaceCard>();
        for (int i = 0; i < playPilePalaceCards.size(); i++) {
            temp.add(new PalaceCard(playPilePalaceCards.get(i)));
        }
        return temp;
    }

    public ArrayList<PalaceCard> removeFromP1Hand(PalaceCard remove) {
        p1Hand.remove(remove);
        ArrayList<PalaceCard> temp = new ArrayList<PalaceCard>();
        for (int i = 0; i < p1Hand.size(); i++) {
            temp.add(new PalaceCard(p1Hand.get(i)));
        }
        return temp;
    }

    public ArrayList<PalaceCard> removeFromP1Hand(int remove) {
        p1Hand.remove(remove);
        ArrayList<PalaceCard> temp = new ArrayList<PalaceCard>();
        for (int i = 0; i < p1Hand.size(); i++) {
            temp.add(new PalaceCard(p1Hand.get(i)));
        }
        return temp;
    }

    public void removeFromP2Hand(PalaceCard remove) {
        p2Hand.remove(remove);
    }

    public ArrayList<PalaceCard> removeFromP2Hand(int remove) {
        p2Hand.remove(remove);
        ArrayList<PalaceCard> temp = new ArrayList<PalaceCard>();
        for (int i = 0; i < p2Hand.size(); i++) {
            temp.add(new PalaceCard(p2Hand.get(i)));
        }
        return temp;
    }

    public void removeFromP1TopCards(PalaceCard remove) {
        p1TopPalaceCards.remove(remove);
    }

    public void removeFromP1TopCards(int remove) {
        p1TopPalaceCards.remove(remove);
    }

    public void removeFromP2TopCards(PalaceCard remove) {
        p2TopPalaceCards.remove(remove);
    }

    public void removeFromP2TopCards(int remove) {
        p2TopPalaceCards.remove(remove);
    }

    public void removeFromP1Bottom(int remove) {
        p1BottomPalaceCards.remove(remove);
    }

    public void removeFromP1Bottom(PalaceCard remove) {
        p1BottomPalaceCards.remove(remove);
    }

    public void removeFromP2Bottom(int remove) {
        p2BottomPalaceCards.remove(remove);
    }

    public void removeFromP2Bottom(PalaceCard remove) {
        p2BottomPalaceCards.remove(remove);
    }

    public ArrayList<PalaceCard> removeFromSelectedCards(PalaceCard remove) {
        selectedPalaceCards.remove(remove);
        ArrayList<PalaceCard> temp = new ArrayList<PalaceCard>();
        for (int i = 0; i < selectedPalaceCards.size(); i++) {
            temp.add(new PalaceCard(selectedPalaceCards.get(i)));
        }
        return temp;
    }
}
