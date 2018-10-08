# heavenhr-java-interview-exercise
Backend service that handles a (very simple) recruiting process.

The project was building in Eclipse with a maven archtype. I do not used lombok on this project to avoid compatibility issues
in your IDE.

For the purpose of the exercise I used basic auth in memory. The user login is: heavenhr and the password is: secret
To treat the data at runtime I used the h2 database that runs in memory.

Like asked the application is a self-container packaged in a .jar file. (java 8)
  To run the .jar, in console type: java -jar PATH_DO_JAR/name.of.file.jar
  Tomcat will start on port 8080

In the URL http://127.0.0.1:8080/swagger-ui.html you can see the endopints of thr api.
I put a file of postman with 19 request for test the uses cases.(Test.Api.HeavenHR.postman_collection.json)
