# endorse

Clojure toy web project to endorse (or insult) other people.

## Setup

Create the project using a specific template version, h2 and http-kit:

```sh
lein new luminus endorse --template-version 3.91  -- +h2 +http-kit
```

Start the REPL:

```sh
lein repl
```

Start the web application:

```clojure
(start)
```

The application is running under [localhost:3000](http://localhost:3000/).

### Migrations

Cleanup the existing migrations:

```sh
rm resources/migrations/*
```

Create a new migration in the REPL:

```clojure
(create-migration "endorse")
```

Create a table in the `resources/migrations/*.up.sql` file:

```sql
create table endorsement (
    id integer primary key auto_increment,
    name varchar(30),
    message varchar(200),
    timestamp timestamp default current_timestamp
);
```

Drop the table in the `resources/migrations/*.down.sql` file:

```sql
drop table endorsement;
```

Run the migration in the REPL, and restart the application:

```clojure
(migrate)
(restart)
```

Create queries in `resources/sql/queries.sql`:

```sql
-- :name save-endorsement! :! :n
-- :doc creates a new endorsement using the name and message keys
insert into endorsement(name, message)
values (:name, :message);

-- :name get-endorsements :? :*
-- :doc selects all available endorsements
select * from endorsement;
```

The HugSQL library automatically generates functions for those
queries, for which the metadata above each query is used:

- `:name` is how to name the function
- `:!` indicates that the query as destructive (it changes state)
- `:n` indicates that the number of rows is returned
- `:?` indicates a select operation
- `:*` indicates that multiple rows are returned

Switch to the `endorse.db.core` namespace, connect to the database,
and execute queries:

```clojure
(in-ns 'endorse.db.core)
(conman/bind-connection *db* "sql/queries.sql")
(save-endorsement! {:name "Joe" :message "Nice guy."}) ; 1
(get-endorsements) ; [{:id 1, :name "Joe", :message "Nice guy.", :timestamp … }]
```

### Testing

Run the test cases (`test/clj/endorse/db/core_test.clj`):

```sh
lein test
```

Automatically run the tests upon changes:

```sh
lein test-refresh
```

### Routing

Define routes (`src/clj/endorse/routes/home.clj`), e.g.:

```clojure
(defn home-page [request]
  (layout/render request "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))
```

A map is passed to the template (`endorse/resources/html/home.html`),
whose keys (here: `:docs`) can be used to render the page:

```html
{% extends "base.html" %}
{% block content %}
  <div class="content">
  {{docs|markdown}}
  </div>
{% endblock %}
```

TODO: Validating Input