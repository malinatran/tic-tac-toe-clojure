(ns tic-tac-toe.human-player
  (:require [tic-tac-toe.board :refer [get-length]]
            [tic-tac-toe.messages :refer :all]
            [tic-tac-toe.validator :refer [valid-move?]]
            [tic-tac-toe.user-interface :refer [prompt]]))

(defn make-human-move
  ([board]
   (make-human-move board move-message))

  ([board message]
   (try
     (let [board board
           move (Integer/parseInt (prompt message))
           length (get-length board)]
       (if (valid-move? board move length)
         move
         (make-human-move board move-message-with-guidelines)))
     (catch Exception e
       (make-human-move board move-message-with-guidelines)))))

