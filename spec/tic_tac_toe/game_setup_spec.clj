(ns tic-tac-toe.game-setup-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game-setup :refer :all]
            [tic-tac-toe.messages :refer :all]
            [tic-tac-toe.validator :refer :all]))

(describe "game setup"

          (around [it]
                  (with-out-str (it)))

          (before-all
            (def x-marker "X")
            (def message "Enter your input:")
            (def empty-board [nil nil nil
                              nil nil nil
                              nil nil nil]))

          (describe "get-valid-input"
                    (it "returns the value of user entered valid input"
                        (should= 3 (with-in-str "3" (get-valid-input size
                                                                     size-guidelines
                                                                     valid-size?
                                                                     get-size))))

                    (it "calls on function to prompt user again if user entered invalid input"
                        (should-invoke get-game-type {} (with-in-str "25" (get-valid-input game-type-menu
                                                                                           selection-guidelines
                                                                                           valid-selection?
                                                                                           get-game-type)))))

          (describe "run-loop"
                    (it "calls on function get valid user input"
                        (should-invoke get-valid-input {} (run-loop game-type-menu
                                                                    selection-guidelines
                                                                    valid-selection?
                                                                    get-game-type))))

          (describe "get-game-type"
                    (it "calls on function to run loop"
                        (should-invoke run-loop {} (get-game-type message))))

          (describe "get-first-player"
                    (it "calls on function to run loop"
                        (should-invoke run-loop {} (get-first-player message))))

          (describe "get-size"
                    (it "calls on function to run loop"
                        (should-invoke run-loop {} (get-size message))))

          (describe "get-postgame-option"
                    (it "calls on function to run loop"
                        (should-invoke run-loop {} (get-postgame-option message))))

          (describe "get-human-move"
                    (it "calls on function to run loop"
                        (should-invoke run-loop {} (get-human-move empty-board x-marker)))))
