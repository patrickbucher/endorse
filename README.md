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

TODO: Querying the Database
