(ns tic-tac-toe.user-interface
  (:require [tic-tac-toe.board :as board :refer [get-length]]
            [tic-tac-toe.board-formatter :as formatter :refer [add-breaks-and-dividers
                                                               translate-move]]
            [tic-tac-toe.messages :as message :refer :all]
            [tic-tac-toe.validator :as validator :refer [valid-selection?
                                                         valid-turn?
                                                         valid-move?
                                                         valid-size?]]))

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

(defn prompt-for-game-type
  ([]
   (prompt-for-game-type message/game-type-menu))

  ([message]
   (try
     (let [response (Integer/parseInt (prompt message))]
       (if (valid-selection? response)
         response
         (prompt-for-game-type message/selection-guidelines)))
     (catch Exception e
       (prompt-for-game-type message/selection-guidelines)))))

(defn prompt-for-first-player
  ([]
   (prompt-for-first-player message/first-player-menu))

  ([message]
   (try
     (let [turn (clojure.string/upper-case (prompt message))]
       (if (valid-turn? turn)
         turn
         (prompt-for-first-player message/first-player-guidelines)))
     (catch Exception e
       (prompt-for-first-player message/first-player-guidelines)))))

(defn prompt-for-size
  ([]
   (prompt-for-size message/size))

  ([message]
   (try
     (let [size (Integer/parseInt (prompt message))]
       (if (valid-size? size)
         size
         (prompt-for-size message/size-guidelines)))
     (catch Exception e
       (prompt-for-size message/size-guidelines)))))

(defn prompt-for-postgame-options
  ([]
   (prompt-for-postgame-options message/postgame-menu))

  ([message]
   (try
     (let [response (Integer/parseInt (prompt message))]
       (if (valid-selection? response)
         response
         (prompt-for-postgame-options message/selection-guidelines)))
     (catch Exception e
       (prompt-for-postgame-options message/selection-guidelines)))))

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
         (get-human-move board (str marker message/move-guidelines))))
     (catch Exception e
       (get-human-move board (str marker message/move-guidelines))))))
