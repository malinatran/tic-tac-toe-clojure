(ns tic-tac-toe.user-interface-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.user-interface :refer :all]
            [tic-tac-toe.player :refer :all]))

(describe "user interface console"

          (around [it]
                  (with-out-str (it)))

          (before-all
            (def x-marker "X")
            (def computer-player-x (new-computer-player "X"))
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

          (describe "print-welcome"
                    (it "prints welcome message"
                        (should= "Welcome to tic-tac-toe!\n"
                                 (with-out-str (print-welcome)))))

          (describe "print-goodbye"
                    (it "prints goodbye message"
                        (should= "Goodbye!\n"
                                 (with-out-str (print-goodbye)))))

          (describe "print-board"
                    (it "prints the formatted version of the board"
                        (let [board "\n |  X  |  2  |  3  | \n |  O  |  X  |  6  | \n |  7  |  8  |  9  | \n\n"]
                          (should= board (with-out-str (print-board partial-board))))))

          (describe "print-outcome"
                    (it "displays marker of winner if there is a win"
                        (should= "X wins!\n" (with-out-str (print-outcome x-marker))))

                    (it "displays a message that nobody won if there isn't a win"
                        (should= "Nobody wins in the game of life - er, I mean, tic-tac-toe.\n" (with-out-str (print-outcome)))))

          (describe "print-first-player"
                    (it "displays marker of first player and message"
                        (should= "X goes first!\n" (with-out-str (print-first-player computer-player-x))))))
