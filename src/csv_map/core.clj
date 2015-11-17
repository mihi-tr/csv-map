(ns csv-map.core
  (:require (clojure-csv core)
            [clojure.string :as s]))

;; Helper

(defn- keywordize-
  "takes a map, converts string keys to keyword keys
   with all lowercase and dash instead of spaces"
  [m]
  (into {}
    (for [[k v] m]
      [(keyword (s/lower-case (s/replace k #" " "-"))) v])))

;; API

(defn parse-csv
  "parses a csv to a map
   ([csv & {:as opts}])
   passes options to clojure-csv
   converts string keys to keywords
   if ':key :keyword' is pass as extra opts.
   "
  [csv & {key :key :as opts}]
  (let [opts   (vec (reduce concat (vec opts)))
        c      (apply clojure-csv.core/parse-csv csv opts)
        output (map (partial zipmap (first c)) (rest c))]
    (if (= key :keyword) (map keywordize- output) output)))

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
                  map-sequence)]
    (apply clojure-csv.core/write-csv (cons (map name header) data) opts)))
