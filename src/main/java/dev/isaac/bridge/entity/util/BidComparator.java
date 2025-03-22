package dev.isaac.bridge.entity.util;

import java.util.Comparator;

import dev.isaac.bridge.entity.model.Bid;

public class BidComparator implements Comparator<Bid>{

    public BidComparator() {
    }

    /**
     * NOT IMPLEMENTED YET!!!
     * Compares two bid objects to find the highest bid
     * Special bid (pass, double, redouble) rank above number bids
     * 
     * @param b1
     * @param b2
     * @return
     */
    @Override 
    public int compare(Bid b1, Bid b2) {
        if (b1.isSpecialBid() || b2.isSpecialBid()) {}

        return 1;

    }
    
}
