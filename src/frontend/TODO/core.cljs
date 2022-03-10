(ns TODO.core
  (:require 
    [reagent.dom  :as rdom]))

(defn greeting 
  []
  [:h1.text-3xl.font-extrabold.mt-6
   "Hello World!"])

(defn btn 
  []
  [:button.btn.btn-success 
   "click me"])

(defn app 
  []
  [:div.container
   [greeting]
   [btn]])

(defn ^:dev/after-load main 
  []
  (rdom/render 
    [app]
    (.getElementById js/document "app")))
