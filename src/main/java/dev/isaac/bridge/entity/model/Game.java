package dev.isaac.bridge.entity.model;

import javax.persistence.*;

import dev.isaac.bridge.entity.enums.Direction;

import java.util.ArrayList;

@Entity
@Table(name="game")
public class Game {
    
    @Column(name="gameId")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="dealer")
    @Enumerated(EnumType.STRING) 
    private Direction dealer;

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

    private ArrayList<Round> rounds;

    public enum GameState {
        WAITING_FOR_PLAYERS, BIDDING, PLAYING, COMPLETED
    }

    public Game() {
        dealer = Direction.NORTH;
        rounds = new ArrayList<Round>();
    }

    public Game(Direction dealer) {
        this.dealer = dealer;
        rounds = new ArrayList<Round>();
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
    
    public Direction getDealer() {
        return dealer;
    }
    
    public void setDealer(Direction dealer) {
        this.dealer = dealer;
    }

    public void addRound(Round round) {
        rounds.add(round);
    }

    public ArrayList<Round> getRounds () {
        return rounds;
    }

    public Round getCurrentRound() {
        return rounds.get(rounds.size() - 1);
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
