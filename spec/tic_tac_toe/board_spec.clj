(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "tic-tac-toe board"

          (before-all
            (def board (create-board 9))
            (def size 3)
            (def x-marker "X")
            (def o-marker "O")
            (def small-board (vec [nil nil nil nil]))
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

          (describe "get-length"
                    (it "returns 9 as the length for a 3x3 board"
                        (should= 9 (get-length board)))

                    (it "returns 4 as the length for a 4x4 board"
                        (should= 4 (get-length small-board))))

          (describe "get-size"
                    (it "returns 3 as the square root of a board with a length of 9"
                        (should= 3 (get-size board)))

                    (it "returns 2 as the square root of a board with a length of 4"
                        (should= 2 (get-size small-board))))

          (describe "filled-with-marker?"
                              (it "returns true if set of cells is filled with same marker"
                                  (let [cells ["X" "X" "X"]]
                                    (should= true (filled-with-marker? cells x-marker))))

                              (it "returns false if set of cells is not filled with same marker"
                                  (let [cells ["X" "O" "X"]]
                                    (should= false (filled-with-marker? cells x-marker)))))

          (describe "get-rows"
                    (it "returns a collection of collection representing rows"
                        (should= [["X" "O" "X"]["O" "X" "X"]["O" "X" "O"]]
                                 (get-rows full-board size))))

          (describe "any-row-filled?"
                    (it "returns true if horizontally adajcent cells are filled with the same marker"
                        (should= true (any-row-filled? horizontal-board o-marker)))

                    (it "returns false if horizontally adjacent cells are not filled with the same marker"
                        (should= false (any-row-filled? full-board o-marker))))

          (describe "get-columns"
                    (it "returns a collection of collection representing columns"
                        (should= [["X" "X" "X"][nil nil nil][nil nil nil]]
                                  (get-columns vertical-board size))))

           (describe "any-column-filled?"
                      (it "returns true if vertically adjacent cells are filled with the same marker"
                        (should= true (any-column-filled? vertical-board x-marker)))

                    (it "returns false if vertically adjacent cells are not filled with the same marker"
                        (should= false (any-column-filled? full-board o-marker))))

          (describe "get-diagonals"
                    (it "returns a vector of cells representing a board's backward diagonal"
                        (should= [[nil "X" nil]["X" "X" "X"]]
                                 (get-diagonals backward-diagonal-board size))))

          (describe "any-diagonal-filled?"
                    (it "returns true if a forward diagonal is filled"
                        (should= true (any-diagonal-filled? forward-diagonal-board o-marker)))

                    (it "returns true if a backward diagonal is filled"
                        (should= true (any-diagonal-filled? backward-diagonal-board x-marker)))

                    (it "returns false if neither diagonal is filled"
                        (should= false (any-diagonal-filled? full-board o-marker)))))