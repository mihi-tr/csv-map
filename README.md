# csv-map

A Clojure library that reads well formed csv files into a sequence of maps
({}) using the first line as column headers. It also supports writing csv's
from the same structures

## Usage

In leiningen add 

```clojure
[csv-map "0.1.2"]
```

Like clojure-csv csv-map exposes two public functions: parse-csv and
write-csv. Use it like:

```clojure
(use 'csv-map.core)
(parse-csv (slurp "file.csv"))
```

To parse the column names into keywords use ```:key :keyword``` as options.
csv-map uses clojure-csv as backend: this means you can pass any opts you
would pass to clojure-csv into csv-map and the options will be passed on to
clojure-csv's parse and write functions.

## License

Copyright © 2013 Michael Bauer

Distributed under the Eclipse Public License, the same as Clojure.
