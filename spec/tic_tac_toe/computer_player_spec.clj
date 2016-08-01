(ns tic-tac-toe.computer-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.computer-player :refer :all]))

(describe "computer player"

          (before-all
            (def o-marker "O")
            (def x-marker "X")
            (def board-without-center [nil nil nil nil])
            (def empty-board [nil nil nil
                              nil nil nil
                              nil nil nil])
            (def partial-board ["X" "O" nil
                                nil "O" nil
                                nil nil nil]))

          (describe "best-move"
                    (it "returns the highest-scored move if current player is computer"
                        (should= 6 (best-move x-marker scores)))

                    (it "returns the lowest-scored move if current player is human"
                        (should= 5 (best-move o-marker scores))))

          (describe "make-computer-move"
                    (it "returns center cell if computer is making first move on a 3x3 board"
                        (should= 4 (make-computer-move empty-board x-marker o-marker)))

                    (it "returns the first cell of a board if computer is making first move on a 2x2 board"
                        (should= 0 (make-computer-move board-without-center x-marker o-marker)))

                    (it "returns minimax move if computer is not making first move"
                        (with-redefs [best-move (constantly 6)]
                          (should= 6 (make-computer-move partial-board x-marker o-marker))))))
