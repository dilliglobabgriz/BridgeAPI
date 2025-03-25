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
| firstDealerDirection | integer   |                                | [0-3] refers to N, S, E, W   |
| northSouthScore      | integer   | DEFAULT 0                      | Update after each hand       |
| eastWestScore        | integer   | DEFAULT 0                      | Update after each hand       |

FOREIGN KEY (northId) REFERENCES Player(playerId)   *(Same for other directions)*

### Hand
| Column               | Type      | Constraints                    | Description                  |
|----------------------|-----------|--------------------------------|------------------------------|
| handId               | integer   | PRIMARY KEY AUTO_INCREMENT     | Hand identifier              |
| gameId               | integer   | FOREIGN KEY                    | Associated game ID           |
| dealerDirection      | integer   |                                | [0-3] refers to N, S, E, W   |
| winningContract      | varchar(3)|                                | Number, suit, doubled        |
| northCardsId         | integer   | FOREIGN KEY                    | Cards held by North          |
| eastCardsId          | integer   | FOREIGN KEY                    | Cards held by East           |
| southCardsId         | integer   | FOREIGN KEY                    | Cards held by South          |
| westCardsId          | integer   | FOREIGN KEY                    | Cards held by West           |
| northSouthTricksTaken| integer   |                                | Tricks taken by N/S          |
| eastWestTricksTaken  | integer   |                                | Tricks taken by E/W          |

FOREIGN KEY (gameId) REFERENCES Game(gameId)\
FOREIGN KEY (northCardsId) REFERENCES Cards(cardsId) *(Same for other dirs)*

### Player
| Column   | Type        | Constraints                | Description          |
|----------|-------------|---------------------------|----------------------|
| playerId | integer     | PRIMARY KEY AUTO_INCREMENT| Player identifier    |
| botVersion| integer    | DEFAULT 1                  | 0 for human player   |
| name     | varchar(255)| DEFAULT 'Computer'         | Player name          |
    

### Cards
| Column   | Type       | Constraints                 | Description          |
|----------|------------|-----------------------------|----------------------|
| cardsId  | integer    | PRIMARY KEY AUTO_INCREMENT  | Cards identifier     |
| card0    | varchar(2) |                             | RankABV, SuitABV     |
| ...      |            |                             |                      |
| card12   | varchar(2) |                             | Last card in hand    |
                    

## Background

Full stack web application using Java's SpringBoot framework. Allows users to play and enjoy the game of bridge.

## Requirements

Requirements can all be found in the pom.xml but I am running this using Java 17 and Spring Boot 3.4.3


