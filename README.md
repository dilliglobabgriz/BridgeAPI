# Bridge API
Java Spring Boot bridge application
## How to use

Currently in progress, to run tests use Maven with the command ```mvn test```

## Relational Database Table Structure

### Game
| Column               | Type      | Constraints                    | Description                  |
|----------------------|-----------|--------------------------------|------------------------------|
| gameId               | long      | PRIMARY KEY AUTO_INCREMENT     | Game identifier              |
| northId              | long      | FOREIGN KEY                    | North player ID              |
| eastId               | long      | FOREIGN KEY                    | East player ID               |
| southId              | long      | FOREIGN KEY                    | South player ID              |
| westId               | long      | FOREIGN KEY                    | West player ID               |
| firstDealerDirection | integer   | DEFAULT 0                      | [0-3] refers to N, S, E, W   |
| northSouthScore      | integer   | DEFAULT 0                      | Update after each hand       |
| eastWestScore        | integer   | DEFAULT 0                      | Update after each hand       |


### Hand
| Column               | Type      | Constraints                    | Description                  |
|----------------------|-----------|--------------------------------|------------------------------|
| handId               | long      | PRIMARY KEY AUTO_INCREMENT     | Hand identifier              |
| gameId               | long      | FOREIGN KEY                    | Associated game ID           |
| winningBidId         | long      | FOREIGN KEY                    | Associated Bid               |
| dealerDirection      | integer   |                                | [0-3] refers to N, S, E, W   |
| northSouthTricksTaken| integer   |                                | Tricks taken by N/S          |
| eastWestTricksTaken  | integer   |                                | Tricks taken by E/W          |

### Bid
| Column      | Type    | Constraints                | Description                 |
|-------------|---------|----------------------------|-----------------------------|
| bidId       | long    | PRIMARY KEY AUTO_INCREMENT | Bid identifier              |
| direction   | integer |                            | [0-3] refers to N, S, E, W  |
| suit        | integer |                            | [0-4] refers C, D, H, S, NT |
| level       | integer |                            | [1-7]                       |
| isDouble    | integer | DEFAULT 0                  | 0 for false 1 for true      | 
| isRedoubled | integer | DEFAULT 0                  | 0 for false 1 for true      |

### Player
| Column   | Type        | Constraints                | Description          |
|----------|-------------|---------------------------|----------------------|
| playerId | long        | PRIMARY KEY AUTO_INCREMENT| Player identifier    |
| botVersion| integer    | DEFAULT 1                  | 0 for human player   |
| name     | varchar(255)| DEFAULT 'Computer'         | Player name          |
    

### Cards
| Column   | Type       | Constraints                 | Description          |
|----------|------------|-----------------------------|----------------------|
| handId   | long       | FOREIGN KEY                 | Associated handId    |
| playerId | long       | FOREIGN KEY                 | Associated playerId  |
| card0    | varchar(2) |                             | RankABV, SuitABV     |
| ...      |            |                             |                      |
| card12   | varchar(2) |                             | Last card in hand    |
                    

## Background

Full stack web application using Java's SpringBoot framework. Allows users to play and enjoy the game of bridge.

## Requirements

Requirements can all be found in the pom.xml but I am running this using Java 17 and Spring Boot 3.4.3

## Steps
1. Initialize 4 players
    + 4 bots
2. Initialize a new game connecting player ids to game (save to DB)
3. Initialize a new hand
    + Deal 13 cards per player
    + Save cards with handId and playerId foreign keys
4. Start the bidding process
    + Get bids from each player until a final contract is determined
    + Save the winning contract and the player who bid it to the hand
5. Start the trick taking process
    + Get one card from each player starting with left of dealer
    + Determine who won the trick and keep score
6. Score the hand
    + Determine the score based on the contract, tricks taken, and vulnerability
7. Save game/hand to the DB

## Possible Future Features
+ One or more human players
+ Adding more extensive trick or bid history to DB
+ Better UI


