(ns tic-tac-toe.user-interface
  (:require [tic-tac-toe.helper :refer [translate-move replace-nils-with-indexes add-cell-padding]]
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

(defn format-board
  [board]
  (let [size (int (java.lang.Math/sqrt (count board)))
        indexed-board (replace-nils-with-indexes board)
        formatted-board (add-cell-padding indexed-board)]
    (clojure.string/join " | " (cons "\n" (concat (apply concat (interpose ["\n"] (partition size formatted-board))) "\n")))))

(defn print-board
  [board]
  (println (format-board board)))

(defn print-outcome
  ([]
   (println "Nobody wins in the game of life - er, I mean, tic-tac-toe."))
  ([result]
   (println (str result " wins!"))))
