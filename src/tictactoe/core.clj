(ns tictactoe.core
  (:gen-class))


(defn print-ttt-line
  "Print a line of the tic tac toe board."
  [sym] (println (nth sym 0) "|" (nth sym 1) "|" (nth sym 2)))
(defn print-ttt-row-separator
  [] (println "--+---+--"))


;; TODO: Figure out how to nicely iterate through `sym`
(defn print-ttt-board
  [game-state] (do
          (print-ttt-line (take 3 @game-state))
          (print-ttt-row-separator)
          (print-ttt-line (take 3 (drop 3 @game-state)))
          (print-ttt-row-separator)
          (print-ttt-line (take 3 (drop 6 @game-state)))))

(defn update-game-state
  [player-index player game-state] (assoc game-state (dec player-index) player))

(defn update-game-state-with-player-input
  [game-state current-player] (do
       (println @current-player "'s move")
       (println "Which index?")
       (update-game-state (Integer/parseInt (read-line)) @current-player game-state)))

(defn run-game
  [] (let [game-state (atom [1 2 3 4 5 6 7 8 9]) current-player (atom "X")]
       (println)
       (print-ttt-board game-state)
       (dotimes [i 3]
        (swap! game-state update-game-state-with-player-input current-player) ; Update the game state atom
;;        (is-winner? @game-state @current-player)
        (swap! current-player #(if (= % "X") "O" "X"))
        (print-ttt-board game-state))))

;; TODO: Try use a cleaner way of calculating the winner than hard-coding
(defn is-winner?
  [game-state player]
  (if (= (nth game-state 0) (nth game-state 1) (nth game-state 2) player) true) ; row
  (if (= (nth game-state 3) (nth game-state 4) (nth game-state 5) player) true) ; row
  (if (= (nth game-state 6) (nth game-state 7) (nth game-state 8) player) true) ; row
  (if (= (nth game-state 0) (nth game-state 3) (nth game-state 6) player) true) ; col
  (if (= (nth game-state 1) (nth game-state 4) (nth game-state 7) player) true) ; col
  (if (= (nth game-state 2) (nth game-state 5) (nth game-state 8) player) true) ; col
  (if (= (nth game-state 0) (nth game-state 4) (nth game-state 8) player) true) ; diag
  (if (= (nth game-state 2) (nth game-state 4) (nth game-state 6) player) true)) ; diag

;; Test
(let [game-state (atom ["X" "X" "X"])]
  (is-winner? game-state "X"))

(run-game)


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (run-game))
