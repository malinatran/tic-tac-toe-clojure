(ns tic-tac-toe.game-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game :refer :all]
            [tic-tac-toe.core :as board]))

(describe "tic-tac-toe game"

          (before-all
            (def x-marker "X")
            (def o-marker "O")
            (def markers ["X" "O"])
            (def partial-board (vec [nil nil "X"
                                     "O" "O" nil
                                     nil nil nil]))
            (def winning-board (vec ["X" "O" "X"
                                     "O" "X" "O"
                                     "O" "O" "X"]))
            (def tie-board (vec ["X" "O" "X"
                                 "X" "X" "O"
                                 "O" "X" "O"])))

          (describe "winner?"
                    (it "returns true if marker is a winner"
                        (should= true (winner? winning-board x-marker)))

                    (it "returns false if marker is not a winner"
                        (should= false (winner? winning-board o-marker))))

          (describe "win?"
                    (it "returns true if the game has a win"
                        (should= true (win? winning-board markers)))

                    (it "returns false if the game does not have a win"
                        (should= false (win? tie-board markers))))

          (describe "game-over?"
                    (it "returns true if there is a win or draw"
                        (should= true (game-over? winning-board)))

                    (it "returns false if there is not a win or draw"
                        (should= false (game-over? partial-board))))

          (describe "get-all-markers"
                    (it "returns all the players' markers in a vector"
                        (should= markers (get-all-markers winning-board))))

          (describe "switch-player"
                    (it "returns the next player"
                        (should= x-marker (switch-player o-marker)))))
