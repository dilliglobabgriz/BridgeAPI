package dev.isaac.bridge.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CardComparatorTest {
    
    @Test
    public void testCompareWithoutTrump() {
        Card c1 = new Card(Rank.ACE, Suit.CLUBS);
        Card c2 = new Card(Rank.KING, Suit.HEARTS);
        
        CardComparator comparator = new CardComparator();
        int result = comparator.compare(c1, c2);

        // Expect Clubs to be ranked lower than Hearts
        assertTrue(result < 0, "ACE of CLUBS should be less than KING of HEARTS without trump.");
    }

    @Test
    public void testCompareWithTrumpSuit() {
        Card c1 = new Card(Rank.TWO, Suit.DIAMONDS);
        Card c2 = new Card(Rank.KING, Suit.SPADES);
        
        CardComparator comparator = new CardComparator(Suit.DIAMONDS);
        int result = comparator.compare(c1, c2);

        // Expect the trump card to be higher
        assertTrue(result < 0, "TWO of DIAMONDS (trump) should be higher than KING of CLUBS.");
    }

    @Test
    public void testCompareSameSuitDifferentRank() {
        Card c1 = new Card(Rank.KING, Suit.HEARTS);
        Card c2 = new Card(Rank.QUEEN, Suit.HEARTS);
        
        CardComparator comparator = new CardComparator();
        int result = comparator.compare(c1, c2);

        // Expect King to be higher than Queen
        assertTrue(result < 0, "KING of HEARTS should be higher than QUEEN of HEARTS.");
    }

    @Test
    public void testCompareSameCard() {
        Card c1 = new Card(Rank.ACE, Suit.SPADES);
        Card c2 = new Card(Rank.ACE, Suit.SPADES);
        
        CardComparator comparator = new CardComparator();
        int result = comparator.compare(c1, c2);

        // Same card should return 0
        assertEquals(0, result, "Same cards should be considered equal.");
    }

    @Test
    public void testCompareTrumpSuitTie() {
        Card c1 = new Card(Rank.JACK, Suit.DIAMONDS);
        Card c2 = new Card(Rank.TEN, Suit.DIAMONDS);
        
        CardComparator comparator = new CardComparator(Suit.DIAMONDS);
        int result = comparator.compare(c1, c2);

        // Expect JACK to be higher than TEN within trump
        assertTrue(result < 0, "JACK of DIAMONDS should be higher than TEN of DIAMONDS.");
    }
}
