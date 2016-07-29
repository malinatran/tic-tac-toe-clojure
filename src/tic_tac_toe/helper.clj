(ns tic-tac-toe.helper)

(defn translate-move
  [move]
  (- move 1))

(defn padding
  [cell]
  (let [digit (count (str cell))]
  (cond (= digit 1) (str " " cell " ")
        (= digit 2) (str " " cell)
        :else (str cell))))

(defn replace-nils-with-indexes
  [board]
  (map-indexed (fn [index cell]
                 (if (nil? cell)
                   (+ index 1)
                   cell)) board))

(defn add-cell-padding
  [board]
  (map #(padding %) board))
