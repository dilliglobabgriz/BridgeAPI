package dev.isaac.bridge.entity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Deck extends CardHolder {
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

    public String toString() {
        return "Deck{" +
                "id=" + id +
                ", cards=" + cards.size() +
                '}';
    }
}