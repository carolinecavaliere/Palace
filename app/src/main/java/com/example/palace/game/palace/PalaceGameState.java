package com.example.palace.game.palace;

import android.util.Log;

import com.example.palace.game.infoMsg.GameState;

import java.util.ArrayList;

/**
 * @Author: Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
 * 10/20/2020
 */
public class PalaceGameState extends GameState {

    private int numPlayers;

    private int p1numCards;
    private int p2numCards;
    private int p3numCards;
    private int p4numCards;

    private ArrayList<PalaceCard> p1Hand;
    private ArrayList<PalaceCard> p2Hand;
    private ArrayList<PalaceCard> p3Hand;
    private ArrayList<PalaceCard> p4Hand;

    private int turn;

    private PalaceDeckOfCards deck;

    private ArrayList<PalaceCard> p1TopPalaceCards;
    private ArrayList<PalaceCard> p1BottomPalaceCards;

    private ArrayList<PalaceCard> p2TopPalaceCards;
    private ArrayList<PalaceCard> p2BottomPalaceCards;

    private ArrayList<PalaceCard> p3TopPalaceCards;
    private ArrayList<PalaceCard> p3BottomPalaceCards;

    private ArrayList<PalaceCard> p4TopPalaceCards;
    private ArrayList<PalaceCard> p4BottomPalaceCards;

    private ArrayList<PalaceCard> playPilePalaceCards;

    private ArrayList<PalaceCard> selectedPalaceCards; // array of selected cards
    private PalaceCard cardToBeSelected;
    private PalaceCard topCardSelected;

    private PalaceCard drawPileTopPalaceCard;
    private PalaceCard playPileTopPalaceCard;

    private int drawPileNumCards;

    private int playPileNumCards;

    public PalaceGameState(int numPlayers)
    {
        this.numPlayers = numPlayers;
        turn = 1;
        playPilePalaceCards = new ArrayList<PalaceCard>();

        p1TopPalaceCards = new ArrayList<PalaceCard>();
        p1BottomPalaceCards = new ArrayList<PalaceCard>();

        p2TopPalaceCards = new ArrayList<PalaceCard>();
        p2BottomPalaceCards = new ArrayList<PalaceCard>();

        p3TopPalaceCards = new ArrayList<PalaceCard>();
        p3BottomPalaceCards = new ArrayList<PalaceCard>();

        p4TopPalaceCards = new ArrayList<PalaceCard>();
        p4BottomPalaceCards = new ArrayList<PalaceCard>();

        p1Hand = new ArrayList<PalaceCard>();
        p2Hand = new ArrayList<PalaceCard>();
        p3Hand = new ArrayList<PalaceCard>();
        p4Hand = new ArrayList<PalaceCard>();

        selectedPalaceCards = new ArrayList<PalaceCard>();

        if(this.numPlayers<=2)
        {
            deck = new PalaceDeckOfCards(1, this);
        }
        else
        {
            deck= new PalaceDeckOfCards(2, this);
        }

        deck.DealDeck();
    }

    public PalaceGameState(PalaceGameState orig)//copy constructor
    {
        this.numPlayers = orig.getNumPlayers();
        this.p1numCards = orig.getP1numCards();
        this.p2numCards = orig.getP2numCards();
        this.p3numCards = orig.getP3numCards();
        this.p4numCards = orig.getP4numCards();

        this.p1Hand = new ArrayList<PalaceCard>();
        this.p2Hand = new ArrayList<PalaceCard>();
        this.p3Hand = new ArrayList<>(orig.getP3Hand().size());
        this.p4Hand = new ArrayList<>(orig.getP4Hand().size());

        this.p1TopPalaceCards = new ArrayList<PalaceCard>();
        this.p2TopPalaceCards = new ArrayList<PalaceCard>();
        this.p3TopPalaceCards = new ArrayList<>(orig.getP3TopPalaceCards().size());
        this.p4TopPalaceCards = new ArrayList<>(orig.getP4TopPalaceCards().size());

        this.p1BottomPalaceCards = new ArrayList<PalaceCard>();
        this.p2BottomPalaceCards = new ArrayList<PalaceCard>();
        this.p3BottomPalaceCards = new ArrayList<>(orig.getP3BottomPalaceCards().size());
        this.p4BottomPalaceCards = new ArrayList<>(orig.getP4BottomPalaceCards().size());

        this.selectedPalaceCards = new ArrayList<PalaceCard>(orig.getSelectedPalaceCards().size());

        for(int i=0; i< orig.getP1Hand().size() ; i++)
        {
            this.p1Hand.add(new PalaceCard(orig.getP1Hand().get(i)));
        }
        for(int i=0; i<orig.getP2Hand().size() ; i++)
        {
            this.p2Hand.add(new PalaceCard(orig.getP2Hand().get(i)));
        }
        for(int i=0; i<orig.getP3Hand().size() ; i++)
        {
            this.p3Hand.add(new PalaceCard(orig.getP3Hand().get(i)));
        }
        for(int i=0; i<orig.getP4Hand().size() ; i++)
        {
            this.p4Hand.add(new PalaceCard(orig.getP4Hand().get(i)));
        }

        this.turn = orig.getTurn();

        this.deck = new PalaceDeckOfCards(1,orig);

        for(int i = 0; i<orig.getP1TopPalaceCards().size() ; i++)
        {
            this.p1TopPalaceCards.add(new PalaceCard(orig.getP1TopPalaceCards().get(i)));
        }
        for(int i = 0; i<orig.getP2TopPalaceCards().size() ; i++)
        {
            this.p2TopPalaceCards.add(new PalaceCard(orig.getP2TopPalaceCards().get(i)));
        }
        for(int i = 0; i<orig.getP3TopPalaceCards().size() ; i++)
        {
            this.p3TopPalaceCards.add(new PalaceCard(orig.getP3TopPalaceCards().get(i)));
        }
        for(int i = 0; i<orig.getP4TopPalaceCards().size() ; i++)
        {
            this.p4TopPalaceCards.add(new PalaceCard(orig.getP4TopPalaceCards().get(i)));
        }

        for(int i = 0; i<orig.getP1BottomPalaceCards().size() ; i++)
        {
            this.p1BottomPalaceCards.add(new PalaceCard(orig.getP1BottomPalaceCards().get(i)));
        }
        for(int i = 0; i<orig.getP2BottomPalaceCards().size() ; i++)
        {
            this.p2BottomPalaceCards.add(new PalaceCard(orig.getP2BottomPalaceCards().get(i)));
        }
        for(int i = 0; i<orig.getP3BottomPalaceCards().size() ; i++)
        {
            this.p3BottomPalaceCards.add(new PalaceCard(orig.getP3BottomPalaceCards().get(i)));
        }
        for(int i = 0; i<orig.getP4BottomPalaceCards().size() ; i++)
        {
            this.p4BottomPalaceCards.add(new PalaceCard(orig.getP4BottomPalaceCards().get(i)));
        }

        for (int i = 0; i<orig.getSelectedPalaceCards().size(); i++) {
            this.selectedPalaceCards.add(new PalaceCard(orig.getSelectedPalaceCards().get(i)));
        }

        drawPileTopPalaceCard = new PalaceCard(orig.getDrawPileTopPalaceCard());
        playPileTopPalaceCard = new PalaceCard(orig.getPlayPileTopPalaceCard());
        playPilePalaceCards = orig.getPlayPilePalaceCards();

        this.drawPileNumCards = orig.getDrawPileNumCards();
        this.playPileNumCards = orig.getPlayPileNumCards();

        Log.d("GameState","Gamestate successfully created.");
    }

    //getters and setters

    public ArrayList<PalaceCard> getSelectedPalaceCards() {
        return selectedPalaceCards;
    }

    public void setSelectedPalaceCards(ArrayList<PalaceCard> selectedPalaceCards) {
        this.selectedPalaceCards = new ArrayList<PalaceCard>(selectedPalaceCards);
    }
    public void clearSelectedCards() {
        this.selectedPalaceCards.clear();
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

    public void setP3numCards(int p3numCards) {
        this.p3numCards = p3numCards;
    }

    public int getP4numCards() {
        return p4numCards;
    }

    public void setP4numCards(int p4numCards) {
        this.p4numCards = p4numCards;
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

    public ArrayList<PalaceCard> getP3Hand() {
        return p3Hand;
    }

    public void setP3Hand(ArrayList<PalaceCard> p3Hand) {
        this.p3Hand = new ArrayList<PalaceCard>(p3Hand);
    }

    public ArrayList<PalaceCard> getP4Hand() {
        return p4Hand;
    }

    public void setP4Hand(ArrayList<PalaceCard> p4Hand) {
        this.p4Hand = new ArrayList<PalaceCard>(p4Hand);
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
    public void clearPlayPileCards(){
        this.playPilePalaceCards.clear();
        this.playPileTopPalaceCard = null;
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

    public ArrayList<PalaceCard> getP3TopPalaceCards() {
        return p3TopPalaceCards;
    }

    public void setP3TopPalaceCards(ArrayList<PalaceCard> p3TopPalaceCards) {
        this.p3TopPalaceCards = new ArrayList<PalaceCard>(p3TopPalaceCards);
    }

    public ArrayList<PalaceCard> getP3BottomPalaceCards() {
        return p3BottomPalaceCards;
    }

    public void setP3BottomPalaceCards(ArrayList<PalaceCard> p3BottomPalaceCards) {
        this.p3BottomPalaceCards = new ArrayList<PalaceCard>(p3BottomPalaceCards);
    }

    public ArrayList<PalaceCard> getP4TopPalaceCards() {
        return p4TopPalaceCards;
    }

    public void setP4TopPalaceCards(ArrayList<PalaceCard> p4TopPalaceCards) {
        this.p4TopPalaceCards = new ArrayList<PalaceCard>(p4TopPalaceCards);
    }

    public ArrayList<PalaceCard> getP4BottomPalaceCards() {
        return p4BottomPalaceCards;
    }

    public void setP4BottomPalaceCards(ArrayList<PalaceCard> p4BottomPalaceCards) {
        this.p4BottomPalaceCards = new ArrayList<PalaceCard>(p4BottomPalaceCards);
    }

    public PalaceCard getDrawPileTopPalaceCard() {
        return drawPileTopPalaceCard;
    }

    public void setDrawPileTopPalaceCard(PalaceCard drawPileTopPalaceCard) {
        this.drawPileTopPalaceCard = drawPileTopPalaceCard;
    }

    public PalaceCard getPlayPileTopPalaceCard() {
        return playPileTopPalaceCard;
    }

    public void setPlayPileTopPalaceCard(PalaceCard playPileTopPalaceCard) {
        this.playPileTopPalaceCard = playPileTopPalaceCard;
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

    public PalaceCard getCardToBeSelected(){
        return cardToBeSelected;
    }
    public void setCardToBeSelected(PalaceCard card){
        this.cardToBeSelected = card;
    }

    public PalaceCard getTopCardSelected() {
        return topCardSelected;
    }

    public void setTopCardSelected(PalaceCard topCardSelected) {
        this.topCardSelected = topCardSelected;
    }

    //adders and removers
    public void addToPlayPile(PalaceCard add){
        playPilePalaceCards.add(add);
    }

    public void addToP1Hand(PalaceCard add){
        p1Hand.add(add);
    }

    public void addToP2Hand(PalaceCard add){
        p2Hand.add(add);
    }

    public void addToP3Hand(PalaceCard add){
        p3Hand.add(add);
    }

    public void addToP4Hand(PalaceCard add){
        p4Hand.add(add);
    }

    public void addToP1TopCards(PalaceCard add){
        p1TopPalaceCards.add(add);
    }

    public void addToP2TopCards(PalaceCard add){
        p2TopPalaceCards.add(add);
    }

    public void addToP3TopCards(PalaceCard add){
        p3TopPalaceCards.add(add);
    }

    public void addToP4TopCards(PalaceCard add){
        p4TopPalaceCards.add(add);
    }

    public void addToP1Bottom(PalaceCard add){
        p1BottomPalaceCards.add(add);
    }

    public void addToP2Bottom(PalaceCard add){
        p2BottomPalaceCards.add(add);
    }

    public void addToP3Bottom(PalaceCard add){
        p3BottomPalaceCards.add(add);
    }

    public void addToP4Bottom(PalaceCard add){
        p4BottomPalaceCards.add(add);
    }

    public void addToSelectedCards(PalaceCard add){
        selectedPalaceCards.add(add);
    }

    public void removeFromPlayPile(PalaceCard remove){
        playPilePalaceCards.remove(remove);
    }

    public void removeFromPlayPile(int remove){
        playPilePalaceCards.remove(remove);
    }

    public void removeFromP1Hand(PalaceCard remove){
        p1Hand.remove(remove);
    }

    public void removeFromP2Hand(PalaceCard remove){
        p2Hand.remove(remove);
    }

    public void removeFromP3Hand(PalaceCard remove){
        p3Hand.remove(remove);
    }

    public void removeFromP4Hand(PalaceCard remove){
        p4Hand.remove(remove);
    }

    public void removeFromP1TopCards(PalaceCard remove){
        p1TopPalaceCards.remove(remove);
    }

    public void removeFromP2TopCards(PalaceCard remove){
        p2TopPalaceCards.remove(remove);
    }

    public void removeFromP3TopCards(PalaceCard remove){
        p3TopPalaceCards.remove(remove);
    }

    public void removeFromP4TopCards(PalaceCard remove){
        p4TopPalaceCards.remove(remove);
    }

    public void removeFromP1Bottom(PalaceCard remove){
        p1BottomPalaceCards.remove(remove);
    }

    public void removeFromP2Bottom(PalaceCard remove){
        p2BottomPalaceCards.remove(remove);
    }

    public void removeFromP3Bottom(PalaceCard remove){
        p3BottomPalaceCards.remove(remove);
    }

    public void removeFromP4Bottom(PalaceCard remove){
        p4BottomPalaceCards.remove(remove);
    }

    public void removeFromSelectedCards(PalaceCard remove){
        selectedPalaceCards.remove(remove);
    }


    public String toString() {
        String ret =  "Number of Players: " + numPlayers + "\n" +
                "Next Card in the Draw Pile: " + drawPileTopPalaceCard + "\n" +
                "Number of Cards in Play Pile: " + playPileNumCards + "\n" +
                "Current Card in Play Pile: " + playPileTopPalaceCard + "\n" +

                "Player 1: \n"  +
                "Number of Cards in Hand: " + p1numCards + "\n" +
                "Cards in Hand: " + p1Hand.toString() + "\n" +
                "Bottom Cards: " + p1BottomPalaceCards.toString() + "\n" +
                "Top Cards: " + p1TopPalaceCards.toString() + "\n" +

                "Player 2: " + "\n" +
                "Number of Cards in Hand: " + p2numCards + "\n" +
                "Cards in Hand: " + p2Hand.toString() + "\n" +
                "Bottom Cards: " + p2BottomPalaceCards.toString() + "\n" +
                "Top Cards: " + p2TopPalaceCards.toString() + "\n";

        if (numPlayers == 3) {
            ret = ret + "Player 3: " + "\n" +
                    "Number of Cards in Hand: " + p3numCards + "\n" +
                    "Cards in Hand: " + p3Hand.toString() + "\n" +
                    "Bottom Cards: " + p3BottomPalaceCards.toString() + "\n" +
                    "Top Cards: " + p3TopPalaceCards.toString() + "\n";

        } else if (numPlayers == 4) {
            ret = ret + "Player 4: " + "\n" +
                    "Number of Cards in Hand: " + p4numCards + "\n" +
                    "Cards in Hand: " + p4Hand.toString() + "\n" +
                    "Bottom Cards: " + p4BottomPalaceCards.toString() + "\n" +
                    "Top Cards: " + p4TopPalaceCards.toString() + "\n";
        }
        return ret;
    }
}
