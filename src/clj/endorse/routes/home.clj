(ns endorse.routes.home
  (:require
   [endorse.layout :as layout]
   [endorse.db.core :as db]
   [clojure.java.io :as io]
   [endorse.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]))

(defn home-page [request]
  (layout/render request "home.html" {:endorsements (db/get-endorsements)}))

(defn save-endorsement! [{:keys [params]}]
  (db/save-endorsement! params)
  (response/found "/"))

(defn about-page [request]
  (layout/render request "about.html"))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/message" {:post save-endorsement!}]
   ["/about" {:get about-page}]])

