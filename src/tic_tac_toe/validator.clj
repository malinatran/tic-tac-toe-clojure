(ns tic-tac-toe.validator)

(defn valid-move?
  [input length]
  (and (> input 0) (< input length)))

(defn valid-size?
  [input]
  (> input 2))

