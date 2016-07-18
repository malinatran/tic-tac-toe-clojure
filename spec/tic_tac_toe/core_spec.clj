(ns tic-tac-toe.core-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.core :refer :all]))

(describe "tic-tac-toe board"
          (before-all
            (def board (create-board))
            (def partial-board (vec [nil nil nil nil nil nil "X" "O" "X"]))
            (def full-board (vec ["X" "O" "X" "O" "X" "O" "X" "O" "X"])))

          (describe "create-board"
                    (it "creates a new board with 9 cells"
                        (should= 9 (count (create-board)))))

          (describe "is-cell-empty?"
                    (it "returns true if cell is empty space"
                        (let [cell 2]
                          (should= true (is-cell-empty? board cell)))))

          (describe "is-board-filled?"
                    (it "returns true if board does not have empty cells"
                        (should= true (is-board-filled? full-board)))

                    (it "returns false if board has empty cells"
                        (should= false (is-board-filled? board))))

          (describe "get-empty-cells"
                    (it "returns a vector of empty cell numbers"
                        (should= [0 1 2 3 4 5] (get-empty-cells partial-board)))

                    (it "returns empty vector if there are no empty cells"
                        (should= [] (get-empty-cells full-board))))

          (describe "mark-cell"
                    (it "sets cell with marker"
                        (let [cell 1 marker "X"]
                          (should= [nil "X" nil nil nil nil nil nil nil]
                                   (mark-cell board cell marker)))))

          (describe "clear-cell"
                    (it "sets cell to be nil"
                        (let [cell 3]
                          (should= ["X" "O" "X" nil "X" "O" "X" "O" "X"]
                                   (clear-cell full-board cell)))))

          (describe "is-filled?"
                    (it "returns true if set of cells is filled with same marker"
                        (let [cells ["X" "X" "X"]
                              marker "X"]
                          (should= true (is-filled? marker cells))))

                    (it "returns false if set of cells is not filled with same marker"
                        (let [cells ["X" "O" "X"]
                              marker "X"]
                          (should= false (is-filled? marker cells))))))
