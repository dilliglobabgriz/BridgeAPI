package dev.isaac.bridge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.isaac.bridge.entity.enums.BidLevel;
import dev.isaac.bridge.entity.enums.BidType;
import dev.isaac.bridge.entity.model.Bid;
import dev.isaac.bridge.entity.model.BidHistory;
import dev.isaac.bridge.entity.model.Deck;
import dev.isaac.bridge.entity.model.Game;
import dev.isaac.bridge.entity.model.Hand;
import dev.isaac.bridge.entity.model.Player;
import dev.isaac.bridge.repository.GameRepository;

@Service
public class GameService {

    private PlayerService playerService;
    private GameRepository gameRepo;

    public GameService(GameRepository gameRepo, PlayerService playerService) {
        this.gameRepo = gameRepo;
        this.playerService = playerService;
    }

    /**
     * Creates a zero player game
     * First generates 4 bots with current ai version and add their id to a new game instance
     * 
     * @return a game with 4 player ids
     */
    public Game createGame() {

        long northId = playerService.createBot().getId();
        long eastId = playerService.createBot().getId();
        long southId = playerService.createBot().getId();
        long westId = playerService.createBot().getId();

        Game newGame = new Game(northId, eastId, southId, westId);

        return newGame;

    }

    
    public List<Game> getGames() {
        return gameRepo.findAll();
    }


    public Game getGameById(long id) {
        return gameRepo.findById(id).orElse(null);
    }
}
