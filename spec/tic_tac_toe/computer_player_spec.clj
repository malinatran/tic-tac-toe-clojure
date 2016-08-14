(ns tic-tac-toe.computer-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.computer-player :refer :all]))

(describe "computer player"

          (before-all
            (def x-marker "X")
            (def empty-board [nil nil nil
                              nil nil nil
                              nil nil nil])
            (def partial-board-1 ["X" "O" nil
                                  nil "O" nil
                                  nil nil nil])
            (def partial-board-2 ["X" nil "X"
                                  "O" "O" nil
                                  "X" "O" "O"]))

          (describe "get-minimax-move"
                    (it "returns a numeric value representing a move"
                        (with-redefs [best-move (constantly 6)]
                          (should= 6 (get-minimax-move partial-board-1 x-marker))))

                    (it "calls a function to get the best move"
                        (should-invoke best-move {} (get-minimax-move empty-board x-marker)))

                    (it "returns the best possible move"
                        (should= 1 (get-minimax-move partial-board-2 x-marker)))))
