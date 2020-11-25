package com.example.palace.game.palace;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: Chloe Gan, Nathaniel Pon, Jimi Hayes, Caroline Cavaliere
 *
 * This class models the attributes of a Card
 *
 * CAVEATS: None
 *
 * External Citation:
 *  Date: 1 October 2020
 *  Problem: Best way to model a card
 *  Resource: https://www.youtube.com/watch?v=_AUtutrnEP8
 *  Solution: Used example code from the video
 */
public class PalaceCard extends ArrayList<PalaceCard> {
    public int suit;
    public int rank;

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
     *
     * CAVEAT: None
     *
     *         // 1 = diamond
     *         // 2 = heart
     *         // 3 = club
     *         // 4 = spade
     * @return a List array of integers that symbolize the suit of cards
     */
    public static List<Integer> getValidSuits() {
        return Arrays.asList(1,2,3,4);
    }

    /**
     * This method will return a list of valid ranks for card object
     * static because it is generic for all card decks
     *
     * CAVEAT: None
     *
     *         // 11 = jack
     *         // 12 = queen
     *         // 13 = king
     *         // 14 = ace
     * @return a list array of integers for the rank of cards
     */
    public static List<Integer> getValidRank() {
        return Arrays.asList(2,3,4,5,6,7,8,9,10,11,12,13,14);
    }

    /**
     * This is the constructor for a card object
     * CAVEATS: None
     * @param suit of a card
     * @param rank of a card
     */
    public PalaceCard(int suit, int rank) { // card constructor
        setSuit(suit);
        setRank(rank);
    }

    /**
     * Determines if the object is a card object
     * CAVEATS: None
     * @param O
     * @return true if the parameter object is an instance of PalaceCard
     */
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
     * CAVEATS: None
     * @param orig, an instance of itself
     */
    public PalaceCard(PalaceCard orig) { // deep state constructor
        this.suit = orig.getSuit();
        this.rank = orig.getRank();
    }

    /**
     * Print's out the card rank and suit
     * CAVEATS: None
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
