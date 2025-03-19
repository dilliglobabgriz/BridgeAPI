package dev.isaac.bridge.service;

import org.springframework.stereotype.Service;

import dev.isaac.bridge.entity.enums.BidLevel;
import dev.isaac.bridge.entity.enums.BidType;
import dev.isaac.bridge.entity.model.Bid;
import dev.isaac.bridge.entity.model.BidHistory;

@Service
public class BidHistoryService {

    public BidHistoryService() {
    }

    /**
     * Used to check if the bidding is done
     * Only returns true if all players have had the chance to bid once and
     * three players in a row pass 
     *
     * @return true when bidding is complete
     */
    public boolean isBiddingDone(BidHistory bidHistory) {
        if (bidHistory.getBids().size() < 4) {
            return false;
        }

        for (int i=bidHistory.getBids().size()-3; i<bidHistory.getBids().size(); i++) {
            if (bidHistory.getBids().get(i).getType() != BidType.PASS) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the last suit or no trump bid, the contract for the current hand
     * If all players pass the hand should be redealt
     * 
     * @return last contract bid, returns a pass bid if all four players pass
     */
    public Bid getLastContractBid(BidHistory bidHistory) {
        for (int i=bidHistory.getBids().size()-1; i>=0; i--) {
            if (!bidHistory.getBids().get(i).isSpecialBid()) {
                return bidHistory.getBids().get(i);
            }
        }

        // Default
        return new Bid(BidLevel.SPECIAL, BidType.PASS);
    }
    
}
