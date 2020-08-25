FROM openjdk:11-jre

ARG JAR_FILE
ADD /target/${JAR_FILE} /app/batch.jar

WORKDIR /app

CMD ["java", "-jar", "batch.jar"]