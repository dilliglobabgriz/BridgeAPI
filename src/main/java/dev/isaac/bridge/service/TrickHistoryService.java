package dev.isaac.bridge.service;


import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dev.isaac.bridge.entity.enums.BidType;
import dev.isaac.bridge.entity.enums.Suit;
import dev.isaac.bridge.entity.model.Card;
import dev.isaac.bridge.entity.model.Game;
import dev.isaac.bridge.entity.model.Player;
import dev.isaac.bridge.entity.model.Trick;
import dev.isaac.bridge.entity.model.TrickHistory;
import dev.isaac.bridge.entity.util.TrickComparator;
import dev.isaac.bridge.exception.BiddingNotCompleteException;

@Service
public class TrickHistoryService {

    /**
     * Adds a card from the current player's hand to the trick
     * If it is the fourth card in the trick, return the winning player's direction
     * 
     * @param trickHistory
     * @param card
     * @return the next player to act, null if hand is over
     */
    public Player.Direction addCard(TrickHistory trickHistory, Card card) {
        // Make sure bidding is done before adding cards
        if (trickHistory.getTrump() == null) {
            throw new BiddingNotCompleteException("Cannot start playing cards until bidding is done and a trump suit is determined.");
        }

        ArrayList<Trick> tricks = trickHistory.getTricks();

        Trick lastTrick = tricks.get(tricks.size() - 1);
    
        Player.Direction leader = trickHistory.getLastTrickWinner();

        // 4th card in trick
        if (lastTrick.size() == 3) {
            lastTrick.addCard(card);
            Card winningCard = getWinningCard(lastTrick, trickHistory.getTrump());
        }

        // Check if this is the last card of the hand
        if (tricks.size() == 13 && lastTrick.size() == 4) {
            return null;
        }

        return nextDirection;

    }
    
    /**
     * Determines the winning card for the current trick
     * Relies on 0th card in trick being the card led
     * 
     * @param trick object with 4 unique cards, one from each player in the hand
     * @param trump a bid type that must be a NT or suit
     * @return the card that won the trick, no player info attached
     */
    public Card getWinningCard(Trick trick, BidType trump) {
        // Param cleanup
        if (trick.size() != 4) {
            throw new IllegalStateException("Trick must have exactly 4 cards.");
        }
        
        Suit leadSuit = trick.peekTopCard().getSuit();
    
        Trick clonedTrick = new Trick();

        for (Card card : trick.getCards()) {
            clonedTrick.addCard(new Card(card));
        }

        clonedTrick.sort(new TrickComparator(leadSuit, trump));

        //System.out.println("Cloned Trick: " + clonedTrick.toString());
        //System.out.println("Trick: " + trick.toString());

        return clonedTrick.peekTopCard();

    }

    public Player.Direction getWinningDirection(Trick trick, Card winningCard, Player.Direction leadDirection) {
        Game util = new Game();

        int distanceFromLeader = 0;

        for (Card card : trick.getCards()) {
            if (card.equals(winningCard)) {
                for (int i=0; i<distanceFromLeader; i++) {
                    leadDirection = util.getNextPlayerDirection(leadDirection);
                }
            }

            distanceFromLeader++;
        }

        return leadDirection;
    }

}
