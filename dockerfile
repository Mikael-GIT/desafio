FROM maven:3.8.5-openjdk-11 as build
WORKDIR /usr/app
COPY . .
RUN ls /usr/app
RUN mvn clean install

FROM openjdk:11
WORKDIR /app
RUN ls
COPY --from=build /usr/app/target/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT java -jar app.jar
