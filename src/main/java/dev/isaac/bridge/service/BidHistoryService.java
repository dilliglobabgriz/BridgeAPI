package dev.isaac.bridge.service;

import org.springframework.stereotype.Service;

import dev.isaac.bridge.entity.enums.BidLevel;
import dev.isaac.bridge.entity.enums.BidType;
import dev.isaac.bridge.entity.model.Bid;
import dev.isaac.bridge.entity.model.BidHistory;
import dev.isaac.bridge.entity.model.Game;
import dev.isaac.bridge.exception.InvalidBidException;

@Service
public class BidHistoryService {

    public BidHistoryService() {
    }

    /**
     * Adds a bid to the game as long as bidding is not yet completed
     * If the bid ends the bidding round, set trick histories trump to the correct suit
     * 
     * @param game
     * @param bid
     * @return
     */
    public Bid addBid(Game game, Bid bid) {
        BidHistory bidHistory = game.getBidHistory();

        if (!isValidBid(bidHistory, bid)) {
            throw new InvalidBidException("bid is not legal");
        }

        bidHistory.addBid(bid);

        if (isBiddingDone(bidHistory)) {
            game.getTrickHistory().setTrump(getLastContractBid(bidHistory).getType());
        }

        return bid;
    }

    /**
     * Ignores double/redouble for now
     * FOR TESTING ONLY 
     * 
     * @param bidHistory
     * @param bid
     * @return
     */
    public boolean isValidBid(BidHistory bidHistory, Bid bid) {
        StringBuilder debugInfo = new StringBuilder();
    
        if (isBiddingDone(bidHistory)) {
            debugInfo.append("Bidding is already concluded. Cannot add new bids.\n");
            saveDebugInfo(debugInfo.toString());
            return false;
        }
    
        BidType type = bid.getType();
        BidLevel level = bid.getLevel();
        Bid lastContractBid = getLastContractBid(bidHistory);
    
        if (!(lastContractBid.getType() == BidType.PASS)) {
            // Same bid level but suit does not outrank
            if (lastContractBid.getLevel() == level && lastContractBid.getType().ordinal() >= type.ordinal()) {
                debugInfo.append(type.toString())
                         .append(" does not outrank ")
                         .append(lastContractBid.getType().toString())
                         .append("\n");
                saveDebugInfo(debugInfo.toString());
                return false;
            }
            // Bid level is too low
            if (lastContractBid.getLevel().ordinal() > level.ordinal()) {
                debugInfo.append(level.toString())
                         .append(" is less than ")
                         .append(lastContractBid.getLevel().toString())
                         .append("\n");
                saveDebugInfo(debugInfo.toString());
                return false;
            }
        }
    
        bidHistory.addBid(bid);
        return true;
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

    private void saveDebugInfo(String info) {
        // Example: Log to console, but you can save to a file or DB if needed
        System.out.println("DEBUG INFO:\n" + info);
    }
    
    
}
