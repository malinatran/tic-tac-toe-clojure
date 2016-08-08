(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.board :as board :refer [get-empty-cells
                                                 mark-cell]]
            [tic-tac-toe.game-state :as state :refer [get-winner
                                                      tie?
                                                      win?]]))

(declare score-moves)

(defn is-computer?
  [player marker]
  (= (.marker player) marker))

(defn- switch-player
  [players]
  (conj (vec (rest players)) (first players)))

(defn- calculate-win
  [board players depth marker]
  (let [winner (state/get-winner board players)]
    (if (is-computer? winner marker)
      (- 10 depth)
      (- depth 10))))

(defn- best-move-and-score
  [player moves marker]
  (if (is-computer? player marker)
    (apply max-key val moves)
    (apply min-key val moves)))

(defn- best-score
  [player moves marker]
  (val (best-move-and-score player moves marker)))

(defn best-move
  [player moves marker]
  (key (best-move-and-score player moves marker)))

(defn- get-score
  [board players depth marker]
  (cond (state/win? board players) (calculate-win board players depth marker)
        (state/tie? board players) 0
        :else (best-score (second players) (score-moves board (switch-player players) (inc depth) marker) marker)))

(def memoize-scoring (memoize get-score))

(defn- score-moves
  [board players depth marker]
  (let [moves (board/get-empty-cells board)
        player (first players)
        scores (map #(memoize-scoring (board/mark-cell board % (.marker player)) players depth marker) moves)]
    (zipmap moves scores)))

(defn get-minimax-move
  [board players marker]
  (let [depth 0
        player (first players)
        scored-moves (score-moves board players depth marker)]
    (best-move player scored-moves marker)))
