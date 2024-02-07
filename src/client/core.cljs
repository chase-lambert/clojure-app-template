(ns client.core
  (:require
   [clojure.string  :as string]
   [reagent.dom     :as rdom]
   [shadow.resource :as rc]))

;; The tailwind watch compiler does not work well with clojurescript's
;; hot code reloading so I am bringing in tailwind's cdn for dev use
;; but do not want it included in production builds. 
(goog-define TW false)

(defn tailwindcdn []
  (let [config (-> (rc/inline "tailwind.config.js")
                   (string/replace #"^module.exports" "tailwind.config")
                   (string/replace #"require\(.*\)" ""))]
    (js/console.log "tailwindcdn enabled")
    [:<> 
     [:link {:href "https://cdn.jsdelivr.net/npm/daisyui@4.6.2/dist/full.min.css"
             :rel  "stylesheet"
             :type "text/css"}]
     [:script {:src "https://cdn.tailwindcss.com/3.4.1"}]
     [:script {:dangerouslySetInnerHTML {:__html config}}]]))

(defn greeting []
  [:<>
    [:h1 {:class "text-3xl font-extrabold mt-6"}
     "Hello World!"]
    [:button.btn.btn-primary {:on-click #(js/alert "Hi!")}
     "Click me!"]])
  

(defn app []
  [:div.container
   (when TW          ;; Only include tailwind cdn for dev use
      [tailwindcdn])
   [greeting]])

(defn ^:dev/after-load start []
  (rdom/render [app]
    (.getElementById js/document "app")))

(defn ^:export main []
  (start))
