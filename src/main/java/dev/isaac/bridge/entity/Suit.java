package dev.isaac.bridge.entity;

import org.springframework.stereotype.Component;

//@Component
public enum Suit {
    CLUBS('C', '\u2663'),     // ♣
    DIAMONDS('D', '\u2666'),  // ♦
    HEARTS('H', '\u2665'),    // ♥
    SPADES('S', '\u2660');    // ♠

    private final char abbreviation;
    private final char symbol;

    Suit(char abbreviation, char symbol) {
        this.abbreviation = abbreviation;
        this.symbol = symbol;
    }

    public char getAbbreviation() {
        return abbreviation;
    }

    public char getSymbol() {
        return symbol;
    }
}
