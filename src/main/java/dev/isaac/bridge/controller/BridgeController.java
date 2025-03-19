package dev.isaac.bridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import dev.isaac.bridge.entity.enums.Suit;
import dev.isaac.bridge.entity.model.Game;
import dev.isaac.bridge.entity.model.Hand;
import dev.isaac.bridge.entity.model.Player;
import dev.isaac.bridge.entity.util.CardComparator;
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
            Hand hand = player.getHand();
            hand.sort(new CardComparator(Suit.DIAMONDS));
            hands.add(hand.toString());
        }

        return ResponseEntity.ok().body(hands);
    }
    
    

}