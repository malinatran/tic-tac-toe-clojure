(ns tic-tac-toe.game-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game :refer :all]))

(describe "tic-tac-toe game"

          (around [it]
                  (with-out-str (it)))

          (before-all
            (def x-marker "X")
            (def m-marker "M")
            (def o-marker "O")
            (def markers ["X" "M" "O"])
            (def board-with-single-position (vec ["O" nil "X"
                                                  "X" "O" "X"
                                                  "O" "X" "O"]))
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
                        (should= false (game-over? partial-board))))

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

          (describe "announce-outcome"
                    (it "prints a message about the marker that won"
                        (should= "X wins!\n"
                                 (with-out-str (announce-outcome winning-board-full))))

                    (it "prints a message about a draw if there is no win"
                        (should= "Nobody wins in the game of life - er, I mean, tic-tac-toe.\n"
                                 (with-out-str (announce-outcome tie-board)))))

          (describe "prompt-for-move"
                    (it "returns a random move if current player is computer player"
                        (with-redefs [prompt-for-move (constantly 2)]
                          (should= 2 (prompt-for-move partial-board x-marker))))

                    (it "returns a move if current player is human player"
                        (should= 1 (with-in-str "1" (prompt-for-move partial-board o-marker)))))

          (describe "get-move"
                    (it "returns a board with translated cell for human player"
                        (let [board ["O" nil "X"
                                     "O" "O" nil
                                     nil nil nil]]
                          (should= board (with-in-str "1" (get-move partial-board o-marker)))))

                    (it "returns a board with move for computer player"
                        (let [board ["X" nil "X"
                                     "O" "O" nil
                                     nil nil nil]]
                          (with-redefs [prompt-for-move (constantly 0)]
                            (should= board (get-move partial-board x-marker))))))

          (describe "run-game-loop"
                  (it "announces outcome if game is over"
                        (should= "(X O X \n O X O \n O O X)\nX wins!\n"
                                 (with-out-str (run-game-loop winning-board-full x-marker))))))
