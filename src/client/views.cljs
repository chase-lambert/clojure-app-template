(ns client.views 
  (:require
   [client.events        :as events]
   [client.subs          :as subs]
   [re-frame.core        :as rf]
   [reitit.coercion.spec :as rss]
   [reitit.frontend      :as rfe]
   [reitit.frontend.easy :as rfee]
   [reitit.core          :as rc]))

;; (defn href
;;   "return relative url for given route. Url can be used in HTML links."
;;   ([k] (href k nil nil))
;;   ([k params] (href k params nil))
;;   ([k params query] (rfee/href k params query)))

(defn demo []
  [:div
   [:h1.text-3xl.font-extrabold 
    "DEMO"]
   [:button.btn.btn-primary.m-4
    {:on-click #(rf/dispatch [::events/push-state ::home])}
    "Homepage"]])

(defn home-page []
  (let [name  @(rf/subscribe [::subs/name])
        count @(rf/subscribe [::subs/count])]
    [:<>
     [:h1.text-3xl.font-extrabold.mb-4
      "Hello " name "!"]
     [:h2.font-extrabold
      "High-five counter: " count]
     [:button.btn.btn-primary.m-4
      {:on-click #(rf/dispatch [::events/increment-count])}
      "Up high!"]
     [:button.btn.btn-primary 
      {:on-click #(rf/dispatch [::events/decrement-count])}
      "Down low!"]
     [:br]
     [:button.btn.btn-primary.m-4
      {:on-click #(rf/dispatch [::events/push-state ::demo])}
      "Go to demo"]]))


(def routes
  ["/"
   [""     {:name        ::home
            :view        home-page
            :link-text   "Home"
            :controllers [{:start (fn [& params] (js/console.log "Entering home page"))
                           :stop  (fn [& params] (js/console.log "Leaving home page"))}]}]

   ["demo" {:name ::demo
            :view demo
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

;; (defn nav [{:keys [router current-route]}]
;;   [:ul
;;    (for [route-name (rc/route-names router)
;;          :let [route (rc/match-by-name router route-name)
;;                text (-> route :data :link-text)]]
;;      [:li {:key route-name}
;;       (when (= route-name (-> current-route :data :name))
;;         "> ")
;;       [:a {:href (href route-name)} text]])])


(defn router-component [{:keys [router]}]
  (let [current-route @(rf/subscribe [::subs/current-route])]
    [:div 
     ;; [nav {:router router :current-route current-route}]
     (when current-route 
       [(-> current-route :data :view)])])) 

(comment
  (rc/match-by-path router "/demo")
  (rc/match-by-path router ""))
