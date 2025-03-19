package dev.isaac.bridge.entity.util;

import java.util.Comparator;

import dev.isaac.bridge.entity.enums.Suit;
import dev.isaac.bridge.entity.model.Card;

public class CardComparator implements Comparator<Card>{

    private final Suit trumpSuit;

    // Default constructor with trump, sorts by suit C-S and rank A-2
    public CardComparator() {
        this.trumpSuit = null;
    }

    // Constructor with trump, trump cards take priority
    public CardComparator(Suit trumpSuit) {
        this.trumpSuit = trumpSuit;
    }

    @Override 
    public int compare(Card c1, Card c2) {
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
