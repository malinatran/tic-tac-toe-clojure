(ns tic-tac-toe.game
  (:require [tic-tac-toe.board :as board :refer [create-board]]
            [tic-tac-toe.game-state :as state :refer :all]
            [tic-tac-toe.game-setup :as setup :refer :all]
            [tic-tac-toe.player :as player :refer [get-move
                                                   make-move
                                                   new-computer-player
                                                   new-human-player]]
            [tic-tac-toe.user-interface :as ui :refer :all]))

(declare setup-game)

(def x-marker "X")
(def o-marker "O")

(defn say-goodbye
  []
  (ui/print-goodbye))

(defn choose-to-play-again?
  []
  (let [option (setup/get-options {:option :postgame-option})]
    (play-again? option)))

(defn- get-markers
  [players]
  (map #(.marker %) players))

(defn announce-outcome
  [board players]
  (let [markers (get-markers players)]
    (if (state/win? board markers)
      (ui/print-outcome (state/get-winner board markers))
      (ui/print-outcome))))

(defn mark-board-with-move
  [board players player]
  (let [move (player/get-move player board)]
    (player/make-move player board move)))

(defn run-game-loop
  [board players player]
  (let [markers (get-markers players)]
  (if (state/game-over? board markers)
    (do (ui/print-board board)
        (announce-outcome board players))
    (recur (mark-board-with-move board players player) players (state/switch-player players player)))))

(defn setup-players
  [game]
  (if (single-player-game? game)
    [(player/new-computer-player x-marker) (player/new-human-player o-marker)]
    [(player/new-human-player x-marker) (player/new-human-player o-marker)]))

(defn setup-player-order
  [size players]
  (let [turn (setup/get-options {:option :first-player})
        first-player (state/select-first-player players turn)]
    (ui/print-first-player first-player)
    [(board/create-board size) players first-player]))

(defn setup-game
  []
  (ui/print-welcome)
  (let [game (setup/get-options {:option :game-type})
        size (setup/get-options {:option :size})
        players (setup-players game)]
    (if (state/single-player-game? game)
      (setup-player-order size players)
      [(board/create-board size) players (first players)])))
