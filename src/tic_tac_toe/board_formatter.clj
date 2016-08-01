(ns tic-tac-toe.board-formatter)

(defn- padding
  [cell]
  (let [digit (count (str cell))]
  (cond (= digit 1) (str " " cell " ")
        (= digit 2) (str " " cell)
        :else (str cell))))

(defn translate-move
  [move]
  (- move 1))

(defn replace-nils-with-indexes
  [board]
  (map-indexed (fn [index cell]
                 (if (nil? cell)
                   (+ index 1)
                   cell)) board))
(defn add-cell-padding
  [board]
  (map #(padding %) board))

(defn add-breaks-and-dividers
  [board]
  (let [size (int (java.lang.Math/sqrt (count board)))
        indexed-board (replace-nils-with-indexes board)
        formatted-board (add-cell-padding indexed-board)]
    (clojure.string/join " | " (cons "\n" (concat (apply concat (interpose ["\n"] (partition size formatted-board))) "\n")))))
