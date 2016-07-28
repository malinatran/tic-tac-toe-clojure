(ns tic-tac-toe.game
  (:require [tic-tac-toe.board :refer [create-board mark-cell]]
            [tic-tac-toe.computer-player :as computer]
            [tic-tac-toe.game-state :refer [game-over? get-winner select-first-player switch-player win?]]
            [tic-tac-toe.helper :refer [translate-move]]
            [tic-tac-toe.human-player :as human]
            [tic-tac-toe.user-interface :refer [prompt-for-size print-outcome print-board welcome-message]])
  (:gen-class))

(defn is-computer?
  [player]
  (= player "X"))

(defn announce-outcome
  [board]
  (if (win? board)
    (print-outcome (get-winner board))
    (print-outcome)))

(defn prompt-for-move
  [board player]
  (if (is-computer? player)
    (computer/make-move board player (switch-player player))
    (do (print-board board)
        (human/make-move board))))

(defn get-move
  [board player]
  (let [move (prompt-for-move board player)
        cell (translate-move move)]
    (if (is-computer? player)
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
