package dev.isaac.bridge.entity;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "deck_id")
    private ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
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

    public Card removeTopCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }
        return cards.remove(0);
    }

    public Card peekTopCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }
        return cards.get(0);
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public String toString() {
        return "Deck{" +
                "id=" + id +
                ", cards=" + cards.size() +
                '}';
    }
}