(set-env! 
 :source-paths #{"src/main/java" "src/main/clojure"}
 :resource-paths #{"src/main/clojure"}
 :repositories [
 ["central" "https://repo1.maven.org/maven2/"]
 ["clojars" {:url "https://clojars.org/repo/" :username (System/getenv "CLOJARS_USER") :password (System/getenv "CLOJARS_PASS")}]]
 :dependencies `[
 [org.clojure/clojure ~(clojure-version)]
 [net.mikera/orculje "0.0.2"]
 [net.mikera/mikera "1.5.0"]
 [net.mikera/swing-console "0.1.2"]
 [net.mikera/mathz "0.1.0"]
 [net.mikera/cljunit "0.2.0"]
 [org.clojure/math.combinatorics "0.0.3"]])

(task-options!
 ; push {:repo "clojars"}
 pom {:project 'hellonico-alchemy :version "0.1.0"}
 aot {:namespace #{'mikera.alchemy.main} }
 uber {:exclude  #{ #".*pom.xml$" }}
 jar { :file "alchemy.jar"  :main 'mikera.alchemy.main
       :manifest {"Description" "This is a sample game in clojure"
                    "Url" "https://github.com/animalia/animalia-autentisering"}})

(deftask uberjar
  "Build my project."
  []
  (comp 
   (javac)
   (aot)
   (pom)
   (uber)
   (jar)))