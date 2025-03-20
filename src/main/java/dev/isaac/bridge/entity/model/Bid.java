package dev.isaac.bridge.entity.model;

import javax.persistence.*;

import dev.isaac.bridge.entity.enums.BidLevel;
import dev.isaac.bridge.entity.enums.BidType;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private BidLevel level;

    @Enumerated(EnumType.STRING)
    private BidType type;

    private Player.Direction playerDirection;

    protected Bid() {
    }

    public Bid(BidLevel level, BidType type) {
        this.level = level;
        this.type = type;
    }

    public Bid(BidLevel level, BidType type, Player.Direction playerDirection) {
        this.level = level;
        this.type = type;
        this.playerDirection = playerDirection;
    }

    public BidLevel getLevel() {
        return level;
    }

    public BidType getType() {
        return type;
    }

    public Player.Direction getPlayerDirection() {
        return playerDirection;
    }

    public boolean isSpecialBid() {
        return type == BidType.PASS || 
               type == BidType.DOUBLE || 
               type == BidType.REDOUBLE;
    }

    @Override
    public String toString() {
        return "Bid{" +
                level.getAbbreviation() + type.getAbbreviation() +
                ", Direction{" + playerDirection + "}" +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
    
        Bid bid = (Bid) obj;
        return this.level == bid.getLevel() && this.type == bid.getType();
    }

}
