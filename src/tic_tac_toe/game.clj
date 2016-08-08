(ns tic-tac-toe.game
  (:require [tic-tac-toe.board :as board :refer [create-board]]
            [tic-tac-toe.game-state :as state :refer [game-over?
                                                      get-winner
                                                      switch-player
                                                      win?]]
            [tic-tac-toe.player :as player :refer [new-computer-player
                                                   new-human-player
                                                   get-move
                                                   make-move]]
            [tic-tac-toe.user-interface :as ui :refer :all])
  (:gen-class))

(defn announce-outcome
  [board players]
  (if (state/win? board players)
    (ui/print-outcome (.marker (state/get-winner board players)))
    (ui/print-outcome)))

(defn mark-board-with-move
  [board players player]
  (let [move (player/get-move player board players)]
    (player/make-move player board move)))

(defn run-game-loop
  [board players player]
  (loop [board board
         players players
         player player]
    (if (state/game-over? board players)
      (do (ui/print-board board)
          (announce-outcome board players))
      (recur (mark-board-with-move board players player) players (state/switch-player players player)))))

(defn select-first-player
  [players turn]
  (if (= turn "Y")
    (second players)
    (first players)))

(defn single-player-game?
  [game-type]
  (= game-type 1))

(defn determine-players
  [game-type]
  (if (single-player-game? game-type)
    [(player/new-computer-player "X") (player/new-human-player "O")]
    [(player/new-human-player "X") (player/new-human-player "O")]))

(defn get-first-player
  [size players]
  (let [turn (ui/prompt-for-first-player)
        first-player (select-first-player players turn)]
    (run-game-loop (board/create-board size) players first-player)))

(defn -main
  []
  (ui/display-welcome)
  (let [game-type (ui/prompt-for-game-type)
        size (ui/prompt-for-size)
        players (determine-players game-type)]
    (if (single-player-game? game-type)
      (get-first-player size players)
      (run-game-loop (board/create-board size) players (first players)))))
