(ns TODO.core
  (:require 
    [aero.core               :as aero]
    [clojure.java.io         :as io]
    [muuntaja.middleware     :as muuntaja]
    [reitit.ring             :as ring]
    [ring.adapter.jetty      :as jetty]
    [ring.middleware.gzip    :refer [wrap-gzip]]
    [ring.middleware.reload  :refer [wrap-reload]]
    [ring.util.http-response :as response])
  (:gen-class))

(def config 
  (aero/read-config 
    (io/resource "config.edn")))

(defn wrap-nocache [handler]
  (fn [request]
    (-> request
        handler
        (assoc-in [:headers "Pragma"] "no-cache"))))

(defn wrap-formats [handler]
  (-> handler
      (muuntaja/wrap-format)))

(defn index-handler [_]
  (response/ok
   (slurp 
     (io/resource "public/index.html"))))

(def app 
  (ring/routes
    (ring/ring-handler
      (ring/router
        [["/" {:get index-handler}]]))
    (ring/create-file-handler {:path "/" :root "resources/public"})))

(defn -main [& _]
  (jetty/run-jetty 
    (-> #'app 
        wrap-nocache 
        wrap-formats
        wrap-reload
        wrap-gzip) 
    {:port (:port config)
     :join? false}))
