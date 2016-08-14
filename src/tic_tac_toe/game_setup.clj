(ns tic-tac-toe.game-setup
  (:require [tic-tac-toe.board-formatter :as formatter :refer [translate-move]]
            [tic-tac-toe.messages :as message :refer :all]
            [tic-tac-toe.user-interface :as ui :refer [print-board
                                                       prompt]]
            [tic-tac-toe.validator :as validator :refer [valid-selection?
                                                         valid-turn?
                                                         valid-move?
                                                         valid-size?]]))
(declare get-human-move)

(defn- get-valid-response
  [first-message second-message validation function]
  (let [response (Integer/parseInt (ui/prompt first-message))]
    (if (validation response)
      response
      (function second-message))))

(defn run-loop
  [first-message second-message validation function]
  (try
    (get-valid-response first-message second-message validation function)
    (catch Exception e
      (function second-message))))

(defn prompt-for-game-type
  ([] (prompt-for-game-type message/game-type-menu))
  ([message] (run-loop message message/selection-guidelines valid-selection? prompt-for-game-type)))

(defn prompt-for-first-player
  ([] (prompt-for-first-player message/first-player-menu))
  ([message] (run-loop message message/first-player-guidelines valid-turn? prompt-for-first-player)))

(defn prompt-for-size
  ([] (prompt-for-size message/size))
  ([message] (run-loop message message/size-guidelines valid-size? prompt-for-size)))

(defn prompt-for-postgame-option
  ([] (prompt-for-postgame-option message/postgame-menu))
  ([message] (run-loop message message/selection-guidelines valid-selection? prompt-for-postgame-option)))

(defn- get-valid-move
  [board marker message]
  (let [move (Integer/parseInt (ui/prompt (str marker message)))
        length (count board)]
    (if (valid-move? board move length)
      (formatter/translate-move move)
      (get-human-move board marker message/move-guidelines))))

(defn- run-move-loop
  [board marker message]
  (try
    (ui/print-board board)
    (get-valid-move board marker message)
    (catch Exception e
      (get-human-move board marker message/move-guidelines))))

(defn get-human-move
  ([board marker] (get-human-move board marker message/move))
  ([board marker message] (run-move-loop board marker message)))
