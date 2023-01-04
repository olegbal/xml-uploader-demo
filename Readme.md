### How to run

1) Requirements
    - Java 11
    - Maven or docker
    - Curl or postman

1) Run as docker image

```
echo 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 | docker login ghcr.io -u olegbalah@gmail.com --password-stdin
docker pull ghcr.io/olegbal/xml-uploader-demo/xml-uploader-demo
docker run -d --name xml-uploader-demo  -p 8390:8390 ghcr.io/olegbal/xml-uploader-demo/xml-uploader-demo
```

2) Run as maven project

```
mvn clean install
java -jar target/xml-uploader-demo-0.0.1-SNAPSHOT.jar
```

