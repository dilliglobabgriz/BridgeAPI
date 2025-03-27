package dev.isaac.bridge.entity.enums;

public enum BidType {
    CLUBS('C', '\u2663'),     // ♣
    DIAMONDS('D', '\u2666'),  // ♦
    HEARTS('H', '\u2665'),    // ♥
    SPADES('S', '\u2660'),    // ♠
    NO_TRUMP('N', '\u2B55'),  // ⭕
    PASS('P', '\u23ED'),      // ⏭️
    DOUBLE('X', '\u274C'),    // ❌
    REDOUBLE('R', '\u267B');  // ♻️

    private final char abbreviation;
    private final char symbol;

    BidType(char abbreviation, char symbol) {
        this.abbreviation = abbreviation;
        this.symbol = symbol;
    }

    public boolean isSuitOrNoTrump() {
        return this == CLUBS || 
               this == DIAMONDS || 
               this == HEARTS || 
               this == SPADES || 
               this == NO_TRUMP;
    }

    public char getAbbreviation() {
        return abbreviation;
    }

    public char getSymbol() {
        return symbol;
    }

    public Suit toSuit() {
        switch (this) {
            case CLUBS: return Suit.CLUBS;
            case DIAMONDS: return Suit.DIAMONDS;
            case HEARTS: return Suit.HEARTS;
            case SPADES: return Suit.SPADES;
            case NO_TRUMP: return Suit.NO_TRUMP; 
            default: return null;  
        }
    }
}

