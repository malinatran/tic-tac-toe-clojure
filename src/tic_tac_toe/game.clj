(ns tic-tac-toe.game
  (:require [tic-tac-toe.board :as board]))

(defn get-all-markers
  [board]
  (distinct board))

(defn winner?
  [board marker]
  (or (board/any-row-filled? board marker)
      (board/any-column-filled? board marker)
      (board/any-diagonal-filled? board marker)))

(defn get-winner
  [board]
  (let [markers (get-all-markers board)]
    (first (filter (fn [marker]
              (winner? board marker)) markers))))

(defn win?
  [board]
  (boolean (get-winner board)))

(defn game-over?
  [board]
  (let [markers (get-all-markers board)]
    (and (board/board-filled? board) (win? board))))

(defn switch-player
  [marker]
  (if (= "X" marker)
    "O"
    "X"))
