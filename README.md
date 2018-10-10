# README #

Accompanying source code for blog entry at http://tech.asimio.net/2017/11/28/An-alternative-to-ThreadLocal-using-Spring.html

### Requirements ###

* Java 7 or 8
* Maven 3.3 or better

### Building and running from command line ###

```
mvn spring-boot:run
```

### Available endpoints ###

```
curl -v -H "X-TENANT-ID:tenant_1" http://localhost:8080/demo
curl -v -H "X-TENANT-ID:tenant_2" http://localhost:8080/demo
```

