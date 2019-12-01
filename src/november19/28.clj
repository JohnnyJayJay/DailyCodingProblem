; Given the sequence of keys visited by a postorder traversal of a binary search tree, reconstruct the tree.
;
; For example, given the sequence 2, 4, 3, 8, 7, 5, you should construct the following tree:
;
;     5
;    / \
;   3   7
;  / \   \
; 2   4   8
(ns november19.28)

(defn construct-tree [postorder-traversal]
  (if (empty? postorder-traversal)
    {}                                                      ; The tree for an empty postorder walk is empty
    (let [root (last postorder-traversal)                   ; Last element is root
          ; index to split the vector on is the last index whose value is less than root + 1; if no such index exists, default to 0
          split (if-let [split (last (filter #(< (postorder-traversal %) root) (range (count postorder-traversal))))] (inc split) 0)]
      {:value root
       :left  (construct-tree (subvec postorder-traversal 0 split)) ; left: all elements smaller than root (0-split)
       :right (construct-tree (subvec postorder-traversal split (dec (count postorder-traversal))))}))) ; right: all elements greater than root (split-(size - 1))

(println (construct-tree [2 4 3 8 7 5]))