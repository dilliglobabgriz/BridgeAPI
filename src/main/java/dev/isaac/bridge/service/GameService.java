package dev.isaac.bridge.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import dev.isaac.bridge.entity.Game;
import dev.isaac.bridge.entity.Hand;
import dev.isaac.bridge.entity.Player;

@Service
public class GameService {

    private final DeckService deckService;

    public GameService(DeckService deckService) { 
        this.deckService = deckService; 
    }

    /**
     * Takes a game in the waiting stage and populates its players attribute with 4 new players
     * Also adds a direction for each player assigned in order NESW
     * @param game
     */
    public void createPlayers(Game game) {

        if (game.getState() != Game.GameState.WAITING_FOR_PLAYERS) {
            throw new IllegalStateException("Game must be in WAITING_FOR_PLAYERS state");
        }

        if (game.getPlayers().isEmpty()) {
            for (Player.Direction direction : Player.Direction.values()) {
                Player curPlayer = new Player();
                curPlayer.setDirection(direction);
                game.addPlayer(curPlayer);
            }
        } 

        if (game.getPlayers().size() != 4) {
            throw new IllegalStateException("Game cannot run with any number of players except 4.");
        }
    } 

    /**
     * Takes a game in the waiting state and ensures that there are four players
     * Once there are four players, deal each player 13 cards from the game deck shuffled
     * @param game
     */
    public void populatePlayerHands(Game game) {

        if (game.getState() != Game.GameState.WAITING_FOR_PLAYERS) {
            throw new IllegalStateException("Game must be in WAITING_FOR_PLAYERS state");
        }

        if (game.getPlayers().size() != 4) { 
            createPlayers(game);
        }

        
        deckService.populate(game.getDeck());
        

        ArrayList<Hand> hands = deckService.dealHands(game.getDeck());
        ArrayList<Player> players = game.getPlayers();

        for (int i=0; i<game.getPlayers().size(); i++) { 
            players.get(i).setHand(hands.get(i));
        }
    }

    
}
