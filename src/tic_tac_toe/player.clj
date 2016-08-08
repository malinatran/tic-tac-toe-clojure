(ns tic-tac-toe.player
  (:require [tic-tac-toe.board :as board :refer [mark-cell]]
            [tic-tac-toe.computer-player :as computer :refer [get-minimax-move]]
            [tic-tac-toe.user-interface :as ui :refer [get-human-move]]))

(defprotocol Player
  (get-move [this board players])
  (make-move [this board move]))

(deftype ComputerPlayer [marker]
  Player
  (get-move [this board players]
    (computer/get-minimax-move board players marker))
  (make-move [this board move]
    (board/mark-cell board move marker)))

(defn new-computer-player [marker]
  (ComputerPlayer. marker))

(deftype HumanPlayer [marker]
  Player
  (get-move [this board players]
    (ui/get-human-move board marker))
  (make-move [this board move]
    (board/mark-cell board move marker)))

(defn new-human-player [marker]
  (HumanPlayer. marker))
