## Tic-Tac-Toe | Clojure

#### Objectives
* Build a command-line game in Clojoure
* Incorporate Minimax to create an unbeatable AI
* Adhere to [SOLID principles](https://www.wikiwand.com/en/SOLID_(object-oriented_design)) and incorporate [TDD](https://www.wikiwand.com/en/Test-driven_development)

#### Instructions
Install [Leiningen](http://leiningen.org/). To run the game, enter `lein run`. To run tests, enter `lein spec` or `lein spec-a` (to see all of the assertions within their respective namespaces).

#### File Structure
In `src/tic_tac_toe`, you will find the following files/namespaces and corresponding spec files in `spec/tic_tac_toe`:

* `board.clj`: has functionalities related to the grid and cells
* `computer_player.clj`: houses minimax algorithm
* `game.clj`: is responsible for all events of a game
* `game_state.clj`: handles logic for state of game and outcome
* `helper.clj`: translates between board's cells and moves input by user
* `human_player.clj`: makes human move
* `user_interface.clj`: handles command-line printing and formatting
* `validator.clj`: checks for valid board size and move input by user

#### Organization
![Diagram](https://s32.postimg.org/tuyn4x8z9/TTT_Diagram_Clojure.jpg)
