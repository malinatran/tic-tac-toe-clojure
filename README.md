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
* `board_formatter.clj`: handles board formatting for command-line display
* `computer_player.clj`: houses minimax algorithm
* `game.clj`: is responsible for all events of a game
* `game_setup.clj`: prompts user for game options
* `game_state.clj`: handles logic for state of game and outcome
* `messages.clj`: stores messages for command-line display
* `play.clj`: is the entry point
* `player.clj`: a protocol with methods for getting and making moves from players
* `user_interface.clj`: coordinates command-line printing
* `validator.clj`: checks for valid user input

#### Organization
![Diagram](https://s3.postimg.io/e50ay6cr7/clojure_ns_diagram.jpg)
