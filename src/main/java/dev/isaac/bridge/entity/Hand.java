package dev.isaac.bridge.entity;

import java.util.ArrayList;

import javax.persistence.*;

@Entity
public class Hand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hand_id")
    private ArrayList<Card> cards = new ArrayList<>();

    public Hand() {
    }

    public Long getId() {
        return id;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public String toString() {
        String cardString = "Hand{id=" + id;

        for (Card card : cards) {
            cardString += card.toString() + ", ";
        }

        return cardString + "}";
    }
}
