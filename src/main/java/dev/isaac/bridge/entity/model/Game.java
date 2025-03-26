package dev.isaac.bridge.entity.model;

import javax.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name="game")
public class Game {
    
    @Column(name="gameId")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="dealer")
    @Enumerated(EnumType.STRING) 
    private Player.Direction dealer;

    @Column(name="northId")
    private long northId;

    @Column(name="southId")
    private long southId;

    @Column(name="eastId")
    private long eastId;

    @Column(name="westId")
    private long westId;

    private Deck deck;

    @Enumerated(EnumType.STRING) 
    private GameState state;

    private BidHistory bidHistory;

    private TrickHistory trickHistory;

    public enum GameState {
        WAITING_FOR_PLAYERS, BIDDING, PLAYING, COMPLETED
    }

    public Game() {
        dealer = Player.Direction.NORTH;

        bidHistory = new BidHistory();
        trickHistory = new TrickHistory(dealer);
    }

    public Game(Player.Direction dealer) {
        this.dealer = dealer;

        bidHistory = new BidHistory();
        trickHistory = new TrickHistory(dealer);
    }

    public Game(long northId, long eastId, long southId, long westId) {
        this.northId = northId;
        this.eastId = eastId;
        this.southId = southId;
        this.westId = westId;
    }

     // Getters and setters
     public Long getId() {
        return id;
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

    public void addBid(Bid bid) {
        bidHistory.addBid(bid);
    }

    public BidHistory getBidHistory() {
        return bidHistory;
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

    public TrickHistory getTrickHistory() {
        return trickHistory;
    }
    
    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", state=" + state +
                ", dealer=" + dealer +
                '}';
    }
}
