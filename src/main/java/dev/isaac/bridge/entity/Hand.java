package dev.isaac.bridge.entity;

import java.util.ArrayList;

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

    public String toString() {
        String cardString = "Hand{Player=" + player.getDirection() +
                            ", Cards={";

        for (Card card : cards) {
            cardString += card.toStringABV() + ", ";
        }

        return cardString + "}}";
    }
}
