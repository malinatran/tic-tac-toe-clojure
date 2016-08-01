(ns tic-tac-toe.board-formatter-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board-formatter :refer :all]))

(describe "board formatting and translating of moves"

          (before-all
            (def empty-board [nil nil nil
                              nil nil nil
                              nil nil nil])
            (def partial-board ["X" nil nil
                                "O" "X" nil
                                nil nil nil]))

          (describe "translate-move"
                    (it "translates move input by user to corresponding board cell"
                        (let [move 9]
                          (should= 8 (translate-move move)))))

          (describe "padding"
                    (it "adds two spaces to a single digit cell number"
                        (let [cell 1]
                          (should= " 1 " (padding cell))))

                    (it "adds a space to a double digit cell number"
                        (let [cell 10]
                          (should= " 10" (padding cell))))

                    (it "returns cell number as string if it's a triple digit cell number"
                        (let [cell 100]
                          (should= "100" (padding cell)))))

          (describe "replace-nils-with-indexes"
                    (it "displays the board's grid with numerical values"
                        (let [board [1 2 3 4 5 6 7 8 9]]
                          (should= board (replace-nils-with-indexes empty-board))))

                    (it "displays the board's grid with markers and numerical values"
                        (let [board ["X" 2 3 "O" "X" 6 7 8 9]]
                          (should= board (replace-nils-with-indexes partial-board)))))
          (describe "add-cell-padding"
                    (it "adds padding to indexes and markers"
                        (let [board [ " X " "" "" " O " " X " "" "" "" ""]]
                          (should= board (add-cell-padding partial-board)))))

          (describe "add-breaks-and-dividers"
                    (it "adds line breaks and pipes for board visualization"
                        (let [board "\n |  X  |  2  |  3  | \n |  O  |  X  |  6  | \n |  7  |  8  |  9  | \n"]
                          (should= board (add-breaks-and-dividers partial-board))))))
