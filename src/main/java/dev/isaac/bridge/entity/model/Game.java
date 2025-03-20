package dev.isaac.bridge.entity.model;

import javax.persistence.*;

import java.util.ArrayList;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private ArrayList<Player> players = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "game_id")
    private Deck deck;

    @Enumerated(EnumType.STRING) 
    private GameState state;

    @Enumerated(EnumType.STRING) 
    private Player.Direction dealer;

    private BidHistory bidHistory;

    public enum GameState {
        WAITING_FOR_PLAYERS, BIDDING, PLAYING, COMPLETED
    }

    public Game() {
        bidHistory = new BidHistory();
    }

     // Getters and setters
     public Long getId() {
        return id;
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    
    public void addPlayer(Player player) {
        players.add(player);
    }
    
    public Deck getDeck() {
        return deck;
    }
    
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public GameState getState() {
        return state;
    }
    
    public void setState(GameState state) {
        this.state = state;
    }
    
    public Player.Direction getDealer() {
        return dealer;
    }
    
    public void setDealer(Player.Direction dealer) {
        this.dealer = dealer;
    }

    public Player getPlayerByDirection(Player.Direction direction) {
        for (Player player : players) {
            if (player.getDirection() == direction) {
                return player;
            }
        }
        return null;
    }
    
    public Player.Direction getNextPlayerDirection(Player.Direction current) {
        switch (current) {
            case NORTH: return Player.Direction.EAST;
            case EAST: return Player.Direction.SOUTH;
            case SOUTH: return Player.Direction.WEST;
            case WEST: return Player.Direction.NORTH;
            default: return null;
        }
    }

    public void addBid(Bid bid) {
        bidHistory.addBid(bid);
    }

    public BidHistory getBidHistory() {
        return bidHistory;
    }
    
    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", state=" + state +
                ", playerCount=" + players.size() +
                '}';
    }
}
