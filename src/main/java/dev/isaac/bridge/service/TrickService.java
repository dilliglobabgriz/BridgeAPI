package dev.isaac.bridge.service;

import org.springframework.stereotype.Service;

import dev.isaac.bridge.entity.enums.Suit;
import dev.isaac.bridge.entity.model.Card;
import dev.isaac.bridge.entity.model.Trick;
import dev.isaac.bridge.entity.util.TrickComparator;

@Service
public class TrickService {
    
    /**
     * Determines the winning card for the current trick
     * Relies on 0th card in being the card led
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

        trick.sort(new TrickComparator(leadSuit, trump));

        System.out.println(trick.toString());

        return trick.peekTopCard();

    }
}
