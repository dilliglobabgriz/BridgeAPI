package dev.isaac.bridge.entity.model;

import javax.persistence.*;

import dev.isaac.bridge.entity.enums.Direction;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Direction direction;

    private String name;

    private Hand hand;

    public Player() {}

    public Player(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
        // Setting property for both hand and player class
        if (hand != null) {
            hand.setPlayer(this);
        }
    }
}
