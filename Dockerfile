FROM maven:3.6.3-openjdk-11
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package
ENTRYPOINT ["java", "-jar", "target/ficticius-clean-0.0.1-SNAPSHOT.jar"]