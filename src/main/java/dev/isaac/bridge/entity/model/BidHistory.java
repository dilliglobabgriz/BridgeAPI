package dev.isaac.bridge.entity.model;

import java.util.ArrayList;

public class BidHistory {

    private ArrayList<Bid> bids = new ArrayList<>();

    public BidHistory() {
    }

    public ArrayList<Bid> getBids() {
        return bids;
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }

    public ArrayList<ArrayList<Bid>> getBidsByDirection() {
        ArrayList<ArrayList<Bid>> bidsByPlayer = new ArrayList<>();

        for (Player.Direction direction : Player.Direction.values()) {

            ArrayList<Bid> playerBids = new ArrayList<>();

            for (Bid bid : bids) {
                if (bid.getPlayerDirection() == direction) {
                    playerBids.add(bid);
                }
            }

            bidsByPlayer.add(playerBids);
        }

        return bidsByPlayer;
    }

    @Override 
    public String toString() {
        String s = "";

        for (Bid bid : bids) {
            s += bid.toString() + "\n";
        }

        return s;
    }

    
}
