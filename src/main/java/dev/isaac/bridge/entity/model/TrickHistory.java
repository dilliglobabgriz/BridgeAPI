package dev.isaac.bridge.entity.model;

import java.util.ArrayList;

import dev.isaac.bridge.entity.enums.BidType;
import dev.isaac.bridge.exception.InvalidTrumpSuitException;

public class TrickHistory {

    private ArrayList<Trick> tricks;

    private int northSouthTricks;
    private int eastWestTricks;

    private Player.Direction lastTrickWinner;

    private BidType trump;

    public TrickHistory(Player.Direction dealer) {
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

    public void addTrick(Trick trick, Player.Direction winningDirection) {
        if (winningDirection == Player.Direction.NORTH || winningDirection == Player.Direction.SOUTH) {
            northSouthTricks++;
        } else {
            eastWestTricks++;
        }
    }

    public void setLastTrickWinner(Player.Direction lastTrickWinner) {
        this.lastTrickWinner = lastTrickWinner;
    }

    public Player.Direction getLastTrickWinner() {
        return lastTrickWinner;
    }

    public int getNorthSouthTricks() {
        return northSouthTricks;
    }

    public int getEastWestTricks() {
        return eastWestTricks;
    }
    
}
