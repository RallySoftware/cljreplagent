# cljreplagent

A Clojure library that enables a remote repl when started in any JVM using a `-javaagent` flag.

## Usage

    java -javaagent:/path/to/cljreplagent-0.1.0-SNAPSHOT-standalone.jar $OTHER_OPTIONS -jar anyjavaprogram.jar

then connect to the repl server with

    lein repl :connect localhost:4567

The repl port can be set with a JVM property.

cljreplagent can be used with any java/clojure/scala/etc program, but
for the sake of illustration let's use it with a small clojure program
so as not to get distracted with specifics. For example, imagine a
very simple web application with a little bit of state.

    (ns hello-webapp.core
      (:require [ring.adapter.jetty :refer [run-jetty]])
      (:gen-class))
        
    (defonce requests (atom 0))
        
    (defn handler [request]
      (do
       (swap! requests inc)
       {:status 200
        :headers {"Content-Type" "text/html"}
        :body (str "Hello World " @requests "\n")}))

    (defn -main [] (run-jetty handler {:port 8080}))

Now compile that webapp into an uberjar and run it with the cljreplagent java agent.

    java -javaagent:../cljreplagent/target/cljreplagent-0.1.0-SNAPSHOT-standalone.jar -jar target/hello-webapp-0.1.0-SNAPSHOT-standalone.jar

All it does is store an integer in an atom and return the number for all requests, incrementing it each time.

    $ curl localhost:8080
    >> Hello World 1
    $ curl localhost:8080
    >> Hello World 2

Now the webapp is serving HTTP over port 8080, and it's listening for
nrepl connections on port 4567. So you can connect with a leiningen
repl or cider and inspect the application's state. Try it:

    lein repl :connect localhost:4567
    user> @hello-webapp.core/requests
    2
    user> (reset! hello-webapp.core/requests 9)
    9

Exit the repl, and the change you made is still in the application's state.

    $ curl localhost:8080
    >> Hello World 10

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
