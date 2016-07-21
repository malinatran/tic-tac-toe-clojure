(ns tic-tac-toe.game
  (:require [tic-tac-toe.board :as board]))

(defn winner?
  [board marker]
  (or (board/any-row-filled? board marker)
      (board/any-column-filled? board marker)
      (board/either-diagonal-filled? board marker)))

(defn win?
  [board markers]
  (loop [board board
         markers markers]
    (if (empty? markers)
      false
      (or
        (winner? board (first markers))
        (recur board (rest markers))))))

(defn get-all-markers
  [board]
  (distinct board))

(defn game-over?
  [board]
  (let [markers (get-all-markers board)]
    (or (board/board-filled? board) (win? board markers))))

(defn switch-player
  [marker]
  (if (= "X" marker)
    "O"
    "X"))
