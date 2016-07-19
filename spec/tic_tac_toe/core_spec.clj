(ns tic-tac-toe.core-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.core :refer :all]))

(describe "tic-tac-toe board"
          (before-all
            (def board (create-board))
            (def partial-board (vec [nil nil nil
                                     nil nil nil
                                     "X" "O" "X"]))
            (def full-board (vec ["X" "O" "X"
                                  "O" "X" "X"
                                  "O" "X" "O"]))
            (def vertical-board (vec ["X" nil nil
                                      "X" nil nil
                                      "X" nil nil]))
            (def horizontal-board (vec ["O" "O" "O"
                                        nil nil nil
                                        nil nil nil]))
            (def forward-diagonal-board (vec [nil nil "O"
                                              nil "O" nil
                                              "O" nil nil]))
            (def backward-diagonal-board (vec ["X" nil nil
                                               nil "X" nil
                                               nil nil "X"])))

          (describe "create-board"
                    (it "creates a new board with 9 cells"
                        (should= 9 (count (create-board)))))

          (describe "cell-empty?"
                    (it "returns true if cell is empty space"
                        (let [cell 2]
                          (should= true (cell-empty? board cell)))))

          (describe "board-filled?"
                    (it "returns true if board does not have empty cells"
                        (should= true (board-filled? full-board)))

                    (it "returns false if board has empty cells"
                        (should= false (board-filled? board))))

          (describe "get-empty-cells"
                    (it "returns a vector of empty cell numbers"
                        (should= [0 1 2 3 4 5] (get-empty-cells partial-board)))

                    (it "returns empty vector if there are no empty cells"
                        (should= [] (get-empty-cells full-board))))

          (describe "mark-cell"
                    (it "sets cell with marker"
                        (let [cell 1 marker "X"]
                          (should= [nil "X" nil nil nil nil nil nil nil]
                                   (mark-cell board cell marker))))

                    (it "throws an error message if cell is already taken"
                        (let [cell 1 marker "O"]
                          (should-throw Error "Cell is already taken."
                                        (mark-cell full-board cell marker)))))

          (describe "clear-cell"
                    (it "sets cell to be nil"
                        (let [cell 3]
                          (should= ["X" "O" "X" nil "X" "X" "O" "X" "O"]
                                   (clear-cell full-board cell)))))

          (describe "filled-with-marker?"
                    (it "returns true if set of cells is filled with same marker"
                        (let [cells ["X" "X" "X"]
                              marker "X"]
                          (should= true (filled-with-marker? cells marker))))

                    (it "returns false if set of cells is not filled with same marker"
                        (let [cells ["X" "O" "X"]
                              marker "X"]
                          (should= false (filled-with-marker? cells marker)))))

          (describe "row-num"
                    (it "returns 3 as the square root of a board with a length of 9"
                        (should= 3 (row-num board)))

                    (it "returns 2 as the square root of a board with a length of 4"
                        (let [small-board [nil nil nil nil]]
                        (should= 2 (row-num small-board)))))

          (describe "horizontally-filled?"
                    (it "returns true if a set of three adjacent horizontal cells are filled"
                        (let [marker "O"]
                          (should= true (horizontally-filled? horizontal-board marker)))))

          (describe "vertically-filled?"
                    (it "returns true if a set of three adjacent vertical cells are filled"
                        (let [marker "X"]
                          (should= true (vertically-filled? vertical-board marker)))))

          (describe "forward-diagonal-filled?"
                    (it "returns true if a set of three"
                        (let [marker "O"]
                          (should= true (forward-diagonal-filled? forward-diagonal-board marker))))

                    (it "returns false if a set of three"
                        (let [marker "O"]
                          (should= false (forward-diagonal-filled? full-board marker)))))

          (describe "backward-diagonal-filled?"
                    (it "returns true if backward diagonal has the same marker"
                        (let [marker "X"]
                          (should= true (backward-diagonal-filled? backward-diagonal-board marker))))

                    (it "returns false if backward diagonal does not have the same marker"
                        (let [marker "X"]
                          (should= false (backward-diagonal-filled? full-board marker))))))
