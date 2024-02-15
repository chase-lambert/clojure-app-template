(ns client.core
  (:require
   [client.events :as events]
   [client.views  :refer [greeting]]
   [re-frame.core :as rf]
   [reagent.dom   :as rdom]))

(defn app []
  [:div
    [greeting]])

(defn ^:dev/after-load start []
  (rdom/render [app]
    (.getElementById js/document "app")))

(defn ^:export main []
  (rf/dispatch-sync [::events/initialize-db])
  (start))
