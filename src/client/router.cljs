(ns client.router
  (:require
   [client.events        :as events]
   [client.views         :refer [demo home-page]]
   [re-frame.core        :as rf]
   [reitit.coercion.spec :as rss]
   [reitit.frontend      :as rfe]
   [reitit.frontend.easy :as rfee]))

(def routes
  ["/"
   [""     {:name        ::home
            :view        #'home-page
            :link-text   "home"
            :controllers [{:start (fn [& params] (js/console.log "Entering home page"))
                           :stop  (fn [& params] (js/console.log "Leaving home page"))}]}]

   ["demo" {:name ::demo
            :view #'demo
            :link-text "demo"
            :controllers [{:start (fn [& params] (js/console.log "Entering demo"))
                           :stop  (fn [& params] (js/console.log "Leaving demo"))}]}]])
            
(defn on-navigate [new-match]
  (when new-match
    (rf/dispatch [::events/navigated new-match])))

(def router
  (rfe/router routes {:data {:coercion rss/coercion}}))

(defn init-routes! []
  (js/console.log "initializing routes")
  (rfee/start! router on-navigate {:use-fragment true}))

