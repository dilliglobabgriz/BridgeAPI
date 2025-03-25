# Bridge API
## How to use

Currently in progress, to run tests use Maven with the command ```mvn test```

## Relational Database Table Structure

### Game
| Column               | Type      | Constraints                    | Description                  |
|----------------------|-----------|--------------------------------|------------------------------|
| gameId               | integer   | PRIMARY KEY AUTO_INCREMENT     | Game identifier              |
| northId              | integer   | FOREIGN KEY                    | North player ID              |
| eastId               | integer   | FOREIGN KEY                    | East player ID               |
| southId              | integer   | FOREIGN KEY                    | South player ID              |
| westId               | integer   | FOREIGN KEY                    | West player ID               |
| firstDealerDirection | integer   | DEFAULT 0                      | [0-3] refers to N, S, E, W   |
| northSouthScore      | integer   | DEFAULT 0                      | Update after each hand       |
| eastWestScore        | integer   | DEFAULT 0                      | Update after each hand       |


### Hand
| Column               | Type      | Constraints                    | Description                  |
|----------------------|-----------|--------------------------------|------------------------------|
| handId               | integer   | PRIMARY KEY AUTO_INCREMENT     | Hand identifier              |
| gameId               | integer   | FOREIGN KEY                    | Associated game ID           |
| winningBidId         | integer   | FOREIGN KEY                    | Associated Bid               |
| dealerDirection      | integer   |                                | [0-3] refers to N, S, E, W   |
| northSouthTricksTaken| integer   |                                | Tricks taken by N/S          |
| eastWestTricksTaken  | integer   |                                | Tricks taken by E/W          |

### Bid
| Column      | Type    | Constraints                | Description                 |
|-------------|---------|----------------------------|-----------------------------|
| bidId       | integer | PRIMARY KEY AUTO_INCREMENT | Bid identifier              |
| direction   | integer |                            | [0-3] refers to N, S, E, W  |
| suit        | integer |                            | [0-4] refers C, D, H, S, NT |
| level       | integer |                            | [1-7]                       |
| isDouble    | integer | DEFAULT 0                  | 0 for false 1 for true      | 
| isRedoubled | integer | DEFAULT 0                  | 0 for false 1 for true      |

### Player
| Column   | Type        | Constraints                | Description          |
|----------|-------------|---------------------------|----------------------|
| playerId | integer     | PRIMARY KEY AUTO_INCREMENT| Player identifier    |
| botVersion| integer    | DEFAULT 1                  | 0 for human player   |
| name     | varchar(255)| DEFAULT 'Computer'         | Player name          |
    

### Cards
| Column   | Type       | Constraints                 | Description          |
|----------|------------|-----------------------------|----------------------|
| handId   | integer    | FOREIGN KEY                 | Associated handId    |
| playerId | integer    | FOREIGN KEY                 | Associated playerId  |
| card0    | varchar(2) |                             | RankABV, SuitABV     |
| ...      |            |                             |                      |
| card12   | varchar(2) |                             | Last card in hand    |
                    

## Background

Full stack web application using Java's SpringBoot framework. Allows users to play and enjoy the game of bridge.

## Requirements

Requirements can all be found in the pom.xml but I am running this using Java 17 and Spring Boot 3.4.3

## Steps
1. Initialize 4 players, human or bot
2. Initialize a new game connecting player ids to game (save to DB)
3. Initialize a new hand
    3.1 Deal 13 cards per player
    3.2 Save cards with handId and playerId foreign keys
    3.3 Get bids and save winning bid


