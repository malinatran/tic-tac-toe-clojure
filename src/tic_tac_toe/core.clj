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

(defn get-size
  [board]
  (math/sqrt (count board)))

(defn any-row-filled?
  ([board marker]
   (let [index 0
         counter 0]
     (any-row-filled? board marker index counter)))

  ([board marker index counter]
   (loop [board board
          marker marker
          size (get-size board)
          counter counter]
     (if (> counter size)
       false
       (or (filled-with-marker? (subvec board index (+ index size)) marker)
           (recur board marker (+ index size) (inc counter)))))))

(defn get-column-cells
  ([board size index]
   (let [cells []]
   (get-column-cells board size index cells)))

  ([board size index cells]
   (loop [board board
          size size
          index index
          cells cells]
     (if (= (count cells) size)
       cells
       (recur board size (+ index size) (cons (get board index) cells))))))

(defn any-column-filled?
  ([board marker]
   (any-column-filled? board marker 0))

  ([board marker index]
   (loop [board board
          marker marker
          size (get-size board)]
     (if (> index size)
       false
       (or (filled-with-marker? (get-column-cells board size index) marker)
           (recur board marker (inc index)))))))

(defn get-forward-diagonal-cells
  ([board size]
   (get-forward-diagonal-cells board size (- size 1) []))

  ([board size index cells]
   (loop [board board
          size size
          index index
          cells cells]
     (if (= (count cells) size)
       cells
       (recur board size (+ index (- size 1)) (cons (get board index) cells))))))

(defn forward-diagonal-filled?
  [board marker]
  (let [board board
        size (get-size board)]
    (filled-with-marker? (get-forward-diagonal-cells board size) marker)))

(defn get-backward-diagonal-cells
  ([board size]
   (get-backward-diagonal-cells board size 0 []))

  ([board size index cells]
   (loop [board board
          size size
          index index
          cells cells]
     (if (= (count cells) size)
       cells
       (recur board size (+ index size 1) (cons (get board index) cells))))))

(defn backward-diagonal-filled?
  [board marker]
  (let [board board
        size (get-size board)]
    (filled-with-marker? (get-backward-diagonal-cells board size) marker)))
