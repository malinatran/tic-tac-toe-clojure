(ns tic-tac-toe.game
  (:require [tic-tac-toe.board :refer [create-board]]
            [tic-tac-toe.computer-player :refer [is-computer?]]
            [tic-tac-toe.game-state :refer :all]
            [tic-tac-toe.player :refer :all]
            [tic-tac-toe.user-interface :refer :all])
  (:import [tic-tac-toe.player])
  (:gen-class))

(defn announce-outcome
  [board players]
  (if (win? board players)
    (print-outcome (.marker (get-winner board players)))
    (print-outcome)))

(defn prompt-for-move
  [board players]
  (let [player (first players)]
    (get-move player board players)))

(defn mark-move-on-board
  [board players]
  (let [move (prompt-for-move board players)
        player (first players)]
    (make-move player board move)))

(defn run-game-loop
  [board players]
  (loop [board board
         players players]
    (if (game-over? board players)
      (do (print-board board)
          (announce-outcome board players))
      (recur (mark-move-on-board board players) (switch-player players)))))

(defn -main
  []
  (display-welcome)
  (let [size (prompt-for-size)
        players [(new-computer-player "X") (new-human-player "O")]]
    (run-game-loop (create-board size) players)))
