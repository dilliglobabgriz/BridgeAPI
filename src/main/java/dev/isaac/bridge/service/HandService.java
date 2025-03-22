package dev.isaac.bridge.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dev.isaac.bridge.entity.model.Card;
import dev.isaac.bridge.entity.model.Hand;

@Service
public class HandService {

    /**
     * Calculates the high card points for a given 13 card hand
     * A=4, K=3, Q=2, J=1
     */
    public int getHCP(Hand hand) {
        if (hand.size() != 13) {
            throw new IllegalStateException("Hand must have exactly 13 cards.");
        }

        ArrayList<Card> cards = hand.getCards();
        int totalHCP = 0;
        
        for (Card card : cards) {
            // ord(2) = 0 ... ,J=9, Q=10, K=11, A=12
            int rankOrdinal = card.getRank().ordinal();

            if (rankOrdinal >= 9) {  // Jacks and up
                totalHCP += rankOrdinal - 8;  // Converts ord to correct HCP
            }
        }
        return totalHCP;
    }
    
}
