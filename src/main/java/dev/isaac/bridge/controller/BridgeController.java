package dev.isaac.bridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import dev.isaac.bridge.entity.enums.Suit;
import dev.isaac.bridge.entity.model.Bid;
import dev.isaac.bridge.entity.model.Game;
import dev.isaac.bridge.entity.model.Hand;
import dev.isaac.bridge.entity.model.Player;
import dev.isaac.bridge.entity.util.CardComparator;
import dev.isaac.bridge.service.DeckService;
import dev.isaac.bridge.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


//import dev.isaac.bridge.entity.Card;
//import dev.isaac.bridge.entity.Deck;

@RestController
public class BridgeController {

    private final GameService gameService;

    public BridgeController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Retrieve the current hands of all players.
     * If hands are not yet populated, they will be populated first.
     */
    @GetMapping("/hands")
    public ResponseEntity<ArrayList<String>> getHands() { 
        gameService.initializeGame();
        Game game = gameService.getGame();
        
        // Ensure hands are populated
        if (game.getState() == Game.GameState.WAITING_FOR_PLAYERS) {
            gameService.populatePlayerHands(game);
        }

        ArrayList<String> hands = new ArrayList<>();
        for (Player player : game.getPlayers()) { 
            Hand hand = player.getHand();
            hand.sort(new CardComparator(Suit.DIAMONDS));
            hands.add(hand.toString());
        }

        return ResponseEntity.ok().body(hands);
    }

    /**
     * Start the bidding process and return the bid history.
     */
    @GetMapping("/bids")
    public ResponseEntity<ArrayList<Bid>> getBids() {
        gameService.playGame();  // Play bidding sequence
        Game game = gameService.getGame();
        
        return ResponseEntity.ok().body(game.getBidHistory().getBids());
    }

    /**
     * Reset the game.
     */
    @PostMapping("/reset")
    public ResponseEntity<String> resetGame() {
        gameService.initializeGame(); // Reset game state
        return ResponseEntity.ok("Game reset successfully!");
    }
}