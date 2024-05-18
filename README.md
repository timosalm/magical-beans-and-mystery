# Demo Application for the Magical Beans and Mystery Talk

## Spring Framework Web MVC example
```
./gradlew assemble -p spring-framework-web-app
docker run -d -p 8080:8080 -v $(pwd)/spring-framework-web-app/build/libs/spring-framework-web-app-1.0-SNAPSHOT.war:/usr/local/tomcat/webapps/ROOT.war tomcat:latest
```

Open `http://localhost:8080/api/v1/recipes` in your browser.

## Spring Framework Data access example
```
./gradlew assemble -p spring-framework-data-access
(cd spring-framework-data-access  && docker compose up)
```

Open `http://localhost:8080/api/v1/recipes` in your browser.

## Spring Boot example
```
./gradlew bootRun -p spring-boot
```
or
```
./gradlew assemble -p spring-boot
(cd spring-boot  && docker compose up)
```

Open `http://localhost:8080/api/v1/recipes` in your browser.

### Spring Security
Uncomment the `spring-boot-starter-security` in `build.gradle` and restart the application.
If you open `http://localhost:8080/api/v1/recipes` in your browser, a login form for basic auth should appear.
The default username for basic auth with Spring Security is "user", the password is random and is available in the application logs.
