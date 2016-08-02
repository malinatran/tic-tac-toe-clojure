(ns tic-tac-toe.computer-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.computer-player :refer :all]
            [tic-tac-toe.player :refer :all]))

(describe "computer player"

          (before-all
            (def o-marker "O")
            (def x-marker "X")
            (def players [(new-computer-player "X") (new-human-player "O")])
            (def board-without-center [nil nil nil nil])
            (def empty-board [nil nil nil
                              nil nil nil
                              nil nil nil])
            (def partial-board ["X" "O" nil
                                nil "O" nil
                                nil nil nil]))

          (describe "get-computer-move"
                    (it "returns center cell if computer is making first move on a 3x3 board"
                        (should= 4 (get-computer-move empty-board players)))

                    (it "returns the first cell of a board if computer is making first move on a 2x2 board"
                        (should= 0 (get-computer-move board-without-center players)))

                    (it "returns minimax move if computer is not making first move"
                        (with-redefs [best-move (constantly 6)]
                          (should= 6 (get-computer-move partial-board players))))))
