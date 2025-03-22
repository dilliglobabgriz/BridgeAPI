package dev.isaac.bridge.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dev.isaac.bridge.entity.enums.BidLevel;
import dev.isaac.bridge.entity.enums.BidType;
import dev.isaac.bridge.entity.model.Bid;
import dev.isaac.bridge.entity.model.Deck;
import dev.isaac.bridge.entity.model.Game;
import dev.isaac.bridge.entity.model.Hand;
import dev.isaac.bridge.entity.model.Player;

@Service
public class GameService {

    private DeckService deckService;
    private BidHistoryService bidHistoryService;

    private Game game; // Persisted game instance

    public GameService(DeckService deckService, BidHistoryService bidHistoryService) {
        this.deckService = deckService;
        this.bidHistoryService = bidHistoryService;
        this.game = new Game(); // Initialize only once
    }

    /**
     * Create 4 players and assign directions in order NESW.
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
     * Populate hands for all 4 players by shuffling and dealing 13 cards each.
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

        for (int i = 0; i < players.size(); i++) {
            players.get(i).setHand(hands.get(i));
        }

        // Set state to bidding after hands are dealt
        game.setState(Game.GameState.BIDDING);
    }

    /**
     * Initialize game with a new deck and set to waiting state.
     */
    public void initializeGame() {
        this.game = new Game();
        Deck deck = new Deck();
        game.setDeck(deck);
        game.setState(Game.GameState.WAITING_FOR_PLAYERS);
    }

    /**
     * Simulate a custom bidding sequence.
     */
    public void playGame() {
        if (game.getState() != Game.GameState.WAITING_FOR_PLAYERS) {
            initializeGame();
        }

        // Initialize and populate hands if not already done
        initializeGame();
        populatePlayerHands(game);

        Player.Direction currentPlayer = game.getDealer();

        // Will be replaced by input from users or bots
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

        while (!bidHistoryService.isBiddingDone(game.getBidHistory()) && !customBids.isEmpty()) {
            Bid playerlessBid = customBids.remove(0);
            Bid curBid = new Bid(playerlessBid.getLevel(), playerlessBid.getType(), currentPlayer);
            game.addBid(curBid); // Add bid to game

            currentPlayer = game.getNextPlayerDirection(currentPlayer);
        }

        // Set state to PLAY once bidding is done
        game.setState(Game.GameState.PLAYING);

    }

    /**
     * Get the current game instance.
     */
    public Game getGame() {
        return game;
    }
}
