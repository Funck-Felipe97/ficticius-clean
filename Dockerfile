FROM openjdk:11
VOLUME /app
COPY ficticius-clean.jar ficticius-clean.jar
ENTRYPOINT ["java", "-jar", "ficticius-clean.jar"]