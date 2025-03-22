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
import dev.isaac.bridge.entity.model.Hand;

@SpringBootTest(classes = BridgeApplication.class)
public class HandServiceTest {

    @Autowired 
    private HandService handService;

    private Hand hand;

    @BeforeEach 
    void setUp() {
        hand = new Hand();
    }

    @Test 
    void testGetHCP() {
        hand.addCard(new Card(Rank.ACE, Suit.CLUBS));
        hand.addCard(new Card(Rank.KING, Suit.CLUBS));
        hand.addCard(new Card(Rank.JACK, Suit.CLUBS));
        hand.addCard(new Card(Rank.TEN, Suit.CLUBS));
        hand.addCard(new Card(Rank.NINE, Suit.CLUBS));
        hand.addCard(new Card(Rank.FIVE, Suit.CLUBS));
        hand.addCard(new Card(Rank.FOUR, Suit.CLUBS));
        hand.addCard(new Card(Rank.THREE, Suit.CLUBS));
        hand.addCard(new Card(Rank.TWO, Suit.CLUBS));
        hand.addCard(new Card(Rank.ACE, Suit.HEARTS));
        hand.addCard(new Card(Rank.KING, Suit.SPADES));
        hand.addCard(new Card(Rank.QUEEN, Suit.DIAMONDS));
        hand.addCard(new Card(Rank.JACK, Suit.HEARTS));

        int actualHCP = handService.getHCP(hand);
        int expectedHCP = 18;

        Assertions.assertEquals(expectedHCP, actualHCP, "HCP of the given hand should be 18.");
    }
    
}
