package dev.isaac.bridge.service;


import org.springframework.stereotype.Service;

import dev.isaac.bridge.entity.enums.Suit;
import dev.isaac.bridge.entity.model.Card;
import dev.isaac.bridge.entity.model.Game;
import dev.isaac.bridge.entity.model.Player;
import dev.isaac.bridge.entity.model.Trick;
import dev.isaac.bridge.entity.util.TrickComparator;

@Service
public class TrickHistoryService {
    
    /**
     * Determines the winning card for the current trick
     * Relies on 0th card in trick being the card led
     * 
     * @param trick object with 4 unique cards, one from each player in the hand
     * @param trump a bid type that must not be a NT or suit
     * @return the card that won the trick, no player info attached
     */
    public Card getWinningCard(Trick trick, Suit trump) {
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
