(ns server.core
  (:require
   [integrant.core          :as ig]
   [ring.adapter.jetty      :as jetty]
   [server.config           :refer [config]]
   [server.handler :refer [main-handler]])
  (:gen-class))

(defmethod ig/init-key :server/jetty
  [_ {:keys [handler port]}]
  (println "\nServer running on port" port)
  (jetty/run-jetty handler {:port port
                            :join? false}))

(defmethod ig/init-key :handler/run-app
  [_ config]
  (println "\nStarted app")
  (main-handler config))

(defmethod ig/init-key :db/postgres
  [_ config]
  (println "\nConfigured db")
  (:jdbc-url config))

(defmethod ig/halt-key! :server/jetty
  [_ server]
  (.stop server))

(defn -main [& _]
  (ig/init config))

(comment
  (-main))
