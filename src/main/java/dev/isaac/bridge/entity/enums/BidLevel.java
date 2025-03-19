package dev.isaac.bridge.entity.enums;

public enum BidLevel {
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    SPECIAL(' ');

    private final char abbreviation;

    BidLevel(char abbreviation) {
        this.abbreviation = abbreviation;
    }

    public char getAbbreviation() {
        return abbreviation;
    }
}
