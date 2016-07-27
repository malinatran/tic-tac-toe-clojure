(ns tic-tac-toe.human-player
  (:require [tic-tac-toe.board :refer [get-length]]
            [tic-tac-toe.validator :refer [valid-move?]]
            [tic-tac-toe.user-interface :refer [prompt]]))

(def marker "O")

(defn make-move
  ([board]
   (let [length (get-length board)]
     (make-move board length "Enter your move:")))

  ([board length message]
   (let [board board
         move (Integer/parseInt (prompt message))
         length length]
     (if (valid-move? board move length)
       move
       (recur board length message)))))

