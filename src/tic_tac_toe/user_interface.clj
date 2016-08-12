(ns tic-tac-toe.user-interface
  (:require [tic-tac-toe.board :as board :refer [get-length]]
            [tic-tac-toe.board-formatter :as formatter :refer [add-breaks-and-dividers
                                                               translate-move]]
            [tic-tac-toe.messages :as message :refer :all]
            [tic-tac-toe.validator :as validator :refer [valid-selection?
                                                         valid-turn?
                                                         valid-move?
                                                         valid-size?]]))
(declare prompt-for-game-type
         prompt-for-postgame-option
         prompt-for-first-player
         prompt-for-size
         get-human-move)

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

; Game type
(defn- get-valid-game-type
  [message]
  (let [response (Integer/parseInt (prompt message))]
    (if (valid-selection? response)
      response
      (prompt-for-game-type message/selection-guidelines))))

(defn- run-game-type-loop
  [message]
  (try
    (get-valid-game-type message)
    (catch Exception e
      (prompt-for-game-type message/selection-guidelines))))

(defn prompt-for-game-type
  ([] (prompt-for-game-type message/game-type-menu))
  ([message] (run-game-type-loop message)))

; First player
(defn- get-valid-first-player
  [message]
  (let [response (clojure.string/upper-case (prompt message))]
    (if (valid-turn? response)
      response
      (prompt-for-first-player message/first-player-guidelines))))

(defn- run-first-player-loop
  [message]
  (try
    (get-valid-first-player message)
    (catch Exception e
      (prompt-for-first-player message/first-player-guidelines))))

(defn prompt-for-first-player
  ([] (prompt-for-first-player message/first-player-menu))
  ([message] (run-first-player-loop message)))

; Size
(defn- get-valid-size
  [message]
  (let [response (Integer/parseInt (prompt message))]
    (if (valid-size? response)
      response
      (prompt-for-size message/size-guidelines))))

(defn- run-size-loop
  [message]
  (try
    (get-valid-size message)
    (catch Exception e
      (prompt-for-size message/size-guidelines))))

(defn prompt-for-size
  ([] (prompt-for-size message/size))
  ([message] (run-size-loop message)))

; Postgame option
(defn- get-valid-postgame-option
  [message]
  (let [response (Integer/parseInt (prompt message))]
    (if (valid-selection? response)
      response
      (prompt-for-postgame-option message/selection-guidelines))))

(defn- run-postgame-loop
  [message]
  (try
    (get-valid-postgame-option message)
    (catch Exception e
      (prompt-for-postgame-option message/selection-guidelines))))

(defn prompt-for-postgame-option
  ([] (prompt-for-postgame-option message/postgame-menu))
  ([message] (run-postgame-loop message)))

; Move
(defn- get-valid-move
  [board marker message]
  (let [board board
        move (Integer/parseInt (prompt (str marker message)))
        length (board/get-length board)]
    (if (valid-move? board move length)
      (formatter/translate-move move)
      (get-human-move board marker message/move-guidelines))))

(defn- run-move-loop
  [board marker message]
  (try
     (print-board board)
     (get-valid-move board marker message)
     (catch Exception e
       (get-human-move board marker message/move-guidelines))))

(defn get-human-move
  ([board marker] (get-human-move board marker message/move))
  ([board marker message] (run-move-loop board marker message)))
