(ns tic-tac-toe.human-player
  (:require [tic-tac-toe.board :refer [get-length]]
            [tic-tac-toe.validator :refer [valid-move?]]
            [tic-tac-toe.user-interface :refer [prompt]]))

(defn make-human-move
  ([board]
   (make-human-move board "Enter your move:"))

  ([board message]
   (try
     (let [board board
           move (Integer/parseInt (prompt message))
           length (get-length board)]
       (if (valid-move? board move length)
         move
         (make-human-move board "Enter your move (must be a number on the board):")))
     (catch Exception e
       (make-human-move board "Enter your move (must be a number on the board):")))))

