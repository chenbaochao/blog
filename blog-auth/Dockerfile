FROM java:8


ADD ./target/blog-auth.jar /app/
CMD ["java","-Xmx200m","-jar","/app/blog-auth.jar"]


EXPOSE 5000