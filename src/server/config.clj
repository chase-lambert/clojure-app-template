(ns server.config 
  (:require
   [aero.core       :as aero]
   [clojure.java.io :as io]
   [integrant.core  :as ig]))

(defmethod aero/reader 'ig/ref
  [_ _ value]
  (ig/ref value))

(def config
  (-> "config.edn"
      (io/resource)
      (aero/read-config)))

