(ns tic-tac-toe.board)

(defn create-board
  [size]
  (vec (repeat size nil)))

(defn cell-empty?
  [board cell]
  (nil? (get board cell)))

(defn board-filled?
  [board]
  (= -1 (.indexOf board nil)))

(defn board-empty?
  [board]
  (= (count board) (get (frequencies board) nil)))

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

(defn get-length
  [board]
  (count board))

(defn get-size
  [board]
  (int (java.lang.Math/sqrt (get-length board))))

(defn filled-with-marker?
  [cells marker]
  (reduce
    (fn [acc cell]
      (and acc (= marker cell))) true cells))

(defn get-rows
  [board size]
  (vec (partition size board)))

(defn any-row-filled?
  [board marker]
  (let [size (get-size board)
        rows (get-rows board size)]
    (boolean (some #(filled-with-marker? % marker) rows))))

(defn get-columns
  [board size]
  (let [rows (get-rows board size)]
    (apply map vector rows)))

(defn any-column-filled?
  [board marker]
  (let [size (get-size board)
        columns (get-columns board size)]
    (boolean (some #(filled-with-marker? % marker) columns))))

(defn get-diagonals
  [board size]
  (let [length (get-length board)
        backward-inc (+ size 1)
        forward-inc (- size 1)
        cell-indexes (vec (range length))
        forward-diagonal (filter (fn [index]
                                   (and
                                     (not (= index 0))
                                     (not (= index (- length 1)))
                                     (= (mod index forward-inc) 0))) cell-indexes)
        backward-diagonal (filter (fn [index]
                                    (= (mod index backward-inc) 0)) cell-indexes)
        diagonal-indexes (vector forward-diagonal backward-diagonal)]

    (map (fn [diagonal]
           (map (fn [index]
                       (nth board index)) diagonal))
           diagonal-indexes)))

(defn any-diagonal-filled?
  [board marker]
  (let [size (get-size board)
        diagonals (get-diagonals board size)]
    (boolean (some #(filled-with-marker? % marker) diagonals))))
