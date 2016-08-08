(ns tic-tac-toe.game
  (:require [tic-tac-toe.board :refer [create-board]]
            [tic-tac-toe.game-state :refer [game-over?
                                            get-winner
                                            switch-player
                                            win?]]
            [tic-tac-toe.player :refer [new-computer-player
                                        new-human-player
                                        get-move
                                        make-move]]
            [tic-tac-toe.user-interface :refer [display-welcome
                                                print-board
                                                print-outcome
                                                prompt-for-first-player
                                                prompt-for-size]])
  (:gen-class))

(defn announce-outcome
  [board players]
  (if (win? board players)
    (print-outcome (.marker (get-winner board players)))
    (print-outcome)))

(defn mark-board-with-move
  [board players player]
  (let [move (get-move player board players)]
    (make-move player board move)))

(defn run-game-loop
  [board players player]
  (loop [board board
         players players
         player player]
    (if (game-over? board players)
      (do (print-board board)
          (announce-outcome board players))
      (recur (mark-board-with-move board players player) players (switch-player players player)))))

(defn first-player
  [players turn]
  (if (= turn "Y")
    (second players)
    (first players)))

(defn -main
  []
  (display-welcome)
  (let [turn (prompt-for-first-player)
        size (prompt-for-size)
        players [(new-computer-player "X") (new-human-player "O")]
        first-player (first-player players turn)]
    (run-game-loop (create-board size) players first-player)))
