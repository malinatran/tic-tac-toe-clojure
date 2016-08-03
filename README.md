## Tic-Tac-Toe | Clojure

#### Objectives
* Build a command-line game in Clojoure
* Incorporate Minimax to create an unbeatable AI
* Adhere to [SOLID principles](https://www.wikiwand.com/en/SOLID_(object-oriented_design)) and incorporate [TDD](https://www.wikiwand.com/en/Test-driven_development)

#### Instructions
You can do either one of the following:
1. Install [Leiningen](http://leiningen.org/). To run the game, enter `lein run`. To run tests, enter `lein spec` or `lein spec-a` (to see all of the assertions within their respective namespaces).
2. If you do not want to install Leiningen,  and you have Java installed, you can run `java -jar target/tic-tac-toe-0.1.0-SNAPSHOT-standalone.jar`.

#### File Structure
In `src/tic_tac_toe`, you will find the following files/namespaces and corresponding spec files in `spec/tic_tac_toe`:

* `board.clj`: has functionalities related to the grid and cells
* `board_formatter.clj`: handles all board formatting for command-line display
* `computer_player.clj`: houses minimax algorithm (implements protocol)
* `game.clj`: is responsible for all events of a game
* `game_state.clj`: handles logic for state of game and outcome
* `human_player.clj`: makes human move (implements protocol)
* `messages.clj`: stores messages for command-line display
* `player.clj`: a protocol with methods for getting and making moves from players
* `user_interface.clj`: coordinates command-line printing and prompting for user input
* `validator.clj`: checks for valid board size and move input by user

#### Organization
![Diagram](https://s31.postimg.org/i5ytymiaz/TTT_Diagram_Clojure_2016_08_03.jpg)
