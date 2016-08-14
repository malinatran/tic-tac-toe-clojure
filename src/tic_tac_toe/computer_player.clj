(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.board :as board :refer :all]
            [tic-tac-toe.game-state :as state :refer :all]))

(declare score-moves)

(def opponent "O")
(def depth 0)
(def score 10)

(defn- computer?
  [player marker]
  (= player marker))

(defn- calculate-win
  [board players marker depth]
  (let [winner (state/get-winner board players true)]
    (if (computer? winner marker)
      (- score depth)
      (- depth score))))

(defn- best-move-and-score
  [player moves marker]
  (if (computer? player marker)
    (apply max-key val moves)
    (apply min-key val moves)))

(defn- best-score
  [player moves marker]
  (val (best-move-and-score player moves marker)))

(defn best-move
  [player moves marker]
  (key (best-move-and-score player moves marker)))

(defn- get-score
  [board players marker depth]
  (cond (state/win? board players true) (calculate-win board players marker depth)
        (state/tie? board players true) 0
        :else (best-score (second players) (score-moves board (state/switch-player players) marker (inc depth)) marker)))

(def memoize-scoring (memoize get-score))

(defn- score-moves
  [board players marker depth]
  (let [moves (board/get-empty-cells board)
        player (first players)
        scores (map #(memoize-scoring (board/mark-cell board % player) players marker depth) moves)]
    (zipmap moves scores)))

(defn get-minimax-move
  [board marker]
  (let [players (vector marker opponent)
        player (first players)
        scored-moves (score-moves board players marker depth)]
    (best-move player scored-moves marker)))
