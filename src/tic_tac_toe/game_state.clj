(ns tic-tac-toe.game-state
  (:require [tic-tac-toe.board :refer [any-column-filled? any-diagonal-filled? any-row-filled? board-filled?]]))

(defn switch-player
  [players]
  (conj (vec (rest players)) (first players)))

(defn winner?
  [board player]
  (let [marker (.marker player)]
    (or (any-row-filled? board marker)
        (any-column-filled? board marker)
        (any-diagonal-filled? board marker))))

(defn get-winner
  [board players]
  (first (filter (fn [player]
                   (winner? board player)) players)))

(defn win?
  [board players]
  (boolean (get-winner board players)))

(defn tie?
  [board players]
  (and (board-filled? board) (not (win? board players))))

(defn game-over?
  [board players]
  (or (win? board players) (tie? board players)))
