(ns build
  (:require 
    [clojure.tools.build.api :as b]))

(def app       'TODO)
(def class-dir "target/classes")
(def basis     (b/create-basis {:project "deps.edn"}))
(def uber-file (format "target/%s.jar" (name app)))

(defn clean [_]
  (b/delete {:path "target"}))

(defn uber [_]
  (clean nil)
  (b/copy-dir    {:src-dirs   ["src/server" "resources"]
                  :target-dir class-dir})
  (b/compile-clj {:basis     basis
                  :src-dirs  ["src/server" "resources"]
                  :class-dir class-dir})
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis basis
           :main  'server.core}))
