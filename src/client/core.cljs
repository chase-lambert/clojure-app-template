(ns client.core
  (:require
   [client.events   :as events]
   [client.router   :refer [init-routes! router]]
   [client.views    :refer [router-component]]
   [clojure.string  :as str]
   [re-frame.core   :as rf]
   [reagent.dom     :as rdom]
   [shadow.resource :as rc]))

;; The tailwind watch compiler does not work well with clojurescript's
;; hot code reloading so I am bringing in tailwind's cdn for dev use
;; but do not want it included in production builds. 
(goog-define TW false)

(defn tailwindcdn []
  (let [config (-> (rc/inline "tailwind.config.js")
                   (str/replace #"^module.exports" "tailwind.config")
                   (str/replace #"require\(.*\)" ""))]
    (js/console.log "tailwindcdn enabled")
    [:<> 
     [:link {:href "https://cdn.jsdelivr.net/npm/daisyui@4.11.1/dist/full.min.css"
             :rel  "stylesheet"
             :type "text/css"}]
     [:script {:src "https://cdn.tailwindcss.com/3.4.3"}]
     [:script {:dangerouslySetInnerHTML {:__html config}}]]))

(defn app []
  [:<>
   (when TW [tailwindcdn])
   [router-component {:router router}]])

(defn ^:dev/after-load start []
  (rdom/render [app]
    (.getElementById js/document "app")))

;; (def debug? ^boolean goog.DEBUG)

;; (defn dev-setup []
;;   (when debug?
;;     (enable-console-print!)
;;     (println "dev mode")))

(defn ^:export main []
  (rf/clear-subscription-cache!)
  (rf/dispatch-sync [::events/initialize-db])
  ;; (dev-setup)
  (init-routes!)
  (start))
