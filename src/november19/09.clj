; Given a tree where each edge has a weight, compute the length of the longest path in the tree.
;
; For example, given the following tree:
;
;   a
;  /|\
; b c d
;    / \
;   e   f
;  / \
; g   h
;
; and the weights: a-b: 3, a-c: 5, a-d: 8, d-e: 2, d-f: 4, e-g: 1, e-h: 1,
; the longest path would be c -> a -> d -> f, with a length of 17.
;
; The path does not have to pass through the root, and each node can have any amount of children.
(ns november19.09)

; TODO optimise this

; Starting from any node, work all possible ways down, not limited to leaf nodes as the last nodes
(defn longest-path-down [root total-weight]
  (let [children (root :children)]
    (if (seq children)
      (apply
        max                                                 ; if children available, return the maximum value of the longest-path-down from all children
        (for [child children]
          (max total-weight
               (longest-path-down child (+ total-weight (:weight child)))))) ; stop at this node if the total weight is larger than any further path
      total-weight)))                                       ; if at leaf, return the total weight acquired along the way

; The time complexity for this should theoretically be O(n²) but is currently rather something
; like O(n!) because of this bad implementation.
; At least it handles all possible cases (including negative weights).
(defn longest-path [tree]
  (apply max
         (apply + (take 2 (reverse (sort (filter (partial < 0) (for [child (tree :children)]
                                                                 (longest-path-down child (child :weight))))))))
         (for [child (tree :children)] (longest-path child)))) ; for all nodes, find the longest path that starts with them and from that extract the longest one


(def tree
  {:weight   0
   :children [{:weight   3
               :children []}
              {:weight   5
               :children []}
              {:weight   8
               :children [{:weight   2
                           :children [{:weight   1
                                       :children []}
                                      {:weight   1
                                       :children []}]}
                          {:weight   4
                           :children []}]}]})


(println (longest-path tree))