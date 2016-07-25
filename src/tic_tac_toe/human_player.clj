(ns tic-tac-toe.human-player
  (:require [tic-tac-toe.board :refer [get-length]]
            [tic-tac-toe.validator :refer [valid-move?]]
            [tic-tac-toe.user-interface :refer [prompt]]))

(defn make-move
  ([board]
   (let [length (get-length board)]
     (make-move length "Enter your move:")))

  ([length message]
   (let [move (Integer/parseInt (prompt message))
         length length]
     (if (valid-move? move length)
       move
       (recur length message)))))

