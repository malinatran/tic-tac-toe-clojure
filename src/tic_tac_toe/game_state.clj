(ns tic-tac-toe.game-state
  (:require [tic-tac-toe.board :as board :refer [any-column-filled?
                                                 any-diagonal-filled?
                                                 any-row-filled?
                                                 board-filled?]]))

(defn single-player-game?
  [game]
  (= game 1))

(defn play-again?
  [option]
  (= option 1))

(defn select-first-player
  [players turn]
  (condp = turn
    1 (second players)
    2 (first players)
    3 (rand-nth players)))

(defn switch-player
  ([players]
   (conj (vec (rest players)) (first players)))

  ([players player]
   (if (= player (first players))
     (second players)
     (first players))))

(defn winner?
  [board marker]
  (or (board/any-row-filled? board marker)
      (board/any-column-filled? board marker)
      (board/any-diagonal-filled? board marker)))

(defn get-winner
  [board markers]
  (first (filter #(winner? board %) markers)))

(defn win?
  [board markers]
  (boolean (get-winner board markers)))

(defn tie?
  [board markers]
  (and (board/board-filled? board) (not (win? board markers))))

(defn game-over?
  [board markers]
  (or (win? board markers) (tie? board markers)))
