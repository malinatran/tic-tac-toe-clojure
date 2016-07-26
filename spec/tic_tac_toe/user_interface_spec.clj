(ns tic-tac-toe.user-interface-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.user-interface :refer :all]
            [tic-tac-toe.board :as board]))

(describe "user interface console"

          (around [it]
                  (with-out-str (it)))

          (before-all
            (def x-marker "X")
            (def board (board/create-board 9))
            (def length (board/get-length board))
            (def mapped-board [1 2 3 4 5 6 7 8 9])
            (def empty-board [nil nil nil
                              nil nil nil
                              nil nil nil])
            (def partial-board ["X" nil nil
                                "O" "X" nil
                                nil nil nil]))

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
                                 (with-out-str (welcome-message)))))

          (describe "prompt-for-size"
                    (it "returns size if size provided by user input is valid"
                        (should= 3
                                 (with-in-str "3"
                                   (prompt-for-size))))

                    (it "recursively calls function and prompts for size if size provided by user input is invalid"
                        (should= 4
                                 (with-in-str "0\n1\n2\n4"
                                   (prompt-for-size)))))

          (describe "translate-move"
                    (it "translates move input by user to corresponding board cell"
                        (should= 8 (translate-move 9))))

          (describe "replace-nils-with-indexes"
                    (it "displays the board's grid with numerical values"
                        (let [board [1 2 3 4 5 6 7 8 9]]
                          (should= board (replace-nils-with-indexes empty-board))))

                    (it "displays the board's grid with markers and numerical values"
                        (let [board ["X" 2 3 "O" "X" 6 7 8 9]]
                          (should= board (replace-nils-with-indexes partial-board)))))

          (describe "format-board"
                    (it "adds line breaks for board visualization"
                        (let [board ["X" 2 3 "\n" "O" "X" 6 "\n" 7 8 9]]
                          (should= board (format-board partial-board)))))

          (describe "print-outcome"
                    (it "displays marker of winner if there is a win"
                        (should= "X wins!\n" (with-out-str (print-outcome x-marker))))

                    (it "displays a message that nobody won if there isn't a win"
                        (should= "Nobody wins!\n" (with-out-str (print-outcome "Nobody"))))))
