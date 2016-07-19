(ns tic-tac-toe.core
  (:require [clojure.math.numeric-tower :as math]))

(defn create-board
  []
  (vec (repeat 9 nil)))

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

(defn vertically-filled?
  ([board marker]
   (vertically-filled? board marker 0))
  ([board marker index]
   (loop [board board
          marker marker
          length (row-num board)]
     (when (< index length)
       (let [first-cell  (get board index)
             second-cell (get board (+ index length))
             third-cell  (get board (+ index (* length 2)))]
         (if (filled-with-marker? (vec [first-cell second-cell third-cell]) marker)
           true
           (recur board marker (inc index))))))))

(defn forward-diagonal-filled?
  [board marker]
  (let [length (row-num board)
        index (- length 1)
        first-cell  (get board index)
        second-cell (get board (* index 2))
        third-cell  (get board (* index 3))]
    (if (filled-with-marker? (vec [first-cell second-cell third-cell]) marker)
      true
      false)))

(defn backward-diagonal-filled?
  [board marker]
  (let [index 0
        first-cell  (get board index)
        second-cell (get board (+ index 4))
        third-cell  (get board (+ index 8))]
    (if (filled-with-marker? (vec [first-cell second-cell third-cell]) marker)
      true
      false)))
