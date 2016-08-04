(ns tic-tac-toe.player
  (:require [tic-tac-toe.board :refer [mark-cell]]
            [tic-tac-toe.computer-player :refer [get-minimax-move]]
            [tic-tac-toe.user-interface :refer [get-human-move]]))

(defprotocol Player
  (get-move [this board players])
  (make-move [this board move]))

(deftype ComputerPlayer [marker]
  Player
  (get-move [this board players]
    (get-minimax-move board players marker))
  (make-move [this board move]
    (mark-cell board move marker)))

(defn new-computer-player [marker]
  (ComputerPlayer. marker))

(deftype HumanPlayer [marker]
  Player
  (get-move [this board _]
    (get-human-move board))
  (make-move [this board move]
    (mark-cell board move marker)))

(defn new-human-player [marker]
  (HumanPlayer. marker))
