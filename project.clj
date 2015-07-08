(defproject cljreplagent "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :manifest     {"Premain-Class" "cljreplagent.core.CljReplAgent"}
  :profiles     {:uberjar {:aot :all}}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.nrepl "0.2.10"]])
