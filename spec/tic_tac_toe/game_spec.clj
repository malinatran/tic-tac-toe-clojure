(ns tic-tac-toe.game-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game :refer :all]
            [tic-tac-toe.player :refer :all]
            [tic-tac-toe.user-interface :refer [print-goodbye]]))

(describe "tic-tac-toe game"

          (around [it]
                  (with-out-str (it)))

          (before-all
            (def human-player-x (new-human-player "X"))
            (def computer-player-x (new-computer-player "X"))
            (def human-player-o (new-human-player "O"))
            (def human-computer-players [computer-player-x human-player-o])
            (def human-players [human-player-x human-player-o])
            (def partial-board (vec [nil nil "X"
                                     "O" "O" nil
                                     nil nil nil]))
            (def winning-board (vec ["X" "O" "X"
                                     "O" "X" "O"
                                     "O" "O" "X"]))
            (def tie-board (vec ["X" "O" "X"
                                 "X" "X" "O"
                                 "O" "X" "O"])))

          (describe "display-postgame-menu"
                    (it "calls a function to start game if user wants to play again"
                        (with-in-str "1"
                          (should-invoke start {}
                                 (display-postgame-menu))))

                    (it "prints a goodbye message"
                        (with-in-str "2"
                          (should-invoke print-goodbye {}
                                 (display-postgame-menu)))))

          (describe "announce-outcome"
                    (it "prints a message about the marker that won"
                        (should= "X wins!\n"
                                 (with-out-str (announce-outcome winning-board human-computer-players))))

                    (it "prints a message about a draw if there is no win"
                        (should= "Nobody wins in the game of life - er, I mean, tic-tac-toe.\n"
                                 (with-out-str (announce-outcome tie-board human-computer-players)))))

          (describe "mark-board-with-move"
                    (it "returns a board with translated cell for human player"
                        (let [board ["O" nil "X"
                                     "O" "O" nil
                                     nil nil nil]]
                          (should= board (with-in-str "1" (mark-board-with-move partial-board human-computer-players human-player-o)))))

                    (it "returns a board with move for computer player"
                        (let [board [nil nil "X"
                                     "O" "O" "X"
                                     nil nil nil]]
                          (with-redefs [get-move (constantly 6)]
                            (should= board (mark-board-with-move partial-board human-computer-players computer-player-x))))))

          (describe "run-game-loop"
                    (it "displays board and announces winner if game is over"
                        (should-invoke display-postgame-menu {}
                                 (with-out-str (run-game-loop winning-board human-computer-players computer-player-x)))))

          (describe "setup-players"
                    (it "returns a vector with a type of computer player if it is a single-player game"
                        (let [game 1]
                          (should= (type (new-computer-player "X")) (type (first (setup-players game))))
                          (should= (type (new-human-player "O")) (type (second (setup-players game))))))

                    (it "returns a vector with a type of human player if it is a two-player game"
                        (let [game 2]
                          (should= (type (new-human-player "X")) (type (first (setup-players game))))
                          (should= (type (new-human-player "O")) (type (second (setup-players game)))))))

          (describe "setup-player-order"
                    (it "calls on function to run game loop"
                        (let [size 3]
                          (with-in-str "Y"
                            (should-invoke run-game-loop {} (setup-player-order 3 human-computer-players))))))

          (describe "start"
                    (it "calls on function to setup game if user selected single-player game"
                        (with-in-str "1\n3"
                          (should-invoke setup-player-order {} (start))))

                    (it "calls on function to run game loop if user selected two-player game"
                        (with-in-str "2\n3"
                          (should-invoke run-game-loop {} (start)))))

          (describe "main"
                    (it "calls on function to start game"
                        (should-invoke start {} (-main)))))
