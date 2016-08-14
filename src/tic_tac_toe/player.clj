(ns tic-tac-toe.player
  (:require [tic-tac-toe.board :as board :refer [mark-cell]]
            [tic-tac-toe.computer-player :as computer :refer [get-minimax-move]]
            [tic-tac-toe.game-setup :as setup :refer [get-human-move]]))

(defprotocol Player
  (get-move [this board])
  (make-move [this board move]))

(deftype ComputerPlayer [marker]
  Player
  (get-move [this board]
    (computer/get-minimax-move board marker))
  (make-move [this board move]
    (board/mark-cell board move marker)))

(defn new-computer-player [marker]
  (ComputerPlayer. marker))

(deftype HumanPlayer [marker]
  Player
  (get-move [this board]
    (setup/get-human-move board marker))
  (make-move [this board move]
    (board/mark-cell board move marker)))

(defn new-human-player [marker]
  (HumanPlayer. marker))
