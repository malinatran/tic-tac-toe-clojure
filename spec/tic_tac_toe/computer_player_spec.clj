(ns tic-tac-toe.computer-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.computer-player :refer :all]))

(describe "computer player"

          (before-all
            (def o-marker "O")
            (def x-marker "X")
            (def depth 0)
            (def scores {6 10 4 9 5 8})
            (def board-without-center [nil nil nil nil])
            (def winning-o-board ["O" "O" "O"
                                  "X" "X" "O"
                                  "X" "O" "X"])
            (def winning-x-board ["X" "O" "O"
                                  "O" "X" "O"
                                  "X" "O" "X"])
            (def empty-board [nil nil nil
                              nil nil nil
                              nil nil nil])
            (def tie-board ["X" "O" "O"
                            "O" "X" "X"
                            "X" "O" "O"])
            (def partial-board ["X" "O" nil
                                nil "O" nil
                                nil nil nil]))

          (describe "first-move?"
                    (it "returns true if this is the computer's first move"
                        (should= true (first-move? empty-board)))

                    (it "returns false if this is not the computer's first move"
                        (should= false (first-move? partial-board))))

          (describe "even-numbered-board?"
                    (it "returns true if the board is 4x4"
                        (let [length 4]
                          (should= true (even-numbered-board? length))))

                    (it "returns false if the board is 5x5"
                        (let [length 5]
                          (should= false (even-numbered-board? length)))))

          (describe "score-move"
                    (it "returns 10 if winner is computer"
                        (should= 10 (score-move winning-x-board x-marker o-marker depth)))

                    (it "returns -10 if winner is human"
                        (should= -10 (score-move winning-o-board x-marker o-marker depth)))

                    (it "returns 0 if neither winner is human or computer"
                        (should= 0 (score-move tie-board x-marker o-marker depth))))

          (describe "best-move"
                    (it "returns the highest-scored move if current player is computer"
                        (should= 6 (best-move x-marker scores)))

                    (it "returns the lowest-scored move if current player is human"
                        (should= 5 (best-move o-marker scores))))

          (describe "make-move"
                    (it "returns center cell if computer is making first move on a 3x3 board"
                        (should= 4 (make-computer-move empty-board x-marker o-marker)))

                    (it "returns the first cell of a board if computer is making first move on a 2x2 board"
                        (should= 0 (make-computer-move board-without-center x-marker o-marker)))

                    (it "returns minimax move if computer is not making first move"
                        (with-redefs [best-move (constantly 6)]
                          (should= 6 (make-computer-move partial-board x-marker o-marker))))))
