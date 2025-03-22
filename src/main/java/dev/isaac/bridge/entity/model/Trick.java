package dev.isaac.bridge.entity.model;

import java.util.Comparator;

import dev.isaac.bridge.entity.util.CardHolder;

public class Trick extends CardHolder {
    
    @Override
    public void addCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null.");
        } 
        if (this.size() > 4) {
            throw new IllegalStateException("Tricks cannot have more than 4 cards.");
        }
        cards.add(card);
    }

    /**
     * After sorting the 0th card is the winner of the hand
     * @param comparator needs to have a trump suit (null if NT) and a lead suit
     */
    public void sort(Comparator<Card> comparator) {
        cards.sort(comparator);
    }

    @Override 
    public String toString() {
        String s = "";

        for (Card card : cards) {
            s += card.toString() + ", ";
        }

        return s;
    }
}
