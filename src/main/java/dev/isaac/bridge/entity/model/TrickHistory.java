package dev.isaac.bridge.entity.model;

import java.util.ArrayList;

public class TrickHistory {

    private ArrayList<Trick> tricks;

    private int northSoutTricks;
    private int eastWestTricks;

    private Player.Direction lastTrickWinner;

    public TrickHistory(Player.Direction dealer) {
        tricks = new ArrayList<>();

        northSoutTricks = 0;
        eastWestTricks = 0;

        lastTrickWinner = dealer;
    }

    public ArrayList<Trick> getTricks() {
        return tricks;
    }

    public void addTrick(Trick trick, Player.Direction winningDirection) {
        if (winningDirection == Player.Direction.NORTH || winningDirection == Player.Direction.SOUTH) {
            northSoutTricks++;
        } else {
            eastWestTricks++;
        }
    }

    public int getNorthSoutTricks() {
        return northSoutTricks;
    }

    public int getEastWestTricks() {
        return eastWestTricks;
    }
    
}
