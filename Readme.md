### Requirements

    - Java 11
    - Maven or docker
    - Curl or postman

### How to run

1) Run as docker image

```
echo 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 | docker login ghcr.io -u olegbalah@gmail.com --password-stdin
docker pull ghcr.io/olegbal/xml-uploader-demo/xml-uploader-demo
docker run -d --name xml-uploader-demo  -p 8983:8983 ghcr.io/olegbal/xml-uploader-demo/xml-uploader-demo
```

2) Run as maven project

```
mvn clean install
java -jar target/xml-uploader-demo-0.0.1-SNAPSHOT.jar
```

### How to use

1) Import in postman postman_collection.json

