(ns tic-tac-toe.user-interface
  (:require [tic-tac-toe.core :as board]))

(defn prompt
  [message]
  (println message)
  (read-line))

(defn welcome-message
  []
  (prompt "Welcome to tic-tac-toe!"))

(defn valid-size?
  [input]
  (if (> input 2)
    true
    false))

(defn prompt-for-size
  ([]
   (prompt-for-size "Enter a board size:"))
  ([message]
   (let [size (Integer/parseInt (prompt message))]
     (if (valid-size? size)
       size
       (recur message)))))

(defn valid-move?
  [input length]
  (if (and (> input 0) (< input length))
    true
    false))

(defn prompt-for-move
  ([]
   (prompt-for-move "Enter your move:" 9))
  ([message length]
   (let [move (Integer/parseInt (prompt message))
         length length]
     (if (valid-move? move length)
       move
       (recur message length)))))

(defn print-board
  [size]
  (vec (range 1 (+ size 1))))
