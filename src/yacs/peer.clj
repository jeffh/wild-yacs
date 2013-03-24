(ns yacs.peer
  (:require [datomic.api :as d :refer (q)]))

(def uri "datomic:mem://yacs")

(def schema-tx (read-string (slurp "resources/yacs/schema.dtm")))

(defn init-db []
  (when (d/create-database uri)
    (let [conn (d/connect uri)]
      @(d/transact conn schema-tx))))


(defn sources []
  (init-db)
  (let [conn (d/connect uri)]
    (q '[:find ?v :where [:semester ?a ?v]]
       (d/db conn))))

