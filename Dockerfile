FROM openjdk:17
COPY ./start/target/start-release.jar /mnt
EXPOSE 8080
CMD java /mnt/start-release.jar