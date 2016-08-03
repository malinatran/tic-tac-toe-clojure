(ns tic-tac-toe.player
  (:require [tic-tac-toe.board :refer [mark-cell]]
            [tic-tac-toe.board-formatter :refer [translate-move]]
            [tic-tac-toe.computer-player :refer [get-computer-move]]
            [tic-tac-toe.human-player :refer [get-human-move]]))

(defprotocol Player
  (get-move [this board players])
  (make-move [this board move]))

(deftype ComputerPlayer [marker]
  Player
  (get-move [this board players]
    (get-computer-move board players marker))
  (make-move [this board move]
    (mark-cell board move marker)))

(defn new-computer-player [marker]
  (ComputerPlayer. marker))

(deftype HumanPlayer [marker]
  Player
  (get-move [this board _]
    (get-human-move board))
  (make-move [this board move]
    (let [cell (translate-move move)]
      (mark-cell board cell marker))))

(defn new-human-player [marker]
  (HumanPlayer. marker))
