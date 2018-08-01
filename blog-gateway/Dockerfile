FROM java:8

ADD ./target/blog-gateway.jar /app/
CMD ["java","-Xmx200m","-jar","/app/blog-gateway.jar"]

EXPOSE 4000