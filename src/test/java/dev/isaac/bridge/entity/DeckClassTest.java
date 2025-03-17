package dev.isaac.bridge.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeckClassTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    public void addCard() {
        Card card = new Card(Card.Rank.ACE, Card.Suit.SPADES);

        deck.addCard(card);
        String expected = "ACE of SPADES";
        String actual = deck.peekTopCard().toString();

        Assertions.assertEquals(expected, actual);
    }
}