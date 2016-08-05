(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.board :refer [get-empty-cells
                                       get-size
                                       mark-cell]]
            [tic-tac-toe.game-state :refer [get-winner
                                            switch-player
                                            tie?
                                            win?]]))

(declare score-moves)

(def computer-marker (atom nil))

(defn update-computer-marker
  [marker]
  (reset! computer-marker marker))

(defn is-computer?
  [player]
  (= (.marker player) @computer-marker))

(defn- calculate-win
  [board players depth]
  (let [winner (get-winner board players)]
    (if (is-computer? winner)
      (- 10 depth)
      (- depth 10))))

(defn- best-move-and-score
  [player moves]
  (if (is-computer? player)
    (apply max-key val moves)
    (apply min-key val moves)))

(defn- best-score
  [player moves]
  (val (best-move-and-score player moves)))

(defn best-move
  [player moves]
  (key (best-move-and-score player moves)))

(defn- get-score
  [board players depth]
  (cond (win? board players) (calculate-win board players depth)
        (tie? board players) 0
        :else (best-score (second players) (score-moves board (switch-player players) (inc depth)))))

(def memoize-scoring (memoize get-score))

(defn- score-moves
  [board players depth]
  (let [moves (get-empty-cells board)
        player (first players)
        scores (map #(memoize-scoring (mark-cell board % (.marker player)) players depth) moves)]
    (zipmap moves scores)))

(defn get-minimax-move
  [board players marker]
  (let [depth 0
        marker (update-computer-marker marker)
        player (first players)
        scored-moves (score-moves board players depth)]
    (best-move player scored-moves)))
