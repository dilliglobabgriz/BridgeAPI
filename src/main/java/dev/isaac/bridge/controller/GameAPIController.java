package dev.isaac.bridge.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.isaac.bridge.entity.enums.Direction;
import dev.isaac.bridge.entity.model.Bid;
import dev.isaac.bridge.entity.model.BidHistory;
import dev.isaac.bridge.entity.model.Card;
import dev.isaac.bridge.entity.model.Game;
import dev.isaac.bridge.entity.model.Round;
import dev.isaac.bridge.entity.model.TrickHistory;
import dev.isaac.bridge.exception.InvalidBidException;
import dev.isaac.bridge.service.BidHistoryService;
import dev.isaac.bridge.service.GameService;
import dev.isaac.bridge.service.TrickHistoryService;

@RestController
@RequestMapping(path = "/api/games")
public class GameAPIController {

    private GameService gameService;

    private BidHistoryService bidHistoryService;

    private TrickHistoryService trickHistoryService;

    public GameAPIController(GameService gameService, BidHistoryService bidHisotryService, TrickHistoryService trickHistoryService) {
        this.gameService = gameService;
        this.bidHistoryService = bidHisotryService;
        this.trickHistoryService = trickHistoryService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Game>> getGames() {
        ArrayList<Game> games = (ArrayList<Game>) gameService.getGames();
        return ResponseEntity.ok().body(games);
    }

    @PostMapping 
    public ResponseEntity<Long> createGame() {
        Game game = gameService.createGame();
        return ResponseEntity.ok().body(game.getId());
    }

    /**
     * Returns the created Id for the inputted bid
     * 
     * @param gameId
     * @param bid 
     * @return
     */
    @PostMapping(path = "{gameId}/bid")
    public ResponseEntity<Void> makeBid(@PathVariable long gameId, Bid bid) {
        Game game = gameService.getGameById(gameId);
        Round round = game.getRounds().get(game.getRounds().size() - 1);
        BidHistory bidHistory = round.getBidHistory();

        if (!bidHistoryService.isValidBid(bidHistory, bid)) {
            throw new InvalidBidException(bid.toString() + " is not a valid bid");
        }
        
        bidHistoryService.addBid(round, bid);
        

        return ResponseEntity.ok().build();
        
    }

    /**
     * Play a card for the current trick
     * 
     * @param gameId
     * @param card
     * @return the next play direction to go
     */
    @PostMapping(path = "{gameId}/pick")
    public ResponseEntity<Direction> pickCard(@PathVariable long gameId, Card card) {
        Game game = gameService.getGameById(gameId);
        TrickHistory trickHistory = game.getRounds().get(game.getRounds().size() - 1).getTrickHistory();

        Direction nextDirection = trickHistoryService.addCard(trickHistory, card);

        return ResponseEntity.ok().body(nextDirection);
    }
    
}
