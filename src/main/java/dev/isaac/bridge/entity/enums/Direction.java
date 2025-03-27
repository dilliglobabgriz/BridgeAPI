package dev.isaac.bridge.entity.enums;

public enum Direction {
    NORTH('N'), 
    EAST('E'), 
    SOUTH('S'), 
    WEST('W');

    private final char abbreviation;

    // Constructor
    Direction(char abbreviation) {
        this.abbreviation = abbreviation;
    }

    // Getter for abbreviation
    public char getAbbreviation() {
        return abbreviation;
    }

    // Get the next direction clockwise
    public Direction getNext() {
        return values()[(this.ordinal() + 1) % values().length];
    }
}
