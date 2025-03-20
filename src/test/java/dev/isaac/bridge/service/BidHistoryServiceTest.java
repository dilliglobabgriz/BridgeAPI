package dev.isaac.bridge.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.isaac.bridge.BridgeApplication;
import dev.isaac.bridge.entity.enums.BidLevel;
import dev.isaac.bridge.entity.enums.BidType;
import dev.isaac.bridge.entity.model.Bid;
import dev.isaac.bridge.entity.model.BidHistory;

@SpringBootTest(classes = BridgeApplication.class)
public class BidHistoryServiceTest {
    
    @Autowired 
    private BidHistoryService bidHistoryService;

    private BidHistory bidHistory;

    @BeforeEach 
    void setUp() {
        bidHistory = new BidHistory();
        
    }

    @Test 
    void testIsBiddingDone() {
        bidHistory.addBid(new Bid(BidLevel.ONE, BidType.SPADES));     // N: 1S
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // E: P
        bidHistory.addBid(new Bid(BidLevel.TWO, BidType.HEARTS));     // S: 2H
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // W: P
        bidHistory.addBid(new Bid(BidLevel.TWO, BidType.NO_TRUMP));   // N: 2NT
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.DOUBLE)); // E: X
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // S: P
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // W: P
        bidHistory.addBid(new Bid(BidLevel.THREE, BidType.SPADES));   // N: 3S
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // E: P
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // S: P
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // W: P

        boolean expected = true;   // 3 Passes so bidding is completed
        boolean actual = bidHistoryService.isBiddingDone(bidHistory);

        Assertions.assertEquals(expected, actual, "Bidding should be done after 3 passes.");
    }

    @Test 
    void testIsBiddingDoneFalse() {
        bidHistory.addBid(new Bid(BidLevel.ONE, BidType.SPADES));     // N: 1S
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // E: P
        bidHistory.addBid(new Bid(BidLevel.TWO, BidType.HEARTS));     // S: 2H
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // W: P
        bidHistory.addBid(new Bid(BidLevel.TWO, BidType.NO_TRUMP));   // N: 2NT
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.DOUBLE)); // E: X
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // S: P
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // W: P
        bidHistory.addBid(new Bid(BidLevel.THREE, BidType.SPADES));   // N: 3S
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // E: P
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // S: P
        
        boolean expected = false;   // Only two passes
        boolean actual = bidHistoryService.isBiddingDone(bidHistory);

        Assertions.assertEquals(expected, actual, "Bidding is not done only 2 passes.");
    }

    @Test 
    void getLastContractBid() {
        bidHistory.addBid(new Bid(BidLevel.ONE, BidType.SPADES));     // N: 1S
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // E: P
        bidHistory.addBid(new Bid(BidLevel.TWO, BidType.HEARTS));     // S: 2H
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // W: P
        bidHistory.addBid(new Bid(BidLevel.TWO, BidType.NO_TRUMP));   // N: 2NT
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.DOUBLE)); // E: X
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // S: P
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // W: P
        bidHistory.addBid(new Bid(BidLevel.THREE, BidType.SPADES));   // N: 3S
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // E: P
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // S: P
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // W: P

        Bid expected = new Bid(BidLevel.THREE, BidType.SPADES);
        Bid actual = bidHistoryService.getLastContractBid(bidHistory);

        Assertions.assertEquals(expected, actual, "Last bid should be North's 3 spades.");
    }

    @Test 
    void getLastContractBidAllPasses() {
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // N: P
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // E: P
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // S: P
        bidHistory.addBid(new Bid(BidLevel.SPECIAL, BidType.PASS));   // W: P

        Bid expected = new Bid(BidLevel.SPECIAL, BidType.PASS);
        Bid actual = bidHistoryService.getLastContractBid(bidHistory);

        Assertions.assertEquals(expected, actual, "All players pass so should return a pass bid.");
    }
    
}
