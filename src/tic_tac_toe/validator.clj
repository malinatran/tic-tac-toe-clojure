(ns tic-tac-toe.validator
  (:require [tic-tac-toe.board :refer [cell-empty?]]))

(defn valid-move?
  [board input length]
  (and (> input 0)
       (<= input length)
       (cell-empty? board (- input 1))))

(defn valid-size?
  [input]
  (> input 2))
