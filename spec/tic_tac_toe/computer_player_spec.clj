(ns tic-tac-toe.computer-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.computer-player :refer :all]
            [tic-tac-toe.player :refer :all]))

(describe "computer player"

          (before-all
            (def x-marker "X")
            (def computer-player (new-computer-player "X"))
            (def human-player (new-human-player "O"))
            (def players [computer-player human-player])
            (def empty-board [nil nil nil
                              nil nil nil
                              nil nil nil])
            (def partial-board-1 ["X" "O" nil
                                  nil "O" nil
                                  nil nil nil])
            (def partial-board-2 ["X" nil "X"
                                  "O" "O" nil
                                  "X" "O" "O"])
            (def scored-moves {1 10, 2 9, 3 8}))

          (describe "get-best-move"
                    (it "returns the move with the highest score if current player is computer player"
                        (should= 1 (best-move computer-player scored-moves x-marker)))

                    (it "returns the move with the lowest score if current player is not computer player"
                        (should= 3 (best-move human-player scored-moves x-marker))))

          (describe "get-minimax-move"
                    (it "returns a numeric value representing a move"
                        (with-redefs [best-move (constantly 6)]
                          (should= 6 (get-minimax-move partial-board-1 players x-marker))))

                    (it "calls a function to get the best move"
                        (should-invoke best-move {} (get-minimax-move empty-board players x-marker)))

                    (it "returns the best possible move"
                        (should= 1 (get-minimax-move partial-board-2 players x-marker)))))
