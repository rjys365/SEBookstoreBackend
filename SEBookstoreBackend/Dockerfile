FROM bellsoft/liberica-openjdk-debian:17
ARG JAR_FILE=target/SEBookStoreBackend-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
