(ns tic-tac-toe.board-formatter)

(defn- add-padding-to-cell
  [cell]
  (let [digit (count (str cell))]
    (condp = digit
      1 (str " " cell " ")
      2 (str " " cell)
      (str cell))))

(defn translate-move
  [move]
  (- move 1))

(defn replace-nils-with-indexes
  [board]
  (map-indexed (fn [index cell]
                 (if (nil? cell)
                   (+ index 1)
                   cell)) board))

(defn add-padding-to-cells
  [board]
  (map #(add-padding-to-cell %) board))

(defn add-breaks-and-dividers
  [board]
  (let [size (int (java.lang.Math/sqrt (count board)))
        indexed-board (replace-nils-with-indexes board)
        formatted-board (add-padding-to-cells indexed-board)]
    (clojure.string/join " | " (cons "\n" (concat (apply concat (interpose ["\n"] (partition size formatted-board))) "\n")))))
