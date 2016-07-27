(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.board :as board]))

(def marker "X")

(defn make-first-move
  [board]
  (let [length (count board)
        center-cell (quot length 2)]
    (if (= (mod length 2) 0)
      0
      center-cell)))

(defn make-random-move
  [board]
   (let [empty-cells (board/get-empty-cells board)]
     (rand-nth empty-cells)))
