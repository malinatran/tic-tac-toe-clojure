(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.board :refer [board-empty? board-filled? clear-cell get-empty-cells mark-cell]]
            [tic-tac-toe.game-state :refer [game-over? get-winner switch-player]]))

(declare make-minimax-move)

(def computer "X")

(defn is-computer?
  [player]
  (= player "X"))

(defn even-numbered-board?
  [length]
  (= (mod length 2) 0))

(defn- make-first-move
  [board]
  (let [length (count board)
        first-cell 0
        center-cell (quot length 2)]
    (if (even-numbered-board? length)
      first-cell
      center-cell)))

(defn score-move
  [board player opponent depth]
  (let [winner (get-winner board)]
    (cond (= winner computer) (- 10 depth)
          (= winner opponent) (- depth 10)
          (and (nil? winner) (board-filled? board)) 0
          :else (make-minimax-move board (switch-player player) opponent (inc depth)))))

(defn best-move
  [player scores]
  (if (is-computer? player)
    (key (apply max-key val scores))
    (key (apply min-key val scores))))

(defn- make-minimax-move
  [board player opponent depth]
  (let [moves (get-empty-cells board)
        scores (map #(score-move
                       (mark-cell board % player) player opponent depth) moves)
        moves-with-scores (zipmap moves scores)]
      (best-move player moves-with-scores)))

(defn make-computer-move
  [board player opponent]
  (let [depth 0]
    (if (board-empty? board)
      (make-first-move board)
      (make-minimax-move board player opponent depth))))
