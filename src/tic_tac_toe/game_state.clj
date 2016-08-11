(ns tic-tac-toe.game-state
  (:require [tic-tac-toe.board :as board :refer [any-column-filled?
                                                 any-diagonal-filled?
                                                 any-row-filled?
                                                 board-filled?]]))

(defn single-player-game?
  [game-type]
  (= game-type 1))

(defn play-again?
  [option]
  (= option 1))

(defn select-first-player
  [players turn]
  (condp = turn
    "Y" (second players)
    "N" (first players)
    "R" (rand-nth players)))

(defn switch-player
  [players player]
  (if (= player (first players))
    (second players)
    (first players)))

(defn winner?
  [board player]
  (let [marker (.marker player)]
    (or (board/any-row-filled? board marker)
        (board/any-column-filled? board marker)
        (board/any-diagonal-filled? board marker))))

(defn get-winner
  [board players]
  (first (filter (fn [player]
                   (winner? board player)) players)))

(defn win?
  [board players]
  (boolean (get-winner board players)))

(defn tie?
  [board players]
  (and (board/board-filled? board) (not (win? board players))))

(defn game-over?
  [board players]
  (or (win? board players) (tie? board players)))
