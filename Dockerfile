#Use Alpine JDK 8 docker image to run the application
FROM openjdk:8-jdk-alpine

#Use root as working directory
WORKDIR /

#Copy /src for building the application
COPY /src ./src

#Use this port to run the application
EXPOSE 8085

#Create a new directory to copy built JAR file
RUN mkdir /toyrobot

#Copy the built JAR file of the application into api folder
COPY /target/toyrobot-1.0.0.jar /toyrobot/toyrobot-1.0.0.jar

#Entrypoint command to run when Docker container is started
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom", "-jar","/toyrobot/toyrobot-1.0.0.jar"]
