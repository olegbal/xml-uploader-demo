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

There is an external lib used for dynamic search feature
https://github.com/jirutka/rsql-parser

Examples

Equal to : ```==```

Not equal to : ```!=```

Less than : ```=lt=``` or ```<```

Less than or equal to : ```=le=``` or ```⇐```

Greater than operator : ```=gt=``` or ```>```

Greater than or equal to : ```=ge=``` or ```>=```

In : ```=in=```

Not in : ```=out=```

```
- name=="Kill Bill";year=gt=2003
- name=="Kill Bill" and year>2003
- genres=in=(sci-fi,action);(director=='Christopher Nolan',actor==*Bale);year=ge=2000
- genres=in=(sci-fi,action) and (director=='Christopher Nolan' or actor==*Bale) and year>=2000
- director.lastName==Nolan;year=ge=2000;year=lt=2010
- director.lastName==Nolan and year>=2000 and year<2010
- genres=in=(sci-fi,action);genres=out=(romance,animated,horror),director==Que*Tarantino
- genres=in=(sci-fi,action) and genres=out=(romance,animated,horror) or director==Que*Tarantino
```