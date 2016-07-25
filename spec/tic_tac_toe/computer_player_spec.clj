(ns tic-tac-toe.computer-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.computer-player :refer :all]))

(describe "computer player"

          (before-all
            (def board-without-center [nil nil nil nil])
            (def empty-board [nil nil nil
                              nil nil nil
                              nil nil nil])
            (def partial-board ["X" "O" nil
                                nil "O" nil
                                nil nil nil]))

          (describe "make-first-move"
                    (it "returns the center cell of a board, if the board has a center"
                        (should= 4 (make-first-move empty-board)))

                    (it "returns the first cell of a board, if the board does not have a center"
                        (should= 0 (make-first-move board-without-center)))))


