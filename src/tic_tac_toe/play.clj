(ns tic-tac-toe.play
  (:require [tic-tac-toe.game :as game :refer [choose-to-play-again
                                               run-game-loop
                                               say-goodbye
                                               setup-game]])
  (:gen-class))

(declare start)

(defn end
  []
  (if (choose-to-play-again)
    (start)
    (say-goodbye)))

(defn start
  []
  (let [[board players player] (setup-game)]
    (run-game-loop board players player)
    (end)))

(defn -main
  []
  (start))

