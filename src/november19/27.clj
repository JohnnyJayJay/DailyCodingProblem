; Alice wants to join her school's Probability Student Club.
; Membership dues are computed via one of two simple probabilistic games.
;
; The first game: roll a die repeatedly. Stop rolling once you get a five followed by a six.
; Your number of rolls is the amount you pay, in dollars.
;
; The second game: same, except that the stopping condition is a five followed by a five.
;
; Which of the two games should Alice elect to play? Does it even matter?
; Write a program to simulate the two games and calculate their expected value.
(ns november19.27)

(defn roll-dice [] (inc (rand-int 6)))

(defn roll-dice-until [streak]
  (loop [remaining streak counter 0]
    (cond
      (empty? remaining) counter
      (= (first remaining) (roll-dice)) (recur (rest remaining) (inc counter))
      :else (recur remaining (inc counter)))))

; Simulation: play the game 10000 times for both cases and calculate the average
(println (/ (apply + (repeatedly 10000 #(roll-dice-until [5 6]))) 10000.0))
(println (/ (apply + (repeatedly 10000 #(roll-dice-until [5 5]))) 10000.0))

; Answer: no, it doesn't matter since both streaks have a probability of 1/6 * 1/6 to occur.
; The average amount of rolls will always be around 12.