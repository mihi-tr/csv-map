(ns csv-map.core
  (:require (clojure-csv core)))

(defn parse-csv 
  "parses a csv to a map
   ([csv & {:as opts}])
   passes options to clojure-csv
   "
  [csv & {:as opts}]
  (let [opts (vec (reduce concat (vec opts)))
        c (apply clojure-csv.core/parse-csv csv opts)]
  (map (partial zipmap (first c)) (rest c))))

(defn write-csv
  "converts a sequence of maps to csv
   ([map-sequencei & {:as opts}])
  passes options to clojure-csv"
  [map-sequence & {:as opts}]
  (let [opts (vec (reduce concat (vec opts)))
        header (vec (keys (first map-sequence)))
        data (map (fn [line] 
                    (vec (map (fn [item]
                           (get line item))
                         header))) 
                  (rest map-sequence))]
    (apply clojure-csv.core/write-csv (cons header data) opts)))