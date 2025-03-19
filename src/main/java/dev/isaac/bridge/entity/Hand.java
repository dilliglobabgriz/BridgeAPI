package dev.isaac.bridge.entity;

import java.util.Collections;
import java.util.Comparator;

import javax.persistence.*;


@Entity
public class Hand extends CardHolder{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Bidirectional one to one with player
    @OneToOne(mappedBy = "hand")
    private Player player;

    public Hand() {
    }

    public Long getId() {
        return id;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Sorts the collection of cards in the following order
     * 1. Suits (Clubs, Diamonds, Hearts, Spades)
     * 2. Rank - High to Low (A, K, ... 3, 2)
     */
    public void sort(Comparator<Card> comparator) {
        cards.sort(comparator);
    }

    public String toString() {
        String cardString = "Hand{Player=" + player.getDirection() +
                            ", Cards={";

        for (Card card : cards) {
            cardString += card.toStringABV() + ", ";
        }

        return cardString + "}}";
    }
}
