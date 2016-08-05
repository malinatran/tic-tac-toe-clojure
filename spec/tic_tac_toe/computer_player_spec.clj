(ns tic-tac-toe.computer-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.computer-player :refer :all]
            [tic-tac-toe.player :refer :all]))

(describe "computer player"

          (before-all
            (def x-marker "X")
            (def x-player (new-computer-player "X"))
            (def o-player (new-human-player "O"))
            (def players [x-player o-player])
            (def board-without-center [nil nil nil nil])
            (def empty-board [nil nil nil
                              nil nil nil
                              nil nil nil])
            (def partial-board ["X" "O" nil
                                nil "O" nil
                                nil nil nil]))

          (describe "update-computer-marker"
                    (it "returns new value of computer marker"
                        (with-redefs [computer-marker (atom nil)]
                          (should= x-marker (update-computer-marker "X")))))

          (describe "is-computer?"
                    (it "returns true if computer's marker matches player's marker"
                        (with-redefs [computer-marker (atom "X")]
                          (should= true (is-computer? x-player))))

                    (it "returns false if computer's marker does not match player's marker"
                        (with-redefs [computer-marker (atom "X")]
                          (should= false (is-computer? o-player)))))

          (describe "get-minimax-move"
                    (it "returns minimax move if computer is not making first move"
                        (with-redefs [best-move (constantly 6)]
                          (should= 6 (get-minimax-move partial-board players x-marker))))))
