package dev.isaac.bridge.entity.model;

import javax.persistence.*;

import dev.isaac.bridge.entity.enums.Direction;

@Entity
@Table(name="round")
public class Round {

    @Column(name="roundId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long handId;

    @ManyToOne
    @JoinColumn(name = "winningBidId", nullable = false)
    private Bid winningBid;

    @Enumerated(EnumType.STRING)
    private Direction dealerDirection;

    private int northSouthTricksTaken;

    private int eastWestTricksTaken;

    private TrickHistory trickHistory;

    private BidHistory bidHistory;

    // --- Constructors ---
    public Round() {
    }

    public Round(Direction dealerDirection) {
        this.dealerDirection = dealerDirection;
        this.northSouthTricksTaken = 0;
        this.eastWestTricksTaken = 0;
        this.trickHistory = new TrickHistory(dealerDirection);
        this.bidHistory = new BidHistory();
    }

    // --- Getters and Setters ---
    public TrickHistory getTrickHistory() {
        return trickHistory;
    }

    public BidHistory getBidHistory() {
        return bidHistory;
    }

    public Long getHandId() {
        return handId;
    }

    public void setHandId(Long handId) {
        this.handId = handId;
    }

    public Bid getWinningBid() {
        return winningBid;
    }

    public void setWinningBid(Bid winningBid) {
        this.winningBid = winningBid;
    }

    public Direction getDealerDirection() {
        return dealerDirection;
    }

    public void setDealerDirection(Direction dealerDirection) {
        this.dealerDirection = dealerDirection;
    }

    public int getNorthSouthTricksTaken() {
        return northSouthTricksTaken;
    }

    public void setNorthSouthTricksTaken(int northSouthTricksTaken) {
        this.northSouthTricksTaken = northSouthTricksTaken;
    }

    public int getEastWestTricksTaken() {
        return eastWestTricksTaken;
    }

    public void setEastWestTricksTaken(int eastWestTricksTaken) {
        this.eastWestTricksTaken = eastWestTricksTaken;
    }

    // --- Utility Methods ---
    @Override
    public String toString() {
        return "Round{" +
                "handId=" + handId +
                ", winningBidId=" + (winningBid != null ? winningBid.getId() : null) +
                ", dealerDirection=" + dealerDirection +
                ", northSouthTricksTaken=" + northSouthTricksTaken +
                ", eastWestTricksTaken=" + eastWestTricksTaken +
                '}';
    } 

}

