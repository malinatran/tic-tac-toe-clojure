(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.board :refer :all]
            [tic-tac-toe.user-interface :refer [print-board]]
            [tic-tac-toe.game-state :refer [game-over? get-winner switch-player tie? win?]]))

(declare score-moves)

(defn- first-move?
  [board]
  (= ((frequencies board) "X") nil))

(defn is-computer?
  [player]
  (= (.marker player) "X"))

(defn is-opponent?
  [player]
  (= (.marker player) "O"))

(defn- select-first-or-center-cell
  [board]
  (let [length (count board)
        first-cell 0
        center-cell (quot length 2)]
    (if (even-numbered-board? length)
      first-cell
      center-cell)))

(defn calculate-win
  [board players ]
  ;; (println "I am in calculate win")
  (let [winner (get-winner board players)]
    (if (is-computer? winner)
      10
      -10)))

(defn best-move-and-score
  [player moves]
  (if (is-computer? player)
    (apply max-key val moves)
    (apply min-key val moves)))

(defn best-score
  [player moves]
  (val (best-move-and-score player moves)))

(defn best-move
  [player moves]
  (key (best-move-and-score player moves)))

(defn- get-score
  [board players]
  ;; (println "I am in score move")
  (cond (win? board players) (calculate-win board players)
        (tie? board players) 0
        :else (best-score (second players) (score-moves board (switch-player players)))))

(defn score-moves
  [board players]
   (let [moves (get-empty-cells board)
         player (first players)
         scores (map #(get-score (mark-cell board % (.marker player)) players) moves)]
   (zipmap moves scores)))

(defn- select-minimax-move
  [board players ]
  (let [player (first players)
        scored-moves (score-moves board players)]
    (best-move player scored-moves)))

(defn get-computer-move
  [board players]
  (let [depth 0]
    (if (first-move? board)
      (select-first-or-center-cell board)
      (select-minimax-move board players))))
