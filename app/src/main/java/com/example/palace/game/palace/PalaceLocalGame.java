package com.example.palace.game.palace;

import android.util.Log;

import com.example.palace.game.GamePlayer;
import com.example.palace.game.LocalGame;
import com.example.palace.game.actionMsg.GameAction;
import com.example.palace.game.infoMsg.GameState;

import java.util.ArrayList;

/**
 * @author Jimi Hayes, Nathaniel Pon, Caroline Cavaliere, Chloe Gan
 * <p>
 * controls the play of the game
 * <p>
 * CAVEATS: None
 */
public class PalaceLocalGame extends LocalGame {

    private PalaceGameState palaceGame;

    /**
     * constructor makes a new game states
     * <p>
     * CAVEATS: None
     *
     * @param pNum
     */
    public PalaceLocalGame(int pNum) {
        palaceGame = new PalaceGameState(pNum);
    }

    /**
     * sends updated state to player
     * <p>
     * CAVEATS: None
     *
     * @param p player being sent the updated state
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PalaceGameState game = new PalaceGameState(palaceGame);
        p.sendInfo(game);
    }

    /**
     * indicates whether the given player can take an action right now.
     * <p>
     * CAVEATS: None
     *
     * @param playerIdx the player's player-number (ID)
     * @return true if the player can move, false if they can't move
     */
    @Override
    protected boolean canMove(int playerIdx) {

        if (palaceGame.getTurn() == playerIdx) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks if the game is over
     * <p>
     * CAVEATS: None
     *
     * @return the message that tells who has won, or null if it's not over yet
     */
    @Override
    protected String checkIfGameOver() {
        if (palaceGame.getP1TopPalaceCards().isEmpty()) {
            return "" + this.playerNames[0] + " has won!!";
        } else if (palaceGame.getP2TopPalaceCards().isEmpty()) {
            return "" + this.playerNames[1] + " has won!!";
        } else {
            return null;
        }
    }

    /**
     * method called when a new action is sent by a player
     * <p>
     * CAVEATS: None
     *
     * @param action The move that the player has sent to the game
     * @return true if an action has been taken
     */
    @Override
    protected boolean makeMove(GameAction action) {

        // A user decides to play their selected card
        if (action instanceof PalacePlayCardAction) {
            boolean changeTurn = false;
            //not a valid move if a card isn't selected
            if (palaceGame.getSelectedPalaceCards().isEmpty()) {
                return false;
            }
            else if (palaceGame.getSelectedPalaceCards().size() > 1) {
                int sameCards = 0;
                for (int i = 0; i < palaceGame.getSelectedPalaceCards().size(); i++) {
                    if (palaceGame.getTurn() == 0) {
                        if (palaceGame.getP1Hand().size() > 0) {
                            int index = palaceGame.getP1Hand().indexOf(palaceGame.
                                    getSelectedPalaceCards().get(i));
                            //adds selected cards to play pile
                            palaceGame.addToPlayPile(palaceGame.getSelectedPalaceCards().get(i));
                            //removes played cards from player's hand
                            palaceGame.setP1Hand(palaceGame.removeFromP1Hand(index));
                            palaceGame.setPlayPileNumCards(palaceGame.getPlayPileNumCards() + 1);
                            palaceGame.setP1numCards(palaceGame.getP1numCards() - 1);
                            //adds new card to the player's hand
                            if (palaceGame.getP1Hand().size() < 3) {
                                for (int j = palaceGame.getP1numCards(); j < 3; j++) {
                                    palaceGame.getDeck().drawCard(0);
                                    palaceGame.setP1numCards(palaceGame.getP1Hand().size());
                                }

                            }
                        } else if (palaceGame.getP1Hand().isEmpty() &&
                                !palaceGame.getP1TopPalaceCards().isEmpty()) {
                            int index = palaceGame.getP1TopPalaceCards().indexOf(palaceGame.
                                    getSelectedPalaceCards().get(i));
                            //adds selected cards to play pile
                            palaceGame.addToPlayPile(palaceGame.getSelectedPalaceCards().get(i));
                            //removes played cards from player's Top Cards
                            palaceGame.removeFromP1TopCards(index);
                            palaceGame.setPlayPileNumCards(palaceGame.getPlayPileNumCards() + 1);

                        }
                    }
                    for (int k = 0; k < palaceGame.getPlayPileNumCards(); k++) {
                        if (palaceGame.getPlayPilePalaceCards().get(k).getRank() ==
                                palaceGame.getSelectedPalaceCards().get(0).getRank()) {
                            sameCards++;
                        }
                    }
                    if (palaceGame.getSelectedPalaceCards().get(i).getRank() == 10 ||
                            sameCards == 4) {
                        palaceGame.clearPlayPileCards();
                    } else {
                        changeTurn = true;
                    }
                }
                if (changeTurn) {
                    if (palaceGame.getTurn() == palaceGame.getNumPlayers() - 1) {
                        palaceGame.setTurn(0);
                    } else {
                        palaceGame.setTurn(palaceGame.getTurn() + 1);
                    }
                    changeTurn = false;
                }
                //no more cards are selected now
                palaceGame.clearSelectedCards();
                return true;
            }
            else {
                for (int i = 0; i < palaceGame.getSelectedPalaceCards().size(); i++) {
                    if (palaceGame.getTurn() == 0) {
                        if (palaceGame.getP1Hand().size() > 0) {
                            int index = palaceGame.getP1Hand().indexOf(palaceGame.
                                    getSelectedPalaceCards().get(i));
                            int sameCards = 0;
                            //adds selected cards to play pile
                            palaceGame.addToPlayPile(palaceGame.getSelectedPalaceCards().get(i));
                            //removes played cards from player's hand
                            palaceGame.setP1Hand(palaceGame.removeFromP1Hand(index));
                            palaceGame.setPlayPileNumCards(palaceGame.getPlayPileNumCards() + 1);
                            palaceGame.setP1numCards(palaceGame.getP1numCards() - 1);
                            //adds new card to the player's hand
                            if (palaceGame.getP1Hand().size() < 3) {
                                for (int j = palaceGame.getP1numCards(); j < 3; j++) {
                                    palaceGame.getDeck().drawCard(0);
                                    palaceGame.setP1numCards(palaceGame.getP1Hand().size());
                                }

                            }
                            for (int k = 0; k < palaceGame.getPlayPileNumCards(); k++) {
                                if (palaceGame.getPlayPilePalaceCards().get(k).getRank() ==
                                        palaceGame.getSelectedPalaceCards().get(0).getRank()) {
                                    sameCards++;
                                }
                            }
                            if (palaceGame.getSelectedPalaceCards().get(i).getRank() == 10 ||
                                    sameCards == 4) {
                                palaceGame.clearPlayPileCards();
                            } else {
                                changeTurn = true;
                            }
                            //no more cards are selected now
                            palaceGame.clearSelectedCards();

                        } else if (palaceGame.getP1Hand().isEmpty() &&
                                !palaceGame.getP1TopPalaceCards().isEmpty()) {
                            int index = palaceGame.getP1TopPalaceCards().indexOf(palaceGame.
                                    getSelectedPalaceCards().get(i));
                            int sameCards = 0;
                            //adds selected cards to play pile
                            palaceGame.addToPlayPile(palaceGame.getSelectedPalaceCards().get(i));
                            //removes played cards from player's Top Cards
                            palaceGame.removeFromP1TopCards(index);
                            palaceGame.setPlayPileNumCards(palaceGame.getPlayPileNumCards() + 1);
                            palaceGame.clearSelectedCards();

                        }
                    } else if (palaceGame.getTurn() == 1) {
                        if (palaceGame.getP2Hand().size() > 0) {
                            int index = palaceGame.getP2Hand().indexOf(palaceGame.
                                    getSelectedPalaceCards().get(i));
                            int sameCards = 0;
                            //adds selected cards to play pile
                            palaceGame.addToPlayPile(palaceGame.getSelectedPalaceCards().get(i));
                            //removes played cards from player's hand
                            palaceGame.setP2Hand(palaceGame.removeFromP2Hand(index));
                            palaceGame.setPlayPileNumCards(palaceGame.getPlayPileNumCards() + 1);
                            palaceGame.setP2numCards(palaceGame.getP2numCards() - 1);
                            //adds new card to the player's hand
                            if (palaceGame.getP2Hand().size() < 3) {
                                for (int j = palaceGame.getP2numCards(); j < 3; j++) {
                                    palaceGame.getDeck().drawCard(1);
                                    palaceGame.setP2numCards(palaceGame.getP2Hand().size());
                                }

                            }
                            for (int k = 0; k < palaceGame.getPlayPileNumCards(); k++) {
                                if (palaceGame.getPlayPilePalaceCards().get(k).getRank() ==
                                        palaceGame.getSelectedPalaceCards().get(0).getRank()) {
                                    sameCards++;
                                }
                            }
                            if (palaceGame.getSelectedPalaceCards().get(i).getRank() == 10 ||
                                    sameCards == 4) {
                                palaceGame.clearPlayPileCards();
                            } else {
                                changeTurn = true;
                            }
                            //no more cards are selected now
                            palaceGame.clearSelectedCards();

                        } else if (palaceGame.getP2Hand().isEmpty() &&
                                !palaceGame.getP2TopPalaceCards().isEmpty()) {
                            int index = palaceGame.getP2TopPalaceCards().indexOf(palaceGame.
                                    getSelectedPalaceCards().get(i));
                            int sameCards = 0;
                            //adds selected cards to play pile
                            palaceGame.addToPlayPile(palaceGame.getSelectedPalaceCards().get(i));
                            //removes played cards from player's Top Cards
                            palaceGame.removeFromP2TopCards(index);
                            palaceGame.setPlayPileNumCards(palaceGame.getPlayPileNumCards() + 1);
                            palaceGame.clearSelectedCards();
                        }
                    } else if (palaceGame.getTurn() == 2) {
                        if (palaceGame.getP3numCards() > 0) {
                            int index = palaceGame.getP3Hand().indexOf(palaceGame.
                                    getSelectedPalaceCards().get(i));
                            int sameCards = 0;
                            //adds selected cards to play pile
                            palaceGame.addToPlayPile(palaceGame.getSelectedPalaceCards().get(i));
                            //removes played cards from player's hand
                            palaceGame.setP3Hand(palaceGame.removeFromP3Hand(index));
                            palaceGame.setPlayPileNumCards(palaceGame.getPlayPileNumCards() + 1);
                            palaceGame.setP1numCards(palaceGame.getP1numCards() - 1);
                            //adds new card to the player's hand
                            if (palaceGame.getP1Hand().size() < 3) {
                                for (int j = palaceGame.getP1numCards(); j < 3; j++) {
                                    palaceGame.getDeck().drawCard(2);
                                }
                                palaceGame.setP1numCards(palaceGame.getP3Hand().size());
                            }
                            for (int k = 0; k < palaceGame.getPlayPileNumCards(); k++) {
                                if (palaceGame.getPlayPilePalaceCards().get(k).getRank() ==
                                        palaceGame.getSelectedPalaceCards().get(0).getRank()) {
                                    sameCards++;
                                }
                            }
                            if (palaceGame.getSelectedPalaceCards().get(i).getRank() == 10 ||
                                    sameCards == 4) {
                                palaceGame.clearPlayPileCards();
                            }
                            //no more cards are selected now
                            palaceGame.clearSelectedCards();
                        } else if (palaceGame.getP1numCards() == 0 &&
                                palaceGame.getP1TopPalaceCards().isEmpty()) {
                            if (palaceGame.getPlayPileNumCards() >= 0) {
                                for (int j = 0; j < palaceGame.getPlayPileNumCards(); j++) {
                                    //adds pile to player's hand
                                    palaceGame.setP1Hand(palaceGame.addToP1Hand(palaceGame.
                                            getPlayPilePalaceCards().get(j)));
                                    palaceGame.setP1numCards(palaceGame.getP1numCards() + 1);
                                }
                                palaceGame.clearPlayPileCards();//play pile is gone now
                                palaceGame.setP1Hand(palaceGame.addToP1Hand(palaceGame.
                                        getSelectedPalaceCards().get(i)));
                            }
                        }
                    } else if (palaceGame.getTurn() == 3) {
                        palaceGame.addToPlayPile(palaceGame.getSelectedPalaceCards().get(i));
                        palaceGame.removeFromP4Hand(palaceGame.getSelectedPalaceCards().get(i));
                        palaceGame.setPlayPileNumCards(palaceGame.getPlayPileNumCards() + 1);
                        palaceGame.setP4numCards(palaceGame.getP4numCards() - 1);
                        if (palaceGame.getP4Hand().size() < 3) {
                            for (int j = palaceGame.getP4numCards(); j < 3; j++) {
                                palaceGame.getDeck().drawCard(3);
                            }
                            palaceGame.setP4numCards(palaceGame.getP4Hand().size());
                        }
                        palaceGame.clearSelectedCards();
                    }
                    if (changeTurn) {
                        if (palaceGame.getTurn() == palaceGame.getNumPlayers() - 1) {
                            palaceGame.setTurn(0);
                        } else {
                            palaceGame.setTurn(palaceGame.getTurn() + 1);
                        }
                        changeTurn = false;
                    }
                }
                return true;
            }
            // A user decides to select card(s)
        } else if (action instanceof PalaceSelectCardAction) {
            // instantiate a SelectCardAction object
            PalaceSelectCardAction select = (PalaceSelectCardAction) action;
            // retrieve the single card that was selected
            PalaceCard chosenCard = select.getCardSelected();
            // if the number of cards that were played are greater than zero...
            if (palaceGame.getPlayPileNumCards() > 0) {
                // looks at player 1's hand
                if (palaceGame.getTurn() == 0) { // this is player 1
                    if (palaceGame.getP1Hand().contains(chosenCard)) { // if their hand has the
                        // card that was selected...
                        //.. and if their selected card is greater than the most recent card in
                        // the play pile or is a special card...
                        if (chosenCard.getRank() >= palaceGame.getPlayPilePalaceCards().
                                get(palaceGame.getPlayPilePalaceCards().size() - 1).getRank() ||
                                (chosenCard.getRank() == 10 || chosenCard.getRank() == 2)) {
                            //... if the chosen card is not already in the arraylist of selected
                            // cards..
                            if (!palaceGame.getSelectedPalaceCards().contains(chosenCard)) {
                                if((!(palaceGame.getSelectedPalaceCards().isEmpty()))&&palaceGame.getSelectedPalaceCards()
                                        .get(0).getRank() != chosenCard.getRank()){
                                    return false;
                                }
                                else {
                                    // ...put arraylist into GameState variable
                                    palaceGame.setSelectedPalaceCards(palaceGame.
                                            addToSelectedCards(chosenCard));
                                }
                            } else {
                                palaceGame.removeFromSelectedCards(chosenCard);
                            }
                            sendUpdatedStateTo(players[0]);
                            return true;
                        } else {
                            return false;
                        }
                    }
                    //for top cards
                    else if (palaceGame.getP1Hand().isEmpty() &&
                            !palaceGame.getP1TopPalaceCards().isEmpty()) {
                        if (palaceGame.getP1TopPalaceCards().contains(chosenCard)) {
                            if (chosenCard.getRank() >= palaceGame.getPlayPilePalaceCards().
                                    get(palaceGame.getPlayPilePalaceCards().size() - 1).getRank() ||
                                    (chosenCard.getRank() == 10 || chosenCard.getRank() == 2)) {
                                if (!palaceGame.getSelectedPalaceCards().contains(chosenCard)) {
                                    if((!(palaceGame.getSelectedPalaceCards().isEmpty()))&&palaceGame.getSelectedPalaceCards()
                                            .get(0).getRank() != chosenCard.getRank()){
                                        return false;
                                    }
                                    else {
                                        // ...put arraylist into GameState variable
                                        palaceGame.setSelectedPalaceCards(palaceGame.
                                                addToSelectedCards(chosenCard));
                                    }
                                } else {
                                    palaceGame.removeFromSelectedCards(chosenCard);
                                }
                                sendUpdatedStateTo(players[0]);
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }

                    }
                    //for bottom cards
                    else if (palaceGame.getP1numCards() == 0 &&
                            palaceGame.getP1TopPalaceCards().isEmpty()) {
                        if (!(palaceGame.getSelectedPalaceCards().contains(chosenCard))) {
                            palaceGame.setSelectedPalaceCards(palaceGame.
                                    addToSelectedCards(chosenCard));
                        } else {
                            palaceGame.removeFromSelectedCards(chosenCard);
                        }
                        sendUpdatedStateTo(players[0]);
                        return true;
                    } else {
                        return false;
                    }
                }
                // looks at player 2's hand
                else if (palaceGame.getTurn() == 1 &&
                        palaceGame.getP2Hand().contains(chosenCard)) {
                    if (palaceGame.getP2Hand().contains(chosenCard)) {
                        if (chosenCard.getRank() >= palaceGame.getPlayPilePalaceCards().
                                get(palaceGame.getPlayPilePalaceCards().size() - 1).getRank() ||
                                (chosenCard.getRank() == 10 || chosenCard.getRank() == 2)) {
                            if (!palaceGame.getSelectedPalaceCards().contains(chosenCard)) {
                                if((!(palaceGame.getSelectedPalaceCards().isEmpty()))&&palaceGame.getSelectedPalaceCards()
                                        .get(0).getRank() != chosenCard.getRank()){
                                    return false;
                                }
                                else {
                                    // ...put arraylist into GameState variable
                                    palaceGame.setSelectedPalaceCards(palaceGame.
                                            addToSelectedCards(chosenCard));
                                }
                            } else {
                                palaceGame.removeFromSelectedCards(chosenCard);
                            }

                            sendUpdatedStateTo(players[1]);
                            return true;
                        } else {
                            return false;
                        }
                    }
                    //for top cards
                    else if (palaceGame.getP2Hand().isEmpty() &&
                            !palaceGame.getP2TopPalaceCards().isEmpty()) {
                        if (palaceGame.getP2TopPalaceCards().contains(chosenCard)) {
                            if (chosenCard.getRank() >= palaceGame.getPlayPilePalaceCards().
                                    get(palaceGame.getPlayPilePalaceCards().size() - 1).getRank() ||
                                    (chosenCard.getRank() == 10 || chosenCard.getRank() == 2)) {
                                if (!palaceGame.getSelectedPalaceCards().contains(chosenCard)) {
                                    if((!(palaceGame.getSelectedPalaceCards().isEmpty()))&&palaceGame.getSelectedPalaceCards()
                                            .get(0).getRank() != chosenCard.getRank()){
                                        return false;
                                    }
                                    else {
                                        // ...put arraylist into GameState variable
                                        palaceGame.setSelectedPalaceCards(palaceGame.
                                                addToSelectedCards(chosenCard));
                                    }
                                } else {
                                    palaceGame.removeFromSelectedCards(chosenCard);
                                }
                                sendUpdatedStateTo(players[1]);
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                    //for bottom cards
                    else if (palaceGame.getP2numCards() == 0 &&
                            palaceGame.getP2TopPalaceCards().isEmpty()) {
                        if (!palaceGame.getSelectedPalaceCards().contains(chosenCard)) {
                            palaceGame.setSelectedPalaceCards(palaceGame.
                                    addToSelectedCards(chosenCard));
                        } else {
                            palaceGame.removeFromSelectedCards(chosenCard);
                        }
                        sendUpdatedStateTo(players[1]);
                        return true;
                    } else {
                        return false;
                    }
                }
                // looks at player 3's hand
                else if (palaceGame.getTurn() == 2 &&
                        palaceGame.getP3Hand().contains(chosenCard)) {
                    if (chosenCard.getRank() >= palaceGame.getPlayPilePalaceCards().
                            get(palaceGame.getPlayPilePalaceCards().size() - 1).getRank()) {
                        return false;
                    } else if (!palaceGame.getSelectedPalaceCards().contains(chosenCard)) {
                        // put arraylist into GameState variable
                        palaceGame.setSelectedPalaceCards(palaceGame.
                                addToSelectedCards(chosenCard));
                    } else {
                        palaceGame.removeFromSelectedCards(chosenCard);
                    }
                    return true;
                }
                // looks at player 4's hand
                else if (palaceGame.getTurn() == 3 &&
                        palaceGame.getP4Hand().contains(chosenCard)) {
                    if (chosenCard.getRank() >= palaceGame.getPlayPilePalaceCards().
                            get(palaceGame.getPlayPilePalaceCards().size() - 1).getRank()) {
                        return false;
                    } else if (!palaceGame.getSelectedPalaceCards().contains(chosenCard)) {
                        // put arraylist into GameState variable
                        palaceGame.setSelectedPalaceCards(palaceGame.
                                addToSelectedCards(chosenCard));
                    } else {
                        palaceGame.removeFromSelectedCards(chosenCard);
                    }
                    return true;
                } else {
                    return false;
                }
            }
            //if play pile is empty
            else {
                // looks at player 1's hand
                if (palaceGame.getTurn() == 0) {
                    if (palaceGame.getP1Hand().contains(chosenCard)) {
                        if (!palaceGame.getSelectedPalaceCards().contains(chosenCard)) {
                            // put arraylist into GameState variable
                            palaceGame.setSelectedPalaceCards(palaceGame.
                                    addToSelectedCards(chosenCard));
                        } else {
                            palaceGame.removeFromSelectedCards(chosenCard);
                        }
                        return true;
                    } else if (palaceGame.getP1TopPalaceCards().contains(chosenCard)) {
                        if (!palaceGame.getSelectedPalaceCards().contains(chosenCard)) {
                            // put arraylist into GameState variable
                            palaceGame.setSelectedPalaceCards(palaceGame.
                                    addToSelectedCards(chosenCard));
                        } else {
                            palaceGame.removeFromSelectedCards(chosenCard);
                        }
                        return true;
                    } else {
                        return false;
                    }
                }

                // looks at player 2's hand
                else if (palaceGame.getTurn() == 1 &&
                        palaceGame.getP2Hand().contains(chosenCard)) {
                    if (palaceGame.getP2Hand().contains(chosenCard)) {
                        if (!palaceGame.getSelectedPalaceCards().contains(chosenCard)) {
                            // put arraylist into GameState variable
                            palaceGame.setSelectedPalaceCards(palaceGame.
                                    addToSelectedCards(chosenCard));
                        } else {
                            palaceGame.removeFromSelectedCards(chosenCard);
                        }
                        return true;
                    } else if (palaceGame.getP2TopPalaceCards().contains(chosenCard)) {
                        if (!palaceGame.getSelectedPalaceCards().contains(chosenCard)) {
                            // put arraylist into GameState variable
                            palaceGame.setSelectedPalaceCards(palaceGame.
                                    addToSelectedCards(chosenCard));
                        } else {
                            palaceGame.removeFromSelectedCards(chosenCard);
                        }
                        return true;
                    } else {
                        return false;
                    }
                }
                // looks at player 3's hand
                else if (palaceGame.getTurn() == 2 &&
                        palaceGame.getP3Hand().contains(chosenCard)) {
                    if (!palaceGame.getSelectedPalaceCards().contains(chosenCard)) {
                        // put arraylist into GameState variable
                        palaceGame.setSelectedPalaceCards(palaceGame.
                                addToSelectedCards(chosenCard));
                    } else {
                        palaceGame.removeFromSelectedCards(chosenCard);
                    }
                    return true;
                }
                // looks at player 4's hand
                else if (palaceGame.getTurn() == 3 &&
                        palaceGame.getP4Hand().contains(chosenCard)) {
                    if (!palaceGame.getSelectedPalaceCards().contains(chosenCard)) {
                        // put arraylist into GameState variable
                        palaceGame.setSelectedPalaceCards(palaceGame.
                                addToSelectedCards(chosenCard));
                    } else {
                        palaceGame.removeFromSelectedCards(chosenCard);
                    }
                    return true;
                } else {
                    return false;
                }
            }

            // A user decides to switch what they want their top hand to be before playing
        } else if (action instanceof PalaceSwitchBaseCardsAction) {
            int index;
            ArrayList<PalaceCard> handPalaceCards;
            ArrayList<PalaceCard> topPalaceCards;
            if (palaceGame.getTurn() == 1) {
                handPalaceCards = new ArrayList<PalaceCard>(palaceGame.getP1Hand());
                topPalaceCards = new ArrayList<PalaceCard>(palaceGame.getP1TopPalaceCards());
                index = topPalaceCards.indexOf(palaceGame.getTopCardSelected());
                topPalaceCards.set(index, palaceGame.getCardToBeSelected());
                index = handPalaceCards.indexOf(palaceGame.getCardToBeSelected());
                handPalaceCards.set(index, palaceGame.getTopCardSelected());
                palaceGame.setP1Hand(handPalaceCards);
                palaceGame.setP1TopPalaceCards(topPalaceCards);
                return true;
            } else if (palaceGame.getTurn() == 2) {
                handPalaceCards = palaceGame.getP2Hand();
                topPalaceCards = palaceGame.getP2TopPalaceCards();
                index = topPalaceCards.indexOf(palaceGame.getTopCardSelected());
                topPalaceCards.set(index, palaceGame.getCardToBeSelected());
                index = handPalaceCards.indexOf(palaceGame.getCardToBeSelected());
                handPalaceCards.set(index, palaceGame.getTopCardSelected());
                palaceGame.setP1Hand(handPalaceCards);
                palaceGame.setP1TopPalaceCards(topPalaceCards);
                return true;
            } else if (palaceGame.getTurn() == 3) {
                handPalaceCards = palaceGame.getP3Hand();
                topPalaceCards = palaceGame.getP3TopPalaceCards();
                index = topPalaceCards.indexOf(palaceGame.getTopCardSelected());
                topPalaceCards.set(index, palaceGame.getCardToBeSelected());
                index = handPalaceCards.indexOf(palaceGame.getCardToBeSelected());
                handPalaceCards.set(index, palaceGame.getTopCardSelected());
                palaceGame.setP1Hand(handPalaceCards);
                palaceGame.setP1TopPalaceCards(topPalaceCards);
                return true;
            } else if (palaceGame.getTurn() == 4) {
                handPalaceCards = palaceGame.getP4Hand();
                topPalaceCards = palaceGame.getP4TopPalaceCards();
                index = topPalaceCards.indexOf(palaceGame.getTopCardSelected());
                topPalaceCards.set(index, palaceGame.getCardToBeSelected());
                index = handPalaceCards.indexOf(palaceGame.getCardToBeSelected());
                handPalaceCards.set(index, palaceGame.getTopCardSelected());
                palaceGame.setP1Hand(handPalaceCards);
                palaceGame.setP1TopPalaceCards(topPalaceCards);
                return true;
            } else {
                return false;
            }

            // A user decides to take the entire play pile!
        } else if (action instanceof PalaceTakePileAction) {
            if (palaceGame.getPlayPileNumCards() > 0) {
                if (palaceGame.getTurn() == 0) {
                    for (int i = 0; i < palaceGame.getPlayPileNumCards(); i++) {
                        //adds pile to player's hand
                        palaceGame.setP1Hand(palaceGame.addToP1Hand(palaceGame.
                                getPlayPilePalaceCards().get(i)));
                        palaceGame.setP1numCards(palaceGame.getP1numCards() + 1);
                    }
                    palaceGame.clearPlayPileCards();//play pile is gone now
                    return true;
                } else if (palaceGame.getTurn() == 1) {
                    for (int i = 0; i < palaceGame.getPlayPileNumCards(); i++) {
                        //adds pile to player's hand
                        palaceGame.addToP2Hand(palaceGame.
                                getPlayPilePalaceCards().get(i));
                        palaceGame.setP2numCards(palaceGame.getP2numCards() + 1);
                    }
                    palaceGame.clearPlayPileCards();//play pile is gone now
                    return true;
                } else if (palaceGame.getTurn() == 2) {
                    for (int i = 0; i < palaceGame.getPlayPileNumCards(); i++) {
                        palaceGame.addToP3Hand(palaceGame.getPlayPilePalaceCards().get(i));
                    }
                    while (palaceGame.getPlayPilePalaceCards().get(0) != null) {
                        palaceGame.removeFromPlayPile(0);
                    }
                    return true;
                } else if (palaceGame.getTurn() == 3) {
                    for (int i = 0; i < palaceGame.getPlayPileNumCards(); i++) {
                        palaceGame.addToP4Hand(palaceGame.getPlayPilePalaceCards().get(i));
                    }
                    while (palaceGame.getPlayPilePalaceCards().get(0) != null) {
                        palaceGame.removeFromPlayPile(0);
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

            // A user needs to scroll through their cards they collected
        } else if (action instanceof PalaceDisplayNextCards) {
            if (palaceGame.getNumDisplayHand() + 1 <= palaceGame.getP1numCards() / 3 &&
                    (palaceGame.getP1numCards() % 3 != 0 || palaceGame.getP1numCards() / 3 > 1)) {
                palaceGame.setNumDisplayHand(palaceGame.getNumDisplayHand() + 1);
            }
            return true;

            // A user needs to scroll back through their cards they collected
        } else if (action instanceof PalaceDisplayPreviousCards) {
            if (palaceGame.getNumDisplayHand() - 1 >= 0) {
                palaceGame.setNumDisplayHand(palaceGame.getNumDisplayHand() - 1);
            }
            return true;
        } else {
            return false;
        }
    }
}

