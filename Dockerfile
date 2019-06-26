FROM openjdk:11
LABEL AUTHOR="Douglas Schwingel"
COPY ./build/libs/ /var/www
WORKDIR /var/www  
EXPOSE 9000:9000 27017:27017
ENTRYPOINT [ "java", "-jar", "upbox-server-0.0.1-SNAPSHOT.jar" ]
