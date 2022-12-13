# gwt-stockwatch-maven
The GWT stockwatch demo application, maven flavor.

To test it, run:

`mvn -U -e gwt:codeserver -pl stockwatcher-client -am`

to execute the codeserver (just keep that running).

Then you can use

`mvn -U jetty:run -pl stockwatcher-server -am -Denv=dev`

to run the application in developer mode (the URL is `http://localhost:8080/`). 

From now on, whenever you modify the code inside the client module a simple reload on the browser will update the running code on the fly.

If you modify the code inside the server module you can update the server-side part of the running application by issuing

`mvn -U compile -pl *-server -am`
