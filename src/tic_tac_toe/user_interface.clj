(ns tic-tac-toe.user-interface
  (:require [tic-tac-toe.board-formatter :as formatter :refer [add-breaks-and-dividers]]
            [tic-tac-toe.messages :as message :refer :all]))

(defn prompt
  ([message]
   (println message)
   (read-line)))

(defn print-welcome
  []
  (println message/welcome))

(defn print-goodbye
  []
  (println message/goodbye))

(defn print-board
  [board]
  (println (formatter/add-breaks-and-dividers board)))

(defn print-outcome
  ([]
   (println message/draw))
  ([result]
   (println (str result message/win))))

(defn print-first-player
  [player]
  (println (str (.marker player) message/first-player)))
