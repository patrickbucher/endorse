(ns endorse.db.core-test
  (:require
   [endorse.db.core :refer [*db*] :as db]
   [java-time.pre-java8]
   [luminus-migrations.core :as migrations]
   [clojure.test :refer :all]
   [next.jdbc :as jdbc]
   [endorse.config :refer [env]]
   [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (mount/start
     #'endorse.config/env
     #'endorse.db.core/*db*)
    (migrations/migrate ["migrate"] (select-keys env [:database-url]))
    (f)))

(deftest test-endorsements
  (jdbc/with-transaction [t-conn *db* {:rollback-only true}]
    (is (= 1 (db/save-endorsement!
              t-conn
              {:name "Joe" :message "What an impressive person."}
              {:connection t-conn})))
    (is (= {:name "Joe" :message "What an impressive person."}
           (-> (db/get-endorsements t-conn {})
               (first)
               (select-keys [:name :message]))))))
