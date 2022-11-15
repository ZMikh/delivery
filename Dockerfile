FROM openjdk:11
RUN mkdir -p /opt/app/
WORKDIR /opt/app/
COPY ./target/delivery-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "/opt/app/delivery-0.0.1-SNAPSHOT.jar"]