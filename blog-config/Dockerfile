FROM java:8


ADD ./target/blog-config.jar /app/
CMD ["java","-Xmx200m","-jar","/app/blog-config.jar"]

EXPOSE 8888