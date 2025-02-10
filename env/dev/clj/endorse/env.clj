(ns endorse.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [endorse.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[endorse started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[endorse has shut down successfully]=-"))
   :middleware wrap-dev})
