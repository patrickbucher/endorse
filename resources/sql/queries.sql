-- :name save-endorsement! :! :n
-- :doc creates a new endorsement using the name and message keys
insert into endorsement(name, message)
values (:name, :message);

-- :name get-endorsements :? :*
-- :doc selects all available endorsements
select * from endorsement;
