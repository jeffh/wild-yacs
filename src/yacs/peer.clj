(ns yacs.peer
  (:use [clj-time.coerce :only [to-date]] )
  (:require [datomic.api :as d :refer (q)]
            [clj-time.core :as clj-time]))

(def uri "datomic:mem://yacs")

(def schema-tx (read-string (slurp "resources/yacs/schema.dtm")))

(defn as-instant [dt]
  (to-date dt))

(defn init-db []
  (when (d/create-database uri)
    (let [conn (d/connect uri)]
      @(d/transact conn schema-tx))))

(defn safe-get [dict attr]
  (if (contains? dict attr)
    (dict attr)
    (throw (Exception. (str attr " not found in " dict)))))

(defn transact [tx]
  (init-db)
  (let [conn (d/connect uri)]
    @(d/transact conn tx)))

(defn create-source! [source & [now]]
  (let [now (or now clj-time/now)]
    (transact [{:db/id #db/id[:db.part/user]
                :source/ident (safe-get source :ident)
                :source/created (as-instant (now))
                :source/updated (as-instant (now))
                :source/visible (or (source :visible) false)
                :source/merge-order (safe-get source :merge-order)}])))

(defn create-semester! [semester & [now]]
  (transact [{:db/id #db/id[:db.part/user]
              :semester/name (safe-get semester :name)
              :semester/start-date (as-instant (safe-get semester :start-date))
              :semester/departments (safe-get semester :departments)
              :semester/courses (safe-get semester :courses)}]))

(defn create-department! [department & [now]]
  (let [now (or now clj-time/now)]
    (transact [{:db/id #db/id[:db.part/user]
                :department/code (name (safe-get department :code))
                :department/created (as-instant (now))}])))

