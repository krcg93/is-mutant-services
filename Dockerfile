#FROM adoptopenjdk/openjdk15:ubi
FROM amazoncorretto:15
COPY build/libs/is-mutant-services-*-all.jar is-mutant-services.jar
EXPOSE 8080

ENV APPNAME=is-mutant-services
ENV PATHFIREBASE=https://services-crud-default-rtdb.firebaseio.com/


CMD java -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -Dcom.sun.management.jmxremote -jar is-mutant-services.jar
