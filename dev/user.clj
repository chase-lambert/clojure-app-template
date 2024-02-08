(ns user
  (:require 
    [integrant.repl       :as ig-repl]
    [integrant.repl.state :as state]
    [server.config        :refer [config]]
    [server.core]))

(ig-repl/set-prep!
  (fn []
    config))

(def go ig-repl/go)
(def halt ig-repl/halt)
(def reset ig-repl/reset)
(def reset-all ig-repl/reset-all)

(def app (-> state/system :handler/run-app))
(def db  (-> state/system :db/postgres))

(comment
  (go)
  (halt)
  (reset))
