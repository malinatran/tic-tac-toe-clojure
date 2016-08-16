(ns tic-tac-toe.play
  (:require [tic-tac-toe.game :as game :refer [choose-to-play-again?
                                               run-game-loop
                                               say-goodbye
                                               setup-game]])
  (:gen-class))

(declare start-game)

(defn end-game
  []
  (if (game/choose-to-play-again?)
    (start-game)
    (game/say-goodbye)))

(defn start-game
  []
  (let [[board players player] (game/setup-game)]
    (game/run-game-loop board players player)
    (end-game)))

(defn -main
  []
  (start-game))

