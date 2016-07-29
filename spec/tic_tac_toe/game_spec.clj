(ns tic-tac-toe.game-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game :refer :all]))

(describe "tic-tac-toe game"

          (around [it]
                  (with-out-str (it)))

          (before-all
            (def x-marker "X")
            (def o-marker "O")
            (def partial-board (vec [nil nil "X"
                                     "O" "O" nil
                                     nil nil nil]))
            (def winning-board (vec ["X" "O" "X"
                                     "O" "X" "O"
                                     "O" "O" "X"]))
            (def tie-board (vec ["X" "O" "X"
                                 "X" "X" "O"
                                 "O" "X" "O"])))

          (describe "announce-outcome"
                    (it "prints a message about the marker that won"
                        (should= "X wins!\n"
                                 (with-out-str (announce-outcome winning-board))))

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
                        (should= "\n |  X  |  O  |  X  | \n |  O  |  X  |  O  | \n |  O  |  O  |  X  | \n\nX wins!\n"
                                 (with-out-str (run-game-loop winning-board x-marker))))))
