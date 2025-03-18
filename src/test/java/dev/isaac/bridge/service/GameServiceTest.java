package dev.isaac.bridge.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.isaac.bridge.BridgeApplication;
import dev.isaac.bridge.entity.Deck;
import dev.isaac.bridge.entity.Game;
import dev.isaac.bridge.entity.Player;

@SpringBootTest(classes = BridgeApplication.class)
public class GameServiceTest {
    
    @Autowired
    private GameService gameService;

    private Game game;

    //private Deck deck;

    @BeforeEach
    void setUp() {
        game = new Game();

        gameService.initializeGame(game);
        
    }

    @Test
    void testPopulatePlayerHands() {
        gameService.populatePlayerHands(game);

        Player playerOne = game.getPlayers().get(0);

        int expectedPlayerOneHandSize = 13;
        Player.Direction expectedPlayerOneDirection = Player.Direction.NORTH;

        int actualPlayerOneHandSize = playerOne.getHand().size();
        Player.Direction actualPlayerOneDirection = playerOne.getDirection();

        Assertions.assertEquals(expectedPlayerOneDirection, actualPlayerOneDirection, "P1 direction should be North");
        Assertions.assertEquals(expectedPlayerOneHandSize, actualPlayerOneHandSize, "P1 hand size should be 13 cards");
    }

}
