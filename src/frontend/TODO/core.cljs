(ns TODO.core
  (:require 
    [reagent.dom :as rdom]))

(defn greeting 
  []
  [:h1.text-3xl.font-extrabold.mt-6
   "Hello World!"])

(defn app 
  []
  [:div.container
   [greeting]])

(defn ^:dev/after-load main 
  []
  (rdom/render 
    [app]
    (.getElementById js/document "app")))
