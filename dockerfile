FROM openjdk:11

COPY target/short-0.0.1-SNAPSHOT.jar /short.jar

CMD ["java", "-jar", "/short.jar"]