FROM bellsoft/liberica-openjdk-debian:17
ARG JAR_FILE=target/SEBookStoreBackend-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","--add-exports","java.base/sun.nio.ch=ALL-UNNAMED","-jar","/app.jar"]
