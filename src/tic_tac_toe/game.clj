(ns tic-tac-toe.game
  (:require [tic-tac-toe.board :refer [create-board mark-cell]]
            [tic-tac-toe.computer-player :refer [make-random-move]]
            [tic-tac-toe.game-state :refer [game-over? get-winner select-first-player switch-player win?]]
            [tic-tac-toe.helper :refer [translate-move]]
            [tic-tac-toe.human-player :refer [make-move]]
            [tic-tac-toe.user-interface :refer [prompt-for-size print-outcome print-board welcome-message]])
  (:gen-class))

(defn announce-outcome
  [board]
  (if (win? board)
    (print-outcome (get-winner board))
    (print-outcome)))

(defn prompt-for-move
  [board player]
  (if (= player "X")
    (make-random-move board)
    (do (print-board board)
        (make-move board))))

(defn get-move
  [board player]
  (let [move (prompt-for-move board player)
        cell (translate-move move)]
    (if (= player "X")
      (mark-cell board move player)
      (mark-cell board cell player))))

(defn run-game-loop
  [board player]
  (loop [board board
         player player]
    (if (game-over? board)
      (do (print-board board)
          (announce-outcome board))
      (recur (get-move board player) (switch-player player)))))

(defn -main
  []
  (welcome-message)
  (let [size (prompt-for-size)
        player (select-first-player)]
    (run-game-loop (create-board size) player)))
