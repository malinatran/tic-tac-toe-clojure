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
                                                prompt-for-size]])
  (:gen-class))

(defn announce-outcome
  [board players]
  (if (win? board players)
    (print-outcome (.marker (get-winner board players)))
    (print-outcome)))

(defn mark-board-with-move
  [board players]
  (let [player (first players)
        move (get-move player board players)]
    (make-move player board move)))

(defn run-game-loop
  [board players]
  (loop [board board
         players players]
    (if (game-over? board players)
      (do (print-board board)
          (announce-outcome board players))
      (recur (mark-board-with-move board players) (switch-player players)))))

(defn -main
  []
  (display-welcome)
  (let [size (prompt-for-size)
        players [(new-computer-player "X") (new-human-player "O")]]
    (run-game-loop (create-board size) players)))
