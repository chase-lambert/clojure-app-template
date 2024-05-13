(ns client.subs 
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 ::name
 (fn [db]
   (:name db)))

(rf/reg-sub
  ::count
  (fn [db]
    (:count db)))

(rf/reg-sub 
  ::current-route 
  (fn [db]
    (:current-route db)))
