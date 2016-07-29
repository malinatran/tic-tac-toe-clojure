(ns tic-tac-toe.human-player
  (:require [tic-tac-toe.board :refer [get-length]]
            [tic-tac-toe.validator :refer [valid-move?]]
            [tic-tac-toe.user-interface :refer [prompt]]))

(defn make-human-move
  ([board]
   (let [length (get-length board)]
     (make-human-move board length "Enter your move:")))

  ([board length message]
   (try
     (let [board board
           move (Integer/parseInt (prompt message))
           length length]
       (if (valid-move? board move length)
         move
         (make-human-move board)))
     (catch Exception e
       (make-human-move board)))))

