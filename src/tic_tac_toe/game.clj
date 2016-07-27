(ns tic-tac-toe.game
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.user-interface :as ui]
            [tic-tac-toe.computer-player :as computer]
            [tic-tac-toe.human-player :as human])
  (:gen-class))

(def player-markers ["X" "O"])

(defn winner?
  [board marker]
  (or (board/any-row-filled? board marker)
      (board/any-column-filled? board marker)
      (board/any-diagonal-filled? board marker)))

(defn get-winner
  [board]
  (first (filter (fn [marker]
                   (winner? board marker)) player-markers)))

(defn win?
  [board]
  (boolean (get-winner board)))

(defn game-over?
  [board]
  (or (board/board-filled? board) (win? board)))

(defn select-first-player
  []
  (rand-nth player-markers))

(defn switch-player
  ([marker]
   (switch-player marker player-markers))

  ([marker markers]
   (let [last-index (- (count markers) 1)
         current-index (.indexOf markers marker)]
     (if (= current-index 0)
       (get markers last-index)
       (get markers (- current-index 1))))))

(defn announce-outcome
  [board]
  (if (win? board)
    (ui/print-outcome (get-winner board))
    (ui/print-outcome)))

(defn prompt-for-move
  [board player]
  (if (= player "X")
    (computer/make-random-move board)
    (do (ui/print-board board)
        (human/make-move board))))

(defn get-move
  [board player]
  (let [move (prompt-for-move board player)
        cell (ui/translate-move move)]
    (if (= player "X")
      (board/mark-cell board move player)
      (board/mark-cell board cell player))))

(defn run-game-loop
  [board player]
  (loop [board board
         player player]
    (if (game-over? board)
      (do (ui/print-board board)
          (announce-outcome board))
      (recur (get-move board player) (switch-player player)))))

(defn -main
  []
  (ui/welcome-message)
  (let [size (ui/prompt-for-size)
        player (select-first-player)]
    (run-game-loop (board/create-board size) player)))
