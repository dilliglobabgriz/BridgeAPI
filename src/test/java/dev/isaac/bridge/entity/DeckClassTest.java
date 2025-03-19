package dev.isaac.bridge.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import dev.isaac.bridge.entity.enums.Rank;
import dev.isaac.bridge.entity.enums.Suit;
import dev.isaac.bridge.entity.model.Card;
import dev.isaac.bridge.entity.model.Deck;

@SpringBootTest
public class DeckClassTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    public void addCard() {
        Card card = new Card(Rank.ACE, Suit.SPADES);

        deck.addCard(card);
        String expected = "ACE of SPADES";
        String actual = deck.peekTopCard().toString();

        Assertions.assertEquals(expected, actual);
    }
}