# Demo Application for the Magical Beans and Mystery Talk

## Spring Framework Web MVC example
```
./gradlew assemble -p spring-framework-web-app
WAR_FILE_DIR=$(pwd)/spring-framework-web-app/build/libs/spring-framework-web-app-1.0-SNAPSHOT.war
docker run -d -p 8080:8080 -v $WAR_FILE_DIR:/usr/local/tomcat/webapps/ROOT.war tomcat:latest
```
