(ns tic-tac-toe.core)

(defn create-board
  []
  (vec (repeat 9 nil)))

(defn is-cell-empty?
  [board cell]
  (nil? (get board cell)))

(defn is-board-filled?
  [board]
  (= -1 (.indexOf board nil)))

(defn get-empty-cells
  [board]
  (remove nil? (map-indexed (fn [index cell]
                              (if (nil? cell) index nil)) board)))

(defn mark-cell
  [board cell marker]
  (if (is-cell-empty? board cell)
    (assoc board cell marker)))

(defn clear-cell
  [board cell]
  (assoc board cell nil))

(defn is-filled?
  [marker cells]
  (reduce
    (fn [acc cell]
      (and acc (= marker cell))) true cells))
