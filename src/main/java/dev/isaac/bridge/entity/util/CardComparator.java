package dev.isaac.bridge.entity.util;

import java.util.Comparator;

import dev.isaac.bridge.entity.enums.Suit;
import dev.isaac.bridge.entity.model.Card;

public class CardComparator implements Comparator<Card>{

    private final Suit trumpSuit;
    private final Suit leadSuit;

    // Default constructor with trump, sorts by suit C-S and rank A-2
    public CardComparator() {
        this.trumpSuit = null;
        this.leadSuit = null;
    }

    // Constructor with trump, trump cards take priority
    public CardComparator(Suit trumpSuit) {
        this.trumpSuit = trumpSuit;
        this.leadSuit = null;
    }

    public CardComparator(Suit trumpSuit, Suit leadSuit) {
        this.trumpSuit = trumpSuit;
        this.leadSuit = leadSuit;
    }

    @Override 
    public int compare(Card c1, Card c2) {
        // If one card is trump and other isnt return trump card
        if (trumpSuit != null) {
            boolean c1IsTrump = c1.getSuit() == trumpSuit;
            boolean c2IsTrump = c2.getSuit() == trumpSuit;

            // If only one card is trump it is higher
            if (c1IsTrump && !c2IsTrump) {
                return -1; 
            } else if (!c1IsTrump && c2IsTrump) {
                return 1; 
            }
        }
        // Similar logic as trump card but for lead suit card
        if (leadSuit != null) {
            boolean c1IsLeadSuit = c1.getSuit() == leadSuit;
            boolean c2IsLeadSuit = c2.getSuit() == leadSuit;

            if (c1IsLeadSuit && !c2IsLeadSuit) {
                return -1;
            } else if (!c1IsLeadSuit && c2IsLeadSuit) {
                return 1;
            }
            // If neither card is trump or the lead suit they are both worthless
            return 0;
        }

        if (c1.getSuit().ordinal() > c2.getSuit().ordinal()) {
            return 1;
        } else if (c1.getSuit().ordinal() < c2.getSuit().ordinal()) {
            return -1;
        } else {    // Suits are the same so go by rank
            if (c1.getRank().ordinal() < c2.getRank().ordinal()) {
                return 1;
            } else if (c1.getRank().ordinal() > c2.getRank().ordinal()) {
                return -1;
            }
        }

        return 0;  // Should not happen

    }
    
}
