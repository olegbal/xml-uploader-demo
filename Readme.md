### Requirements

    - Java 11
    - Maven or docker
    - Postman or another client for sending requests

### How to run

1) Run as docker image

```
docker run -d --name xml-uploader-demo  -p 8983:8983 olegbal/xml-uploader-demo
```

2) Run as maven project

```
./mvnw clean install spring-boot:run
```

### How to use

1) Import in postman postman_collection.json

