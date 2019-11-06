; Implement a stack API using only a heap. A stack implements the following methods:
;
; - push(item), which adds an element to the stack
; - pop(), which removes and returns the most recently added element
;   (or throws an error if there is nothing on the stack)
;
; Recall that a heap has the following operations:
;
; - push(item), which adds a new key to the heap
; - pop(), which removes and returns the max value of the heap
(ns november19.03)

;; Heap implementation
(defn swap [heap one two]
  (assoc heap one (nth heap two) two (nth heap one)))

(defn parent [pos] (int (/ (dec pos) 2)))

(defn left-child [pos] (inc (* 2 pos)))

(defn right-child [pos] (+ (* 2 pos) 2))

(defn bigger? [heap x y]
  (> (.compareTo (nth heap x) (nth heap y)) 0))

(defn heapify-down [heap pos]
  (let [left (left-child pos)
        right (right-child pos)
        size (count heap)]
    (if (and (< left right size) (or (bigger? heap left pos) (bigger? heap right pos)))
      (if (bigger? heap left right)
        (recur (swap heap pos left) left)
        (recur (swap heap pos right) right))
      heap)))

(defn heapify-up [heap pos]
  (let [parent (parent pos)]
    (if (and (> pos 0) (bigger? heap pos parent))
      (recur (swap heap pos parent) parent)
      heap)))

(defn heap-push [heap key]
  (heapify-up (conj heap key) (count heap)))

(defn heap-pop [heap]
  (heapify-down (pop (assoc heap 0 (last heap))) 0))

(defn heap-peek [heap]
  (nth heap 0))

;; Stack implementation (solely based on heap)
(defrecord StackElement [value key] ; value: The "real" value of the stack element, key: the key to be used in the heap
  Comparable
  (compareTo [_ other] (.compareTo key (:key other))))

; heap insert value wrapped in StackElement - key is either one greater than the heap's max value or 0 if it's empty
(defn stack-push [stack value]
  (heap-push stack (->StackElement value (if (empty? stack) 0 (inc (:key (heap-peek stack)))))))

(defn stack-peek [stack]
  (:value (heap-peek stack)))

(defn stack-pop [stack]
  (heap-pop stack))

;; Tests
(loop [stack (-> [] (stack-push 100) (stack-push 40) (stack-push 120))]
  (println (stack-peek stack))
  (if (= (count stack) 1) nil (recur (stack-pop stack))))