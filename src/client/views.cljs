(ns client.views 
  (:require
   [re-frame.core :as rf]
   [client.subs   :as subs]
   [client.events :as events]))

(defn greeting []
  (let [name  @(rf/subscribe [::subs/name])
        count @(rf/subscribe [::subs/count])]
    [:div
     [:h1.text-3xl.font-extrabold
      "Hello " name "!"]
     [:h2
      "High-five counter: " count]
     [:button {:on-click #(rf/dispatch [::events/increment-count])}
      "Up high!"]
     [:button {:on-click #(rf/dispatch [::events/decrement-count])}
      "Down low!"]]))

