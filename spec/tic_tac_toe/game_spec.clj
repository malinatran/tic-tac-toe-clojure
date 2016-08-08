(ns tic-tac-toe.game-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game :refer :all]
            [tic-tac-toe.player :refer :all]))

(describe "tic-tac-toe game"

          (around [it]
                  (with-out-str (it)))

          (before-all
            (def human-player (new-human-player "O"))
            (def computer-player (new-computer-player "X"))
            (def players [computer-player human-player])
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
                                 (with-out-str (announce-outcome winning-board players))))

                    (it "prints a message about a draw if there is no win"
                        (should= "Nobody wins in the game of life - er, I mean, tic-tac-toe.\n"
                                 (with-out-str (announce-outcome tie-board players)))))

          (describe "mark-board-with-move"
                    (it "returns a board with translated cell for human player"
                        (let [board ["O" nil "X"
                                     "O" "O" nil
                                     nil nil nil]]
                          (should= board (with-in-str "1" (mark-board-with-move partial-board players human-player)))))

                    (it "returns a board with move for computer player"
                        (let [board [nil nil "X"
                                     "O" "O" "X"
                                     nil nil nil]]
                          (with-redefs [get-move (constantly 6)]
                            (should= board (mark-board-with-move partial-board players computer-player))))))

          (describe "first-player"
                    (it "returns the human player as the first player if user input is 'Y'"
                        (should= human-player (first-player players "Y")))

                    (it "returns the computer player as the first player if user input is 'N'"
                        (should= computer-player (first-player players "N"))))

          (describe "run-game-loop"
                    (it "displays board and announces winner if game is over"
                        (should= "\n |  X  |  O  |  X  | \n |  O  |  X  |  O  | \n |  O  |  O  |  X  | \n\nX wins!\n"
                                 (with-out-str (run-game-loop winning-board players computer-player))))

                    (it "displays board and announces draw if game is over"
                        (should= "\n |  X  |  O  |  X  | \n |  X  |  X  |  O  | \n |  O  |  X  |  O  | \n\nNobody wins in the game of life - er, I mean, tic-tac-toe.\n"
                                 (with-out-str (run-game-loop tie-board players computer-player))))))
