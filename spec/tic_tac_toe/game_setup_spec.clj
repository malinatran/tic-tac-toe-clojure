(ns tic-tac-toe.game-setup-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game-setup :refer :all]))

(describe "game setup"

          (around [it]
                  (with-out-str (it)))

          (before-all
            (def x-marker "X")
            (def empty-board [nil nil nil
                              nil nil nil
                              nil nil nil]))

          (describe "prompt-for-game-type"
                    (it "calls on function to run loop"
                        (should-invoke run-loop {} (prompt-for-game-type))))

          (describe "prompt-for-first-player"
                    (it "calls on function to run loop"
                        (should-invoke run-loop {} (prompt-for-first-player))))

          (describe "prompt-for-size"
                    (it "calls on function to run loop"
                        (should-invoke run-loop {} (prompt-for-size))))

          (describe "prompt-for-postgame-option"
                    (it "calls on function to run loop"
                        (should-invoke run-loop {} (prompt-for-postgame-option))))

          (describe "get-human-move"
                    (it "returns move if move provided by user input is valid"
                        (should= 6
                                 (with-in-str "7" (get-human-move empty-board x-marker))))

                    (it "recursively calls function and prompts for size if size provided by user input is invalid"
                        (should= 6
                                 (with-in-str "14\n7" (get-human-move empty-board x-marker))))))
