package dev.isaac.bridge.service;

import dev.isaac.bridge.entity.Deck;
import dev.isaac.bridge.entity.Hand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeckServiceTest {

    @Autowired
    private DeckService deckService;

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    void testPopulateDeck() {
        deckService.populate(deck);

        Assertions.assertEquals(52, deck.getCards().size(), "Populated deck should have 52 cards");

        long uniqueCards = deck.getCards().stream().distinct().count();
        Assertions.assertEquals(52, uniqueCards, "Deck should contain no duplicate cards");
    }

    @Test
    void testDealHands() {
        ArrayList<Hand> hands = deckService.dealHands(deck);

        int actualHandSize = hands.get(3).size();
        int expectedHandSize = 13;

        Assertions.assertEquals(expectedHandSize, actualHandSize, "Hand 4 must have 13 cards.");
    }
}