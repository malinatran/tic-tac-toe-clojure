(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.board :refer :all]
            [tic-tac-toe.game-state :refer [game-over? get-winner switch-player]]))

(declare select-minimax-move)

(defn- first-move?
  [board]
  (= ((frequencies board) "X") nil))

(defn is-computer?
  [player]
  (= (.marker player) "X"))

(defn- select-first-or-center-cell
  [board]
  (let [length (count board)
        first-cell 0
        center-cell (quot length 2)]
    (if (even-numbered-board? length)
      first-cell
      center-cell)))

(defn- score-move
  [board player opponent depth]
  (let [players [player opponent]
        winner (get-winner board players)]
    (cond (= winner "X") (- 10 depth)
          (= winner opponent) (- depth 10)
          (and (nil? winner) (board-filled? board)) 0
          :else (select-minimax-move board (switch-player players) (inc depth)))))

(defn best-move
  [player scores]
  (if (is-computer? player)
    (key (apply max-key val scores))
    (key (apply min-key val scores))))

(defn- select-minimax-move
  [board players depth]
  (let [player (first players)
        opponent (second players)
        moves (get-empty-cells board)
        scores (map #(score-move (mark-cell board % player) player opponent depth) moves)
        moves-with-scores (zipmap moves scores)]
    (best-move player moves-with-scores)))

(defn get-computer-move
  [board players]
  (let [depth 0]
    (if (first-move? board)
      (select-first-or-center-cell board)
      (select-minimax-move board players depth))))
