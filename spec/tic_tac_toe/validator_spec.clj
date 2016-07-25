(ns tic-tac-toe.validator-spec
  (require [speclj.core :refer :all]
           [tic-tac-toe.validator :refer :all]))

(describe "input validator"

          (describe "valid-move?"
                    (it "returns true if user entered a valid move of 3 for a 3x3 board"
                        (should= true (valid-move? 3 9)))

                    (it "returns false if user entered an invalid move that does not exist on a 3x3 board"
                        (should= false (valid-move? 10 9))))

          (describe "valid-size?"
                    (it "returns true if user entered a valid board size of 5"
                        (should= true (valid-size? 5)))

                    (it "returns false if user entered an invalid board size less than 3"
                        (should= false (valid-size? 2)))))
