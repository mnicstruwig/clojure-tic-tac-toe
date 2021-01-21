(ns tictactoe.core
  (:gen-class))

(defn print-ttt-line
  "Print a row of the tic tac toe board."
  [row] (println (nth row 0) "|" (nth row 1) "|" (nth row 2)))

(defn print-ttt-row-separator
  "Print a row separator of the tic tac toe board."
  [] (println "--+---+--"))

;; TODO: Figure out how to nicely iterate through `game-state` row-by-row
(defn print-ttt-board
  "Print the tic tac toe board represented by the game's state"
  [game-state] (do
          (print-ttt-line (take 3 game-state))
          (print-ttt-row-separator)
          (print-ttt-line (take 3 (drop 3 game-state)))
          (print-ttt-row-separator)
          (print-ttt-line (take 3 (drop 6 game-state)))))

(defn update-game-state
  "Update the game state.
  Update an index, `player-index`, of the game state by replacing it with the
  player's symbol, `player`. Then return the updated game state.
  "
  [player-index player game-state] (assoc game-state (dec player-index) player))

(defn is-index-available?
  "Return true if an index `player-index` in `game-state` has not been taken."
  [player-index game-state]
  (not (some
   #(= (nth game-state player-index) %) ["X" "O"])))

(defn get-valid-player-input
  "Get a valid input from the player."
  [game-state] (try
       (let [user-input (Integer/parseInt (read-line))]
         (if (and (pos? user-input) (<= user-input 9))  ; check for valid range and non-nil.
           (if (is-index-available? (dec user-input) game-state) ; check if the index has already been taken. (Remember to `dec` since user-input is 1-indexed)
             user-input
             (do
               (println "Location on the board already taken!")
               (get-valid-player-input game-state)))
           (do
             (println "Invalid index!")
             (get-valid-player-input game-state))))
       (catch NumberFormatException e  ; When input is not an integer
         (println "Input must be an integer!")
         (get-valid-player-input game-state))))

;; TODO: There is probably a way to do this with recursion.
(defn update-game-state-with-player-input
  "Update the game state using an index provided by the player."
  [game-state current-player] (do
       (println @current-player "'s move")
       (println "Which index?")
       (update-game-state (get-valid-player-input game-state) @current-player game-state)))

;; TODO: Try use a cleaner way of calculating the winner than hard-coding
(defn is-winner?
  "Determine if `player` wins the game in `game-state`."
  [game-state player]
  (or (= (nth game-state 0) (nth game-state 1) (nth game-state 2) player)
      (= (nth game-state 3) (nth game-state 4) (nth game-state 5) player)
      (= (nth game-state 6) (nth game-state 7) (nth game-state 8) player)
      (= (nth game-state 0) (nth game-state 3) (nth game-state 6) player)
      (= (nth game-state 1) (nth game-state 4) (nth game-state 7) player)
      (= (nth game-state 2) (nth game-state 5) (nth game-state 8) player)
      (= (nth game-state 0) (nth game-state 4) (nth game-state 8) player)
      (= (nth game-state 2) (nth game-state 4) (nth game-state 6) player))) ; diag

(defn run-game
  "Run the game."
  [] (let [game-state (atom [1 2 3 4 5 6 7 8 9]) current-player (atom "X")]
       (println)
       (print-ttt-board @game-state)
       (println "")
       (loop []
         (swap! game-state update-game-state-with-player-input current-player) ; Update the game state atom
         (print-ttt-board @game-state)
         (println "")
         (if (is-winner? @game-state @current-player) (println @current-player "is the winner!") (do
                                                                                                   (swap! current-player #(if (= % "X") "O" "X"))
                                                                                                   (recur))))))

(defn -main
  "Run!"
  [& args]
  (run-game))
