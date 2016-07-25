(ns tic-tac-toe.user-interface
  (:require [tic-tac-toe.validator :refer [valid-size?]]))

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
   (let [size (Integer/parseInt (prompt message))]
     (if (valid-size? size)
       size
       (recur message)))))

(defn translate-move
  [move]
  (- move 1))

(defn print-board
  [size]
  (println (vec (range 1 (+ size 1)))))

(defn print-outcome
  [result]
  (println (str result " wins!")))
