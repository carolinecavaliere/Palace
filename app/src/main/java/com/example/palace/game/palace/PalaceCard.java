package com.example.palace.game.palace;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
 * Citation: https://www.youtube.com/watch?v=_AUtutrnEP8
 * This class models the attributes of a Card
 */
public class PalaceCard extends ArrayList<PalaceCard> {
    private int suit;
    private int rank;
    //private Image image;

    // getter for suit
    public int getSuit() { return suit; }
    // setter for suit
    public void setSuit(int suit) {
        List<Integer> validSuits = getValidSuits();
        if(validSuits.contains(suit)) {
            this.suit = suit;
        } else {
            throw new IllegalArgumentException("Valid Suits are: " + validSuits);
        }
    }

    // getter for card rank
    public int getRank() { return rank; }

    // setter for card rank
    public void setRank(int rank) {
        List<Integer> validRank = getValidRank();
        if(validRank.contains(rank)) {
            this.rank = rank;
        } else {
            throw new IllegalArgumentException("Valid Ranks are: " + validRank);
        }
    }

    /**
     * This method will return a list of valid suits for card object
     * static because it is generic for all card decks
     *         // 1 = diamond
     *         // 2 = heart
     *         // 3 = club
     *         // 4 = spade
     * @return
     */
    public static List<Integer> getValidSuits() {
        return Arrays.asList(1,2,3,4);
    }

    /**
     * This method will return a list of valid ranks for card object
     * static because it is generic for all card decks
     *         // 11 = jack
     *         // 12 = queen
     *         // 13 = king
     *         // 14 = ace
     * @return
     */
    public static List<Integer> getValidRank() {
        return Arrays.asList(2,3,4,5,6,7,8,9,10,11,12,13,14,15);
    }

    /**
     *
     * @param suit
     * @param rank
     */
    public PalaceCard(int suit, int rank) { // card constructor
        setSuit(suit);
        setRank(rank);
    }

    @Override
    public boolean equals(Object O){
        if (O instanceof PalaceCard) {
            PalaceCard c = (PalaceCard)O;
            if(this.rank == c.rank && this.suit == c.suit){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return this.equals(O);
        }
    }

    /**
     * Deep state copy constructor
     * @param orig
     */
    public PalaceCard(PalaceCard orig) { // deep state constructor
        this.suit = orig.getSuit();
        this.rank = orig.getRank();
    }

    /**
     * Print's out the card rank and suit
     * @return String
     */
    public String toString() {
        String rank;
        String suit;
        if(getRank()<=10){
            rank = ""+getRank();
        }
        else if(getRank()==11){
            rank = "Jack";
        }
        else if(getRank()==12){
            rank = "Queen";
        }
        else if(getRank()==13){
            rank = "King";
        }
        else{
            rank = "Ace";
        }

        if(getSuit() == 1){
            suit = "Diamonds";
        }
        else if(getSuit()==2){
            suit = "Hearts";
        }
        else if(getSuit()==3){
            suit = "Clubs";
        }
        else{
            suit = "Spades";
        }

        return rank+" of "+suit;
    }

    @NonNull
    @Override
    public Stream<PalaceCard> stream() {
        return null;
    }

    @NonNull
    @Override
    public Stream<PalaceCard> parallelStream() {
        return null;
    }
}
