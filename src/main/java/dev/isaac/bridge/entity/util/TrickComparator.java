package dev.isaac.bridge.entity.util;

import java.util.Comparator;

import dev.isaac.bridge.entity.enums.Suit;
import dev.isaac.bridge.entity.model.Card;

public class TrickComparator implements Comparator<Card>{

    private final Suit leadSuit;
    private final Suit trumpSuit;

    public TrickComparator(Suit leadSuit, Suit trumpSuit) {
        this.leadSuit = leadSuit;
        this.trumpSuit = trumpSuit;
    }
    
    @Override 
    public int compare(Card c1, Card c2) {
        if (c1.getSuit() == c2.getSuit()) {
            return Integer.compare(c2.getRank().ordinal(), c1.getRank().ordinal());
        }

        if (compareByWinningSuit(c1, c2, trumpSuit) != 0) {
            return compareByWinningSuit(c1, c2, trumpSuit);
        }
        if (compareByWinningSuit(c1, c2, leadSuit) != 0) {
            return compareByWinningSuit(c1, c2, leadSuit);
        }

        return 0;

    }

    public int compareByWinningSuit(Card c1, Card c2, Suit winningSuit) {
        // If one card is trump and other isnt return trump card
        if (winningSuit != null) {
            boolean c1IsWinningSuit = c1.getSuit() == winningSuit;
            boolean c2IsWinningSuit = c2.getSuit() == winningSuit;

            // If only one card is trump it is higher
            if (c1IsWinningSuit && !c2IsWinningSuit) {
                return -1; 
            } else if (!c1IsWinningSuit && c2IsWinningSuit) {
                return 1; 
            }
        }

        return 0;
    }
}
