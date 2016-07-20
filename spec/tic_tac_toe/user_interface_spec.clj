(ns tic-tac-toe.user-interface-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.user-interface :refer :all]
            [tic-tac-toe.core :as board]))

(describe "user interface console"

          (around [it]
                  (with-out-str (it)))

          (before-all
            (def board (board/create-board 9))
            (def length (count board)))

          (describe "prompt"
                    (it "tests the output of prompt"
                        (should= "Enter a custom board size:\n"
                                 (with-out-str (with-in-str "3"
                                                 (prompt "Enter a custom board size:")))))

                    (it "tests the input of prompt"
                        (should= "Los Angeles"
                                 (with-in-str "Los Angeles"
                                   (prompt "Which city do you live in?")))))

          (describe "welcome-message"
                    (it "prints welcome message"
                        (should= "Welcome to tic-tac-toe!\n"
                                 (with-out-str (with-in-str ""
                                                 (prompt "Welcome to tic-tac-toe!"))))))

          (describe "valid-size?"
                    (it "returns true if user entered a valid board size of 5"
                        (should= true (valid-size? 5)))

                    (it "returns false if user entered an invalid board size less than 3"
                        (should= false (valid-size? 2))))

          (describe "prompt-for-size"
                    (it "returns size if size provided by user input is valid"
                        (should= 3
                                 (with-in-str "3"
                                   (prompt-for-size))))

                    (it "recursively calls function and prompts for size if size provided by user input is invalid"
                        (should= 4
                                 (with-in-str "0\n1\n2\n4"
                                   (prompt-for-size)))))

          (describe "valid-move?"
                    (it "returns true if user entered a valid move of 3 for a 3x3 board"
                        (should= true (valid-move? 3 length)))

                    (it "returns false if user entered an invalid move that does not exist on a 3x3 board"
                        (should= false (valid-move? 10 length))))

          (describe "prompt-for-move"
                    (it "returns move if move provided by user input is valid"
                        (should= 7
                                 (with-in-str "7"
                                   (prompt-for-move))))

                    (it "recursively calls function and prompts for move if move provided by user input is invalid"
                        (should= 3
                                 (with-in-str "15\n10\n3"
                                   (prompt-for-move)))))

          (describe "print-board"
                    (it "displays the board's grid"
                        (let [board [1 2 3
                                     4 5 6
                                     7 8 9]]
                          (should= board (print-board 9))))))

