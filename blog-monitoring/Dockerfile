FROM java:8

ADD ./target/blog-monitoring.jar /app/
CMD ["java","-Xmx200m","-jar","/app/blog-monitoring.jar"]

EXPOSE 8080