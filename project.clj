(defproject yacs "3.0.0-SNAPSHOT"
  :description "YACS, rewritten"
  :url "http://yacs.me/"
  :license {:name "Apache 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.5.0"]

                 [enlive "1.1.1"]
                 [clj-time "0.4.4"]
                 [org.clojure/data.json "0.2.1"]
                 [org.clojure/math.combinatorics "0.0.3"]
                 [com.datomic/datomic-free "0.8.3848"]

                 ;; Web
                 [io.pedestal/pedestal.service "0.1.1"]
                 [io.pedestal/pedestal.jetty "0.1.1"]

                 ;; Testing
                 [midje "1.5-RC1"]

                 ;; Logging
                 [ch.qos.logback/logback-classic "1.0.7"]
                 [org.slf4j/jul-to-slf4j "1.7.2"]
                 [org.slf4j/jcl-over-slf4j "1.7.2"]
                 [org.slf4j/log4j-over-slf4j "1.7.2"]]
  :profiles {:dev {:source-paths ["dev"]}}
  :min-lein-version "2.0.0"
  :resource-paths ["config"]
  :main ^{:skip-aot true} yacs.server)
