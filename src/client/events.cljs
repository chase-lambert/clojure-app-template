(ns client.events
  (:require
   [client.db :refer [initial-app-db]]
   [re-frame.core :as rf]))

(rf/reg-event-db
  ::initialize-db
  (fn [_ _]
    initial-app-db))

(rf/reg-event-db
  ::increment-count
  (fn [db _]
    (update db :count inc)))

(rf/reg-event-db
  ::decrement-count
  (fn [db _]
    (update db :count dec)))
