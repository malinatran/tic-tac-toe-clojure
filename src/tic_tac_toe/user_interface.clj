(ns tic-tac-toe.user-interface
  (:require [tic-tac-toe.board :refer [get-length]]
            [tic-tac-toe.board-formatter :refer [add-breaks-and-dividers
                                                 translate-move]]
            [tic-tac-toe.messages :refer :all]
            [tic-tac-toe.validator :refer :all]))

(defn prompt
  [message]
  (println message)
  (read-line))

(defn display-welcome
  []
  (println welcome-message))

(defn prompt-for-size
  ([]
   (prompt-for-size size-message))

  ([message]
   (try
    (let [size (Integer/parseInt (prompt message))]
      (if (valid-size? size)
        size
        (prompt-for-size size-message-with-guidelines)))
    (catch Exception e
      (prompt-for-size size-message-with-guidelines)))))

(defn print-board
  [board]
  (println (add-breaks-and-dividers board)))

(defn print-outcome
  ([]
   (println draw-message))
  ([result]
   (println (str result win-message))))

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
         (translate-move move)
         (get-human-move board move-message-with-guidelines)))
     (catch Exception e
       (get-human-move board move-message-with-guidelines)))))
