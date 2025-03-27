package dev.isaac.bridge.service;

import org.springframework.stereotype.Service;

import dev.isaac.bridge.entity.enums.Rank;
import dev.isaac.bridge.entity.enums.Suit;
import dev.isaac.bridge.entity.model.Card;
import dev.isaac.bridge.entity.model.Deck;
import dev.isaac.bridge.entity.model.Hand;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class DeckService {

    /**
     * Given an empty deck fill it with 52 unique cards and shuffles it
     *
     * @param deck must be empty
     * @return a populated deck
     */
    public void populate(Deck deck) {
        if (deck == null) {
            throw new IllegalArgumentException("Deck must not be null.");
        } 
        
        deck.clear();

        for (Suit suit : Suit.getSuits()) {
            for (Rank rank : Rank.values()) {
                Card curCard = new Card(rank, suit);
                deck.addCard(curCard);
            }
        }

        Collections.shuffle(deck.getCards());
    }

    /**
     * Populates deck if empty or uses current deck to deal 4 hands
     * 
     * @param deck can be empty or 52 cards
     * @return 4 hands of 13 cards each
     */
    public ArrayList<Hand> dealHands(Deck deck) {
        if (deck == null) {
            throw new IllegalArgumentException("Deck must not be null.");
        }

        if (deck.isEmpty()) {
            populate(deck);
        }

        if (deck.size() != 52) {
            throw new IllegalStateException("Can only deal from a full 52-card deck.");
        }

        ArrayList<Hand> hands = new ArrayList<>();

        for (int i=0; i<4; i++) {
            Hand hand = new Hand();

            for (int j=0; j<13; j++) {
                hand.addCard(deck.removeTopCard());
            }

            hands.add(hand);
        }

        return hands;
    }

}