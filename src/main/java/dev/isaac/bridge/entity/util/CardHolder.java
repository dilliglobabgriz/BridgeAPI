package dev.isaac.bridge.entity.util;

import java.util.ArrayList;
import java.util.Collections;

import javax.persistence.MappedSuperclass;

import dev.isaac.bridge.entity.model.Card;

@MappedSuperclass
public abstract class CardHolder {
    protected ArrayList<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null.");
        }
        cards.add(card);
    }

    public void setCards(ArrayList<Card> newCards) {
        cards.clear();
        cards = newCards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void clear() {
        cards.clear();
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public boolean removeCard(Card card) {
        return cards.remove(card);
    }

    public Card removeCardAt(int index) {
        if (index < 0 || index >= cards.size()) {
            throw new IndexOutOfBoundsException("Invalid card index.");
        }
        return cards.remove(index);
    }

    public Card peekTopCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("No cards available.");
        }
        return cards.get(0);
    }

    public Card removeTopCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("No cards available.");
        }
        return cards.remove(0);
    }


    @Override
    public String toString() {
        String s = "";
        for (Card card : cards) {
            s += card.toStringABV();
        }

        return s;
    }
}