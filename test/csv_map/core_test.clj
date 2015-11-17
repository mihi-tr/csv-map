(ns csv-map.core-test
  (:use clojure.test
        csv-map.core))

(def test-csv
  (str
   "a,b,c\n"
   "1,2,3\n"
   "4,5,6\n"))

(deftest write-is-symmetric
  (testing "write-csv is symmetric with parse-csv (for simple headers)"
    (is (= (write-csv (parse-csv test-csv)) test-csv))))

(deftest write-is-symmetric-for-keyword
  (testing "write-csv is keyword-symmetric with parse-csv (for simple headers)"
    (is (= (write-csv (parse-csv test-csv :key :keyword)) test-csv))))
