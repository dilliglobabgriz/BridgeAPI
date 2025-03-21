package dev.isaac.bridge.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.isaac.bridge.BridgeApplication;
import dev.isaac.bridge.entity.enums.Rank;
import dev.isaac.bridge.entity.enums.Suit;
import dev.isaac.bridge.entity.model.Card;
import dev.isaac.bridge.entity.model.Trick;

@SpringBootTest(classes = BridgeApplication.class)
public class TrickServiceTest {
    
    @Autowired 
    private TrickService trickService;

    private Trick trick;

    @BeforeEach 
    void setUp() {
        trick = new Trick();
    }

    @Test 
    void testGetWinningCardTrumped() {
        trick.addCard(new Card(Rank.KING, Suit.CLUBS));
        trick.addCard(new Card(Rank.TWO, Suit.CLUBS));
        trick.addCard(new Card(Rank.ACE, Suit.SPADES));
        trick.addCard(new Card(Rank.THREE, Suit.HEARTS));

        Card expected = new Card(Rank.THREE, Suit.HEARTS);
        Card actual = trickService.getWinningCard(trick, Suit.HEARTS);

        Assertions.assertEquals(expected, actual, "Three of hearts should win this trick");
    }

    @Test 
    void testGetWinningCardNoTrump() {
        trick.addCard(new Card(Rank.KING, Suit.CLUBS));
        trick.addCard(new Card(Rank.ACE, Suit.DIAMONDS));
        trick.addCard(new Card(Rank.ACE, Suit.SPADES));
        trick.addCard(new Card(Rank.ACE, Suit.CLUBS));

        Card expected = new Card(Rank.ACE, Suit.CLUBS);
        Card actual = trickService.getWinningCard(trick, null);

        Assertions.assertEquals(expected, actual, "Ace of clubs should win this trick");
    }

    @Test 
    void testGetWinningCardAllTrump() {
        trick.addCard(new Card(Rank.FOUR, Suit.CLUBS));
        trick.addCard(new Card(Rank.FIVE, Suit.CLUBS));
        trick.addCard(new Card(Rank.SEVEN, Suit.CLUBS));
        trick.addCard(new Card(Rank.SIX, Suit.CLUBS));

        Card expected = new Card(Rank.SEVEN, Suit.CLUBS);
        Card actual = trickService.getWinningCard(trick, Suit.CLUBS);

        Assertions.assertEquals(expected, actual, "Seven of clubs should win this trick");
    }
}
