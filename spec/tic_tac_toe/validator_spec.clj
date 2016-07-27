(ns tic-tac-toe.validator-spec
  (require [speclj.core :refer :all]
           [tic-tac-toe.validator :refer :all]))

(describe "input validator"

          (before-all
            (def board [nil nil nil
                        "X" "O" nil
                        nil nil nil]))

          (describe "valid-move?"
                    (it "returns true if user entered a valid move of 3 for a 3x3 board"
                        (should= true (valid-move? board 3 9)))

                    (it "returns false if user entered an invalid move that does not exist on a 3x3 board"
                        (should= false (valid-move? board 10 9)))

                    (it "returns false if user entered a move for a cell that is already filled"
                        (should= false (valid-move? board 4 9))))

          (describe "valid-size?"
                    (it "returns true if user entered a valid board size of 5"
                        (should= true (valid-size? 5)))

                    (it "returns false if user entered an invalid board size less than 3"
                        (should= false (valid-size? 2)))))
