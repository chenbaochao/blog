FROM java:8


ADD ./target/blog-admin.jar /app/
CMD ["java","-Xmx200m","-jar","/app/blog-admin.jar"]


EXPOSE 7000