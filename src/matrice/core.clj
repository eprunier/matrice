(ns matrice.core
  (:refer-clojure exclude [+ max min]))

(defn scalar? 
  [x]
  (or (instance? java.lang.Long x)
      (instance? java.lang.Double x)))

(defprotocol Matrix
  (print [m]
    "Print the matrix m.")
  (+ [a b]
    "Add b to matrix a.")
  (- [a b]
    "Subtract b from matrix a.")
  (* [a b]
    "Multiply matrix a by b.")
  (/ [a b]
    "Divide matrix a by b.")
  (max [m]
    "Compute the maximum value for each column.")
  (min [m]
    "Compute the minimum value for each column.")
  (mean [m]
    "Compute the mean for each column")
  (std [m]
    "Compute the standard derivation for each column."))

(extend-protocol Matrix
  clojure.lang.PersistentVector
  (print [m]
    (doseq [row m]
      (if (seq? row)
        (println (apply str (interpose " " row)))
        (clojure.core/print row))))
  (+ [a b]
    (if (scalar? b)
      (vec (map #(+ % b) a))
      a))
  (- [a b]
    (+ a (clojure.core/- b)))
  (* [a b])
  (/ [a b])
  (max [m])
  (min [m])
  (mean [m])
  (std [m]))

(extend-protocol Matrix
  java.lang.Long
  (+ [a b]
    (clojure.core/+ a b))
  (- [a b]
    (clojure.core/- a b))
  (* [a b]
    (clojure.core/* a b))
  (/ [a b]
    (clojure.core// a b))
  (max [m]
    m)
  (min [m]
    m)
  (mean [m]
    m)
  (std [m]
    m))

(extend-protocol Matrix
  java.lang.Double
  (+ [a b]
    (clojure.core/+ a b))
  (- [a b]
    (clojure.core/- a b))
  (* [a b]
    (clojure.core/* a b))
  (/ [a b]
    (clojure.core// a b))
  (max [m]
    m)
  (min [m]
    m)
  (mean [m]
    m)
  (std [m]
    m))
