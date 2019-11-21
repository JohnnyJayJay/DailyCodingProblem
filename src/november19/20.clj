; You are given a list of data entries that represent entries and exits of
; groups of people into a building. An entry looks like this:
;
; {"timestamp": 1526579928, count: 3, "type": "enter"}
;
; This means 3 people entered the building. An exit looks like this:
;
; {"timestamp": 1526580382, count: 2, "type": "exit"}
;
; This means that 2 people exited the building. timestamp is in Unix time.
;
; Find the busiest period in the building, that is, the time with the
; most people in the building. Return it as a pair of (start, end) timestamps.
; You can assume the building always starts off and ends up empty, i.e. with 0 people inside.
(ns november19.20)

(defn busiest-period [entries]
  ((apply max-key :people                              ; find the value where :people is greatest
          (reductions
            #(hash-map                                      ; reduce entries by capturing the time frame of the last two entries before each one
               :frame [(%1 :timestamp) (%2 :timestamp)]
               :people (%1 :next-people)
               :timestamp (%2 :timestamp)
               :next-people ((if (= (%2 :type) :enter) + -) (%1 :people) (%2 :count)))
            {:frame       [0 0]                             ; init value: no people, no particular time
             :timestamp   0
             :people      0
             :next-people 0}
            entries))
   :frame))                                                 ; get the time frame from the found value

(println (busiest-period [{:timestamp 1526579928 :count 3 :type :enter}
                          {:timestamp 1526580382 :count 2 :type :exit}]))