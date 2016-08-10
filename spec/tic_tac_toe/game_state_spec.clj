(ns tic-tac-toe.game-state-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game-state :refer :all]
            [tic-tac-toe.player :refer :all]))

(describe "game state of tic-tac-toe"

          (before-all
            (def human-player (new-human-player "O"))
            (def computer-player (new-computer-player "X"))
            (def players [computer-player human-player])
            (def partial-board (vec [nil nil "X"
                                     "O" "O" nil
                                     nil nil nil]))
            (def winning-board-full (vec ["X" "O" "X"
                                          "O" "X" "O"
                                          "O" "O" "X"]))
            (def winning-board-partial (vec [nil "X" nil
                                             nil "X" nil
                                             nil "X" nil]))
            (def tie-board (vec ["X" "O" "X"
                                 "X" "X" "O"
                                 "O" "X" "O"])))

          (describe "single-player-game?"
                    (it "returns true if it is a single-player game"
                        (let [input 1]
                          (should= true (single-player-game? input))))

                    (it "returns false if it is a two-player game"
                        (let [input 2]
                          (should= false (single-player-game? input)))))

          (describe "select-first-player"
                    (it "returns the human player as the first player if user input is 'Y'"
                        (should= human-player (select-first-player players "Y")))

                    (it "returns the computer player as the first player if user input is 'N'"
                        (should= computer-player (select-first-player players "N"))))

          (describe "switch-player"
                    (it "returns the computer player if current player is human player"
                        (should= computer-player (switch-player players human-player)))

                    (it "returns the human player if current player is computer player"
                        (should= human-player (switch-player players computer-player))))

          (describe "winner?"
                    (it "returns true if marker is a winner"
                        (should= true (winner? winning-board-full (first players))))

                    (it "returns false if marker is not a winner"
                        (should= false (winner? winning-board-full (second players)))))

          (describe "get-winner"
                    (it "returns the winner of a full board"
                        (should= (first players) (get-winner winning-board-full players)))

                    (it "returns the winner of a partial board"
                        (should= (first players) (get-winner winning-board-partial players)))

                    (it "returns nil if there is no winner"
                        (should= nil (get-winner tie-board players))))

          (describe "win?"
                    (it "returns true if the game has a win"
                        (should= true (win? winning-board-full players)))

                    (it "returns false if the game does not have a win"
                        (should= false (win? partial-board players))))

          (describe "tie?"
                    (it "returns true if the game has a draw"
                        (should= true (tie? tie-board players)))

                    (it "returns false if the game does not have a win"
                        (should= false (tie? partial-board players))))

          (describe "game-over?"
                    (it "returns true if there is a win"
                        (should= true (game-over? winning-board-full players)))

                    (it "returns true if there is a draw"
                        (should= true (game-over? tie-board players)))

                    (it "returns false if there is not a win or draw"
                        (should= false (game-over? partial-board players)))))
