(ns tic-tac-toe.validator
  (:require [tic-tac-toe.board :as board :refer [cell-empty?]]))

(defn greater-than-zero?
  [input]
  (> input 0))

(defn greater-than-two?
  [input]
  (> input 2))

(defn not-greater-than-length?
  [input length]
  (<= input length))

(defn available?
  [input board]
  (board/cell-empty? board (- input 1)))

(defn valid-type?
  [input]
  (or (= input 1) (= input 2)))

(defn valid-turn?
  [input]
  (let [input (clojure.string/upper-case input)]
    (or (= input "Y") (= input "N"))))

(defn valid-move?
  [board input length]
  (and (greater-than-zero? input)
       (not-greater-than-length? input length)
       (available? input board)))

(defn valid-size?
  [input]
  (greater-than-two? input))
