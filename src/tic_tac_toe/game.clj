(ns tic-tac-toe.game
  (:require [tic-tac-toe.board :as board]))

(def player-markers ["X" "O"])
(def current-player (rand-nth player-markers))

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
  (and (board/board-filled? board) (win? board)))

(defn switch-player
  ([marker]
  (switch-player marker player-markers))

  ([marker markers]
  (let [last-index (- (count markers) 1)
        current-index (.indexOf markers marker)]
    (if (= current-index 0)
      (get markers last-index)
      (get markers (- current-index 1))))))
