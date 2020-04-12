FROM java:8
COPY target/daigou-0.0.1-SNAPSHOT.jar app.jar
ENV spring.profiles.active="dev"
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar"]