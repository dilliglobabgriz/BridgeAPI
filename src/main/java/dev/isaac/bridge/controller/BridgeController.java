package dev.isaac.bridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import dev.isaac.bridge.entity.Game;
import dev.isaac.bridge.entity.Hand;
import dev.isaac.bridge.entity.Player;
import dev.isaac.bridge.service.DeckService;
import dev.isaac.bridge.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


//import dev.isaac.bridge.entity.Card;
//import dev.isaac.bridge.entity.Deck;

@RestController
public class BridgeController {

    private DeckService deckService;
    private GameService gameService;

    @Autowired
    public BridgeController(DeckService deckService, GameService gameService) {
        this.deckService = deckService;
        this.gameService = gameService;
    }

    @GetMapping("hands")
    public ResponseEntity<ArrayList<String>> getHands() { 
        Game game = new Game();
        gameService.initializeGame(game);
        gameService.populatePlayerHands(game);
        ArrayList<String> hands = new ArrayList<>();

        for (Player player : game.getPlayers()) { 
            hands.add(player.getHand().toString());
        }

        return ResponseEntity.ok().body(hands);
    }
    
    

}