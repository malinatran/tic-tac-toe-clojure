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

(defn get-valid-input
  [first-message second-message validation function]
  (let [input (Integer/parseInt (ui/prompt first-message))]
    (if (validation input)
      input
      (function second-message))))

(defn- get-valid-move
  [board marker message]
  (ui/print-board board)
  (let [move (Integer/parseInt (ui/prompt (str marker message)))
        length (count board)]
    (if (validator/valid-move? board move length)
      (formatter/translate-move move)
      (get-human-move board marker message/move-guidelines))))

(defn run-loop
  [first-message second-message validation function & [board marker]]
  (try
    (if board
      (validation board marker first-message)
      (get-valid-input first-message second-message validation function))
    (catch Exception e
      (function second-message))))

(defmulti get-options :option)

(defn get-game-type
  [message]
  (run-loop message message/selection-guidelines validator/valid-selection? get-game-type))

(defmethod get-options :game-type
  [_] (get-game-type message/game-type-menu))

(defn get-first-player
  [message]
  (run-loop message message/first-player-guidelines validator/valid-turn? get-first-player))

(defmethod get-options :first-player
  [_] (get-first-player message/first-player-menu))

(defn get-size
  [message]
  (run-loop message message/size-guidelines validator/valid-size? get-size))

(defmethod get-options :size
  [_] (get-size message/size))

(defn get-postgame-option
  [message]
  (run-loop message message/selection-guidelines validator/valid-selection? get-postgame-option))

(defmethod get-options :postgame-option
  [_] (get-postgame-option message/postgame-menu))

(defn get-human-move
  ([board marker]
   (get-human-move board marker message/move))
  ([board marker message]
   (run-loop message message/move-guidelines get-valid-move get-human-move board marker)))
