(ns tic-tac-toe.game-state
  (:require [tic-tac-toe.board :refer [any-column-filled? any-diagonal-filled? any-row-filled? board-filled?]]))

(def player-markers ["X" "O"])

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

(defn winner?
  [board marker]
  (or (any-row-filled? board marker)
      (any-column-filled? board marker)
      (any-diagonal-filled? board marker)))

(defn get-winner
  [board]
  (first (filter (fn [marker]
                   (winner? board marker)) player-markers)))

(defn win?
  [board]
  (boolean (get-winner board)))

(defn game-over?
  [board]
  (or (board-filled? board) (win? board)))
