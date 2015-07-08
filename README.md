# cljreplagent

A Clojure library that enables a remote repl when started in any JVM using a `-javaagent` flag.

## Usage

    java -javaagent:/path/to/cljreplagent-0.1.0-SNAPSHOT-standalone.jar $OTHER_OPTIONS -jar anyjavaprogram.jar

then connect to the repl server with

    lein repl :connect localhost:4567

The repl port can be set with a JVM property.

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
