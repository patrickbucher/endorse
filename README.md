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
