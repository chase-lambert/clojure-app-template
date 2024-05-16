(ns server.handler 
  (:require
   [clojure.java.io :as io]
   [muuntaja.middleware :as muuntaja]
   [reitit.ring :as ring]
   [ring.middleware.gzip    :refer [wrap-gzip]]
   [ring.middleware.reload :refer [wrap-reload]]
   [ring.util.http-response :as response]))

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

(defn app [_config] 
  (ring/routes
    (ring/ring-handler
      (ring/router
        [["/" {:get index-handler}]]))
    (ring/create-file-handler {:path "/" :root "resources/public"})))

(defn main-handler [config]
  (-> (app config) wrap-nocache wrap-formats wrap-reload wrap-gzip)) 


