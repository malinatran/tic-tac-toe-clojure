(ns tic-tac-toe.game-state-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game-state :refer :all]))

(describe "game state of tic-tac-toe"

          (before-all
            (def x-marker "X")
            (def m-marker "M")
            (def o-marker "O")
            (def markers ["X" "M" "O"])
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

          (describe "select-first-player"
                    (it "returns X as the first player"
                        (with-redefs [select-first-player (constantly "X")]
                          (should= x-marker (select-first-player markers))
                          (should-not= o-marker (select-first-player markers))
                          (should-not= m-marker (select-first-player markers)))))

          (describe "switch-player"
                    (it "returns the other player in a collection of two players (and there is only one arg)"
                        (should= x-marker (switch-player o-marker)))

                    (it "returns the last player of a collection if current player is the first player"
                        (should= o-marker (switch-player x-marker markers)))

                    (it "returns the first player of a collection if current player is the middle-most player"
                        (should= x-marker (switch-player m-marker markers)))

                    (it "returns the player of a previous index in a collection"
                        (should= m-marker (switch-player o-marker markers))))

          (describe "winner?"
                    (it "returns true if marker is a winner"
                        (should= true (winner? winning-board-full x-marker)))

                    (it "returns false if marker is not a winner"
                        (should= false (winner? winning-board-full o-marker))))

          (describe "get-winner"
                    (it "returns the marker of the winner of a full board"
                        (should= x-marker (get-winner winning-board-full)))

                    (it "returns the marker of the winner of a partial board"
                        (should= x-marker (get-winner winning-board-partial)))

                    (it "returns nil if there is no winner"
                        (should= nil (get-winner tie-board))))

          (describe "win?"
                    (it "returns true if the game has a win"
                        (should= true (win? winning-board-full)))

                    (it "returns false if the game does not have a win"
                        (should= false (win? partial-board))))

          (describe "game-over?"
                    (it "returns true if there is a win or draw"
                        (should= true (game-over? winning-board-full)))

                    (it "returns false if there is not a win or draw"
                        (should= false (game-over? partial-board)))))
