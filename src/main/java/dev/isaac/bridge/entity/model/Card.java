package dev.isaac.bridge.entity.model;

import javax.persistence.*;

import dev.isaac.bridge.entity.enums.Rank;
import dev.isaac.bridge.entity.enums.Suit;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Enumerated(EnumType.STRING)
    private Suit suit;

    @Enumerated(EnumType.STRING)
    private Rank rank;

    protected Card() {
    }

    public Card(Rank rank, Suit suit) {
        this.suit = suit;
        this.rank = rank;
    }

    public Long getId() {
        return id;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card other = (Card) obj;
        return rank.equals(other.rank) && suit.equals(other.suit);
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    public String toStringABV() { 
        String s = "";
        return s + rank.getAbbreviation() + suit.getSymbol();
    }
}