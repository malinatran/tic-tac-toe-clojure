(ns tic-tac-toe.user-interface
  (:require [tic-tac-toe.board-formatter :refer [add-breaks-and-dividers]]
            [tic-tac-toe.messages :refer [draw-message
                                          size-message
                                          size-message-with-guidelines
                                          welcome-message
                                          win-message]]
            [tic-tac-toe.validator :refer [valid-size?]]))

(defn prompt
  [message]
  (println message)
  (read-line))

(defn display-welcome
  []
  (println welcome-message))

(defn prompt-for-size
  ([]
   (prompt-for-size size-message))

  ([message]
   (try
    (let [size (Integer/parseInt (prompt message))]
      (if (valid-size? size)
        size
        (prompt-for-size size-message-with-guidelines)))
    (catch Exception e
      (prompt-for-size size-message-with-guidelines)))))

(defn print-board
  [board]
  (println (add-breaks-and-dividers board)))

(defn print-outcome
  ([]
   (println draw-message))
  ([result]
   (println (str result win-message))))
