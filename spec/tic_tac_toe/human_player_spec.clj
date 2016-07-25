(ns tic-tac-toe.human-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.human-player :refer :all]))

(describe "human player"

          (around [it]
                  (with-out-str (it)))

          (before-all
            (def board [nil nil nil
                        nil nil nil
                        nil nil nil]))

          (describe "make-move"
                    (it "returns move if move provided by user input is valid"
                        (should= 7
                                 (with-in-str "7"
                                   (make-move board))))

                    (it "recursively calls function and prompts for size if size provided by user input is invalid"
                        (should= 7
                                 (with-in-str "14\n7"
                                   (make-move board))))))

