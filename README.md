# Demo Application for the Magical Beans and Mystery Talk

## Spring Framework Web MVC example
```
./gradlew assemble -p spring-framework-web-app
docker run -d -p 8080:8080 -v $(pwd)/spring-framework-web-app/build/libs/spring-framework-web-app-1.0-SNAPSHOT.war:/usr/local/tomcat/webapps/ROOT.war tomcat:latest
```

## Spring Framework Data access example
```
./gradlew assemble -p spring-framework-data-access
(cd spring-framework-data-access  && docker compose up)
```

