(ns tic-tac-toe.helper)

(defn translate-move
  [move]
  (- move 1))

(defn replace-nils-with-indexes
  [board]
  (map-indexed (fn [index cell]
                 (if (nil? cell) (+ index 1) cell)) board))

