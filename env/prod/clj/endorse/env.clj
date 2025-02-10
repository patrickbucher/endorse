(ns endorse.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[endorse started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[endorse has shut down successfully]=-"))
   :middleware identity})
