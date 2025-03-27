package dev.isaac.bridge.entity.model;

import java.util.ArrayList;

import dev.isaac.bridge.entity.enums.BidType;
import dev.isaac.bridge.entity.enums.Direction;
import dev.isaac.bridge.exception.InvalidTrumpSuitException;

public class TrickHistory {

    private ArrayList<Trick> tricks;

    private int northSouthTricks;
    private int eastWestTricks;

    private Direction lastTrickWinner;

    private BidType trump;

    public TrickHistory(Direction dealer) {
        tricks = new ArrayList<>();

        northSouthTricks = 0;
        eastWestTricks = 0;

        lastTrickWinner = dealer;

    }

    public ArrayList<Trick> getTricks() {
        return tricks;
    }

    public BidType getTrump() {
        return trump;
    }

    public void setTrump(BidType trump) {
        if (trump == BidType.CLUBS ||
            trump == BidType.DIAMONDS ||
            trump == BidType.HEARTS ||
            trump == BidType.SPADES ||
            trump == BidType.NO_TRUMP) {
                this.trump = trump;
            } else {
                throw new InvalidTrumpSuitException(trump.toString() + " is not a valid trump suit.");
            }
    }

    public void addTrick(Trick trick, Direction winningDirection) {
        if (winningDirection == Direction.NORTH || winningDirection == Direction.SOUTH) {
            northSouthTricks++;
        } else {
            eastWestTricks++;
        }
    }

    public void setLastTrickWinner(Direction lastTrickWinner) {
        this.lastTrickWinner = lastTrickWinner;
    }

    public Direction getLastTrickWinner() {
        return lastTrickWinner;
    }

    public int getNorthSouthTricks() {
        return northSouthTricks;
    }

    public int getEastWestTricks() {
        return eastWestTricks;
    }
    
}
