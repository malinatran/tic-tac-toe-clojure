(ns tic-tac-toe.human-player
  (:require [tic-tac-toe.board :refer [get-length]]
            [tic-tac-toe.messages :refer :all]
            [tic-tac-toe.validator :refer [valid-move?]]
            [tic-tac-toe.user-interface :refer [print-board prompt]]))

(defn get-human-move
  ([board]
   (get-human-move board move-message))

  ([board message]
   (try
     (print-board board)
     (let [board board
           move (Integer/parseInt (prompt message))
           length (get-length board)]
       (if (valid-move? board move length)
         move
         (get-human-move board move-message-with-guidelines)))
     (catch Exception e
       (get-human-move board move-message-with-guidelines)))))
