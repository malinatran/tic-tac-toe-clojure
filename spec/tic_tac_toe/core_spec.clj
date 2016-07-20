(ns tic-tac-toe.core-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.core :refer :all]))

(describe "tic-tac-toe board"
          (before-all
            (def board (create-board 9))
            (def length 3)
            (def x-marker "X")
            (def o-marker "O")
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
                        (should= 9 (count (create-board 9))))

                    (it "creates a new board with 16 cells"
                        (should= 16 (count (create-board 16)))))

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
                        (let [cell 1]
                          (should= [nil "X" nil nil nil nil nil nil nil]
                                   (mark-cell board cell x-marker))))

                    (it "throws an error message if cell is already taken"
                        (let [cell 1]
                          (should-throw Error "Cell is already taken."
                                        (mark-cell full-board cell o-marker)))))

          (describe "clear-cell"
                    (it "sets cell to be nil"
                        (let [cell 3]
                          (should= ["X" "O" "X" nil "X" "X" "O" "X" "O"]
                                   (clear-cell full-board cell)))))

          (describe "filled-with-marker?"
                    (it "returns true if set of cells is filled with same marker"
                        (let [cells ["X" "X" "X"]]
                          (should= true (filled-with-marker? cells x-marker))))

                    (it "returns false if set of cells is not filled with same marker"
                        (let [cells ["X" "O" "X"]]
                          (should= false (filled-with-marker? cells x-marker)))))

          (describe "row-num"
                    (it "returns 3 as the square root of a board with a length of 9"
                        (should= 3 (row-num board)))

                    (it "returns 2 as the square root of a board with a length of 4"
                        (let [small-board [nil nil nil nil]]
                          (should= 2 (row-num small-board)))))

          (describe "horizontally-filled?"
                    (it "returns true if a set of three adjacent horizontal cells are filled"
                        (should= true (horizontally-filled? horizontal-board o-marker))))

          (describe "get-column-cells"
                    (it "returns a vector of cells belonging to a single column"
                        (should= ["X" "X" "X"] (get-column-cells vertical-board length))))

          (describe "vertically-filled?"
                    (it "returns true if vertically adjacent cells are filled with same marker"
                        (should= true (vertically-filled? vertical-board x-marker))))

          (describe "get-forward-diagonal-cells"
                    (it "returns a vector of cells belonging to a board's forward diagonal"
                        (should= ["O" "O" "O"] (get-forward-diagonal-cells forward-diagonal-board length))))

          (describe "forward-diagonal-filled?"
                    (it "returns true if forward diagonal cells have the same marker"
                        (should= true (forward-diagonal-filled? forward-diagonal-board o-marker)))

                    (it "returns false if forward diagonal cells do not have the same marker"
                        (should= false (forward-diagonal-filled? full-board o-marker))))

          (describe "get-backward-diagonal-cells"
                    (it "returns a vector of cells belonging to a board's backward diagonal"
                        (should= ["X" "X" "X"] (get-backward-diagonal-cells backward-diagonal-board length))))

          (describe "backward-diagonal-filled?"
                    (it "returns true if backward diagonal cells have the same marker"
                        (should= true (backward-diagonal-filled? backward-diagonal-board x-marker)))

                    (it "returns false if backward diagonal cells do not have the same marker"
                        (should= false (backward-diagonal-filled? full-board x-marker)))))
