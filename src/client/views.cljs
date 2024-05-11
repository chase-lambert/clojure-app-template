(ns client.views 
  (:require
   [re-frame.core :as rf]
   [client.subs   :as subs]
   [client.events :as events]))

(defn greeting []
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
      "Down low!"]]))

