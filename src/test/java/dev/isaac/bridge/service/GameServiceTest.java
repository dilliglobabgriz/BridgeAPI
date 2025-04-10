package dev.isaac.bridge.service;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.isaac.bridge.BridgeApplication;
import dev.isaac.bridge.entity.enums.BidLevel;
import dev.isaac.bridge.entity.enums.BidType;
import dev.isaac.bridge.entity.enums.Direction;
import dev.isaac.bridge.entity.model.Bid;
import dev.isaac.bridge.entity.model.BidHistory;
import dev.isaac.bridge.entity.model.Game;
import dev.isaac.bridge.entity.model.Player;
import dev.isaac.bridge.entity.model.Round;

@SpringBootTest(classes = BridgeApplication.class)
public class GameServiceTest {
    
    @Autowired
    private GameService gameService;

    private Game game;


    @BeforeEach
    void setUp() {
        Game game = gameService.createGame();
        
    }

    // @Test
    // void testPopulatePlayerHands() {
    //     gameService.populatePlayerHands(game);

    //     Player playerOne = game.getPlayers().get(0);

    //     int expectedPlayerOneHandSize = 13;
    //     Direction expectedPlayerOneDirection = Direction.NORTH;

    //     int actualPlayerOneHandSize = playerOne.getHand().size();
    //     Direction actualPlayerOneDirection = playerOne.getDirection();

    //     Assertions.assertEquals(expectedPlayerOneDirection, actualPlayerOneDirection, "P1 direction should be North");
    //     Assertions.assertEquals(expectedPlayerOneHandSize, actualPlayerOneHandSize, "P1 hand size should be 13 cards");
    // }

    @Test 
    void testAddingBidsToHistory() {
        ArrayList<Bid> customBids = new ArrayList<>();
        customBids.add(new Bid(BidLevel.ONE, BidType.SPADES));     // N: 1S
        customBids.add(new Bid(BidLevel.SPECIAL, BidType.PASS));   // E: P
        customBids.add(new Bid(BidLevel.TWO, BidType.HEARTS));     // S: 2H
        customBids.add(new Bid(BidLevel.SPECIAL, BidType.PASS));   // W: P
        customBids.add(new Bid(BidLevel.TWO, BidType.NO_TRUMP));   // N: 2NT
        customBids.add(new Bid(BidLevel.SPECIAL, BidType.DOUBLE)); // E: X
        customBids.add(new Bid(BidLevel.SPECIAL, BidType.PASS));   // S: P
        customBids.add(new Bid(BidLevel.SPECIAL, BidType.PASS));   // W: P
        customBids.add(new Bid(BidLevel.THREE, BidType.SPADES));   // N: 3S
        customBids.add(new Bid(BidLevel.SPECIAL, BidType.PASS));   // E: P
        customBids.add(new Bid(BidLevel.SPECIAL, BidType.PASS));   // S: P
        customBids.add(new Bid(BidLevel.SPECIAL, BidType.PASS));   // W: P

        game.addRound(new Round());

        BidHistory bidHistory = game.getCurrentRound().getBidHistory();

        for (Bid bid : customBids) {
            bidHistory.addBid(bid);
        }

        int expected = 12;
        int actual = bidHistory.getBids().size();

        Assertions.assertEquals(expected, actual, "Bid history should have 12 bids.");
    }

}
