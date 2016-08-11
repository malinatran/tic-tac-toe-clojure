(ns tic-tac-toe.game
  (:require [tic-tac-toe.board :as board :refer [create-board]]
            [tic-tac-toe.game-state :as state :refer :all]
            [tic-tac-toe.player :as player :refer [get-move
                                                   make-move
                                                   new-computer-player
                                                   new-human-player]]
            [tic-tac-toe.user-interface :as ui :refer :all])
  (:gen-class))

(declare start)

(defn announce-outcome
  [board players]
  (if (state/win? board players)
    (ui/print-outcome (.marker (state/get-winner board players)))
    (ui/print-outcome)))

(defn display-postgame-options
  []
  (let [option (ui/prompt-for-postgame-options)]
    (if (play-again? option)
      (start)
      (ui/print-goodbye))))

(defn mark-board-with-move
  [board players player]
  (let [move (player/get-move player board players)]
    (player/make-move player board move)))

(defn run-game-loop
  [board players player]
  (if (state/game-over? board players)
    (do (ui/print-board board)
        (announce-outcome board players)
        (display-postgame-options))
    (recur (mark-board-with-move board players player) players (state/switch-player players player))))

(defn setup-players
  [game-type]
  (if (single-player-game? game-type)
    [(player/new-computer-player "X") (player/new-human-player "O")]
    [(player/new-human-player "X") (player/new-human-player "O")]))

(defn setup-player-order
  [size players]
  (let [turn (ui/prompt-for-first-player)
        first-player (state/select-first-player players turn)]
    (ui/print-first-player first-player)
    (run-game-loop (board/create-board size) players first-player)))

(defn start
  []
  (let [game-type (ui/prompt-for-game-type)
        size (ui/prompt-for-size)
        players (setup-players game-type)]
    (if (state/single-player-game? game-type)
      (setup-player-order size players)
      (run-game-loop (board/create-board size) players (first players)))))

(defn -main
  []
  (ui/print-welcome)
  (start))
