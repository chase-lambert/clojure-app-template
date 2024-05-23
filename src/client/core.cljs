(ns client.core
  (:require
   [client.events   :as events]
   [client.router   :as-alias routes :refer [init-routes! router]]
   [client.views    :refer [router-component]]
   [re-frame.core   :as rf]
   [reagent.dom     :as rdom]))

(defn app []
  [:<>
   [router-component {:router router}]])

(defn ^:dev/after-load start []
  (rdom/render [app]
    (.getElementById js/document "app")))

(def debug? ^boolean goog.DEBUG)

(defn dev-setup []
  (when debug?
    (enable-console-print!)
    (println "dev mode")))

(defn ^:export main []
  (rf/clear-subscription-cache!)
  (rf/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (init-routes!)
  (start))
