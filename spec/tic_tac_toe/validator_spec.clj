(ns tic-tac-toe.validator-spec
  (require [speclj.core :refer :all]
           [tic-tac-toe.validator :refer :all]))

(describe "input validator"

          (before-all
            (def length 9)
            (def board [nil nil nil
                        "X" "O" nil
                        nil nil nil]))

          (describe "valid-selection?"
                    (it "returns true if user entered a 1"
                        (let [input 1]
                          (should= true (valid-selection? input))))

                    (it "returns false if user entered a number besides 1 or 2"
                        (let [input 20]
                          (should= false (valid-selection? input)))))

          (describe "valid-turn?"
                    (it "returns true if user entered 'N'"
                        (let [input "N"]
                          (should= true (valid-turn? input))))

                    (it "returns false is user entered random letters"
                        (let [input "Malina"]
                          (should= false (valid-turn? input)))))

          (describe "valid-move?"
                    (it "returns true if user entered a valid move of 3 for a 3x3 board"
                        (let [input 3]
                          (should= true (valid-move? board input length))))

                    (it "returns false if user entered an invalid move that does not exist on a 3x3 board"
                        (let [input 10]
                          (should= false (valid-move? board input length))))

                    (it "returns false if user entered a move for a cell that is already filled"
                        (let [input 4]
                          (should= false (valid-move? board input length)))))

          (describe "valid-size?"
                    (it "returns true if user entered a valid board size of 5"
                        (let [input 5]
                          (should= true (valid-size? input))))

                    (it "returns false if user entered an invalid board size less than 3"
                        (let [input 2]
                          (should= false (valid-size? input))))))
