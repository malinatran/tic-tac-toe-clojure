(ns tic-tac-toe.board)

(defn create-board
  [size]
  (vec (repeat (* size size) nil)))

(defn even-numbered-board?
  [length]
  (= (mod length 2) 0))

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
  (when (cell-empty? board cell)
    (assoc board cell marker)))

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
  (reduce (fn [acc cell]
            (and acc (= marker cell))) true cells))

(defn- get-rows
  [board size]
  (vec (partition size board)))

(defn any-row-filled?
  [board marker]
  (let [size (get-size board)
        rows (get-rows board size)]
    (boolean (some #(filled-with-marker? % marker) rows))))

(defn- get-columns
  [board size]
  (let [rows (get-rows board size)]
    (apply map vector rows)))

(defn any-column-filled?
  [board marker]
  (let [size (get-size board)
        columns (get-columns board size)]
    (boolean (some #(filled-with-marker? % marker) columns))))

(defn- get-forward-diagonal-indexes
  [board]
  (let [length (get-length board)
        cell-indexes (vec (range length))
        forward-inc (- (get-size board) 1)]
    (filter #(and (not (= % 0))
                  (not (= % (- length 1)))
                  (= (mod % forward-inc) 0)) cell-indexes)))

(defn- get-backward-diagonal-indexes
  [board]
  (let [cell-indexes (vec (range (get-length board)))
        backward-inc (+ (get-size board) 1)]
    (filter #(= (mod % backward-inc) 0) cell-indexes)))

(defn- get-diagonals
  [board size]
  (let [forward-diagonal (get-forward-diagonal-indexes board)
        backward-diagonal (get-backward-diagonal-indexes board)
        diagonal-indexes (vector forward-diagonal backward-diagonal)]
    (map (fn [diagonal]
           (map (fn [index]
                  (nth board index)) diagonal)) diagonal-indexes)))

(defn any-diagonal-filled?
  [board marker]
  (let [size (get-size board)
        diagonals (get-diagonals board size)]
    (boolean (some #(filled-with-marker? % marker) diagonals))))
