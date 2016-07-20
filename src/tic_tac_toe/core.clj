(ns tic-tac-toe.core
  (:require [clojure.math.numeric-tower :as math]))

(defn create-board
  [size]
  (vec (repeat size nil)))

(defn cell-empty?
  [board cell]
  (nil? (get board cell)))

(defn board-filled?
  [board]
  (= -1 (.indexOf board nil)))

(defn get-empty-cells
  [board]
  (remove nil? (map-indexed (fn [index cell]
                              (if (nil? cell) index nil)) board)))

(defn mark-cell
  [board cell marker]
  (if (cell-empty? board cell)
    (assoc board cell marker)
    (throw (Error. "Cell is already taken."))))

(defn clear-cell
  [board cell]
  (assoc board cell nil))

(defn filled-with-marker?
  [cells marker]
  (reduce
    (fn [acc cell]
      (and acc (= marker cell))) true cells))

(defn row-num
  [board]
  (math/sqrt (count board)))

(defn horizontally-filled?
  ([board marker]
   (horizontally-filled? board marker 0))
  ([board marker index]
   (loop [board board
          marker marker
          length (row-num board)]
     (if (filled-with-marker? (subvec board index (+ index length)) marker)
       true
       (recur board marker (+ index length))))))

(defn get-column-cells
  ([board length]
   (get-column-cells board length 0 []))
  ([board length index cells]
   (loop [board board
          length length
          index index
          cells cells]
     (if (= (count cells) length)
       cells
       (recur board length (+ index length) (cons (get board index) cells))))))

(defn vertically-filled?
  ([board marker]
   (vertically-filled? board marker 0))
  ([board marker index]
   (loop [board board
          marker marker
          length (row-num board)]
     (when (< index length)
       (if (filled-with-marker? (get-column-cells board length) marker)
         true
         (recur board marker (inc index)))))))

(defn get-forward-diagonal-cells
  ([board length]
   (get-forward-diagonal-cells board length (- length 1) []))
  ([board length index cells]
   (loop [board board
          length length
          index index
          cells cells]
     (if (= (count cells) length)
       cells
       (recur board length (+ index (- length 1)) (cons (get board index) cells))))))

(defn forward-diagonal-filled?
  [board marker]
  (let [board board
        length (row-num board)]
    (if (filled-with-marker? (get-forward-diagonal-cells board length) marker)
      true
      false)))

(defn get-backward-diagonal-cells
  ([board length]
   (get-backward-diagonal-cells board length 0 []))
  ([board length index cells]
   (loop [board board
          length length
          index index
          cells cells]
     (if (= (count cells) length)
       cells
       (recur board length (+ index length 1) (cons (get board index) cells))))))

(defn backward-diagonal-filled?
  [board marker]
  (let [board board
        length (row-num board)]
    (if (filled-with-marker? (get-backward-diagonal-cells board length) marker)
      true
      false)))
