(ns tic-tac-toe.user-interface
  (:require [tic-tac-toe.board :as board :refer [get-length]]
            [tic-tac-toe.board-formatter :as formatter :refer [add-breaks-and-dividers
                                                               translate-move]]
            [tic-tac-toe.messages :as message :refer :all]
            [tic-tac-toe.validator :as validator :refer [valid-type?
                                                         valid-turn?
                                                         valid-move?
                                                         valid-size?]]))

(defn prompt
  ([message]
   (println message)
   (read-line)))

(defn display-welcome
  []
  (println message/welcome))

(defn prompt-for-game-type
  ([]
   (prompt-for-game-type message/game-type))

  ([message]
   (try
     (let [response (Integer/parseInt (prompt message))]
       (if (valid-type? response)
         response
         (prompt-for-game-type message)))
     (catch Exception e
       (prompt-for-game-type message)))))

(defn prompt-for-first-player
  ([]
   (prompt-for-first-player message/first-player))

  ([message]
   (try
     (let [turn (prompt message)]
       (if (valid-turn? turn)
         turn
         (prompt-for-first-player message)))
     (catch Exception e
       (prompt-for-first-player message)))))

(defn prompt-for-size
  ([]
   (prompt-for-size message/size))

  ([message]
   (try
     (let [size (Integer/parseInt (prompt message))]
       (if (valid-size? size)
         size
         (prompt-for-size message/size-with-guidelines)))
     (catch Exception e
       (prompt-for-size message/size-with-guidelines)))))

(defn print-board
  [board]
  (println (formatter/add-breaks-and-dividers board)))

(defn print-outcome
  ([]
   (println message/draw))
  ([result]
   (println (str result message/win))))

(defn get-human-move
  ([board marker]
   (get-human-move board marker message/move))

  ([board marker message]
   (try
     (print-board board)
     (let [board board
           move (Integer/parseInt (prompt (str marker message)))
           length (board/get-length board)]
       (if (valid-move? board move length)
         (formatter/translate-move move)
         (get-human-move board (str marker message/move-with-guidelines))))
     (catch Exception e
       (get-human-move board (str marker message/move-with-guidelines))))))
