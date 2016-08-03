(ns tic-tac-toe.game-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game :refer :all]
            [tic-tac-toe.player :refer :all]))

(describe "tic-tac-toe game"

          (around [it]
                  (with-out-str (it)))

          (before-all
            (def current-player-is-computer [(new-computer-player "X") (new-human-player "O")])
            (def current-player-is-human [(new-human-player "O") (new-computer-player "X")])
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
                                 (with-out-str (announce-outcome winning-board current-player-is-computer))))

                    (it "prints a message about a draw if there is no win"
                        (should= "Nobody wins in the game of life - er, I mean, tic-tac-toe.\n"
                                 (with-out-str (announce-outcome tie-board current-player-is-computer)))))

          (describe "mark-board-with-move"
                    (it "returns a board with translated cell for human player"
                        (let [board ["O" nil "X"
                                     "O" "O" nil
                                     nil nil nil]]
                          (should= board (with-in-str "1" (mark-board-with-move partial-board current-player-is-human)))))

                    (it "returns a board with move for computer player"
                        (let [board [nil nil "X"
                                     "O" "O" "X"
                                     nil nil nil]]
                          (with-redefs [get-move (constantly 6)]
                            (should= board (mark-board-with-move partial-board current-player-is-computer))))))

          (describe "run-game-loop"
                    (it "displays board and announces winner if game is over"
                        (should= "\n |  X  |  O  |  X  | \n |  O  |  X  |  O  | \n |  O  |  O  |  X  | \n\nX wins!\n"
                                 (with-out-str (run-game-loop winning-board current-player-is-computer))))

                    (it "displays board and announces draw if game is over"
                        (should= "\n |  X  |  O  |  X  | \n |  X  |  X  |  O  | \n |  O  |  X  |  O  | \n\nNobody wins in the game of life - er, I mean, tic-tac-toe.\n"
                                 (with-out-str (run-game-loop tie-board current-player-is-computer))))))
