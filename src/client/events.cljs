(ns client.events
  (:require
   [client.db :refer [initial-app-db]]
   [re-frame.core :as rf]
   [reitit.frontend.controllers :as rfc]
   [reitit.frontend.easy :as rfee]))

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


(rf/reg-fx 
  :push-state
  (fn [route]
    (apply rfee/push-state route)))

(rf/reg-event-fx 
  ::push-state
  (fn [_ [_ & route]]
    {:push-state route}))
  
(rf/reg-event-db
  ::navigated
  (fn [db [_ new-match]]
    (let [old-match (:current-route db)
          controllers (rfc/apply-controllers (:controllers old-match) new-match)]
      (assoc db :current-route (assoc new-match :controllers controllers)))))           
