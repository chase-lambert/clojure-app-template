(ns client.core
  (:require
   [client.events   :as events]
   [client.router   :refer [init-routes! router]]
   [client.views    :refer [router-component]]
   [clojure.string  :as str]
   [goog.object :as gobj]
   [re-frame.core   :as rf]
   [reagent.dom     :as rdom]
   [reagent.dom.server :refer [render-to-string]] 
   [shadow.resource :as rc]))

;; The tailwind watch compiler does not work well with clojurescript's
;; hot code reloading so I am bringing in tailwind's cdn for dev use
;; but do not want it included in production builds. 
(defn insert-to-head [element]
  (let [head (gobj/get js/document "head")]
    (.appendChild head element)))

(defn remove-from-head [selector]
  (let [element (.querySelector js/document selector)]
    (when element
      (.remove element))))

(defn add-tailwind-and-daisyui-cdn []
  (let [link (doto (.createElement js/document "link")
               (gobj/set "href" "https://cdn.jsdelivr.net/npm/daisyui@4.11.1/dist/full.min.css")
               (gobj/set "rel" "stylesheet")
               (gobj/set "type" "text/css"))
        script1 (doto (.createElement js/document "script")
                  (gobj/set "src" "https://cdn.tailwindcss.com/3.4.3"))]
        ;; script2 (doto (.createElement js/document "script")
        ;;           (gobj/set "innerHTML" "tailwind.config = {};"))] ;; Assuming no config for now
    (insert-to-head script1)
    (insert-to-head link)))
    ;; (insert-to-head script2)))


(goog-define TW false)

(defn app []
  [:div
   [router-component {:router router}]])

(defn ^:dev/after-load start []
  (when TW 
    (remove-from-head "link[href='/css/main.css']")
    (add-tailwind-and-daisyui-cdn)
    (js/console.log "tailwind cdn enabled"))
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
