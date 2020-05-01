FROM openjdk:8

ADD target/ratings-data-service.jar ratings-data-service.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "ratings-data-service.jar"]

