(ns tic-tac-toe.user-interface
  (:require [tic-tac-toe.board :as board]))

(defn prompt
  [message]
  (println message)
  (read-line))

(defn welcome-message
  []
  (println "Welcome to tic-tac-toe!"))

(defn valid-size?
  [input]
  (> input 2))

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
  (and (> input 0) (< input length)))

(defn prompt-for-move
  ([board]
   (let [length (board/get-length board)]
     (prompt-for-move length "Enter your move:")))

  ([length message]
   (let [move (Integer/parseInt (prompt message))
         length length]
     (if (valid-move? move length)
       move
       (recur length message)))))

(defn print-board
  [size]
  (println (vec (range 1 (+ size 1)))))

(defn print-outcome
  [result]
  (println (str result " wins!")))
