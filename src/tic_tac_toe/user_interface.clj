(ns tic-tac-toe.user-interface
  (:require [tic-tac-toe.board-formatter :refer [replace-nils-with-indexes add-cell-padding add-breaks-and-dividers]]
            [tic-tac-toe.validator :refer [valid-size?]]))

(defn prompt
  [message]
  (println message)
  (read-line))

(defn welcome-message
  []
  (println "Welcome to tic-tac-toe!"))

(defn prompt-for-size
  ([]
   (prompt-for-size "Enter a board size:"))

  ([message]
   (try
    (let [size (Integer/parseInt (prompt message))]
      (if (valid-size? size)
        size
        (prompt-for-size "Enter a board size (greater than or equal to 3):")))
    (catch Exception e
      (prompt-for-size "Enter a board size (greater than or equal to 3):")))))

(defn print-board
  [board]
  (println (add-breaks-and-dividers board)))

(defn print-outcome
  ([]
   (println "Nobody wins in the game of life - er, I mean, tic-tac-toe."))
  ([result]
   (println (str result " wins!"))))
