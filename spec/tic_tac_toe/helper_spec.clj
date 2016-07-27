(ns tic-tac-toe.helper-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.helper :refer :all]))

(describe "user interface helper"

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

          (describe "replace-nils-with-indexes"
                    (it "displays the board's grid with numerical values"
                        (let [board [1 2 3 4 5 6 7 8 9]]
                          (should= board (replace-nils-with-indexes empty-board))))

                    (it "displays the board's grid with markers and numerical values"
                        (let [board ["X" 2 3 "O" "X" 6 7 8 9]]
                          (should= board (replace-nils-with-indexes partial-board))))))
