package dev.isaac.bridge.service;

import org.springframework.stereotype.Service;

import dev.isaac.bridge.entity.Deck;
import dev.isaac.bridge.entity.Hand;
import dev.isaac.bridge.entity.Card;

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
        if (!deck.isEmpty()) {
            throw new IllegalStateException("Cannot populate a non empty deck.");
        }

        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
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
        ArrayList<Hand> hands = new ArrayList<>();

        if (deck.isEmpty()) {
            populate(deck);
        } 

        if (!(deck.size() == 52)) {
            throw new IllegalStateException("Can only deal with a 52 card deck.");
        }

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