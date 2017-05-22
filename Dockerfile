FROM java:8
EXPOSE 7702
ADD nearshore-appsms-0.0.1-SNAPSHOT.jar nearshore-appsms-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Xmx312m","-Dspring.profiles.active=dev","-jar","nearshore-appsms-0.0.1-SNAPSHOT.jar"]