(ns TODO.db
  (:require 
    [aero.core :as aero] 
    [clojure.java.io :as io]
    [next.jdbc :as jdbc]))

(def config 
  (aero/read-config 
    (io/resource "config.edn")))

;; (def ds
  ;; (jdbc/get-datasource 
  ;;   {:dbtype "postgresql"
  ;;    :dbname "ai"
  ;;    :user "postgres"
  ;;    :password (System/getenv "DB_PW")}))

