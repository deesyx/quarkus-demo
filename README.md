## Run
1. set up db: docker-compose up -d
2. run in native:  
- build native package: ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
- build native image: docker build -f src/main/docker/Dockerfile.native -t quarkus/quarkus-demo .
- run native: docker run -i --rm -p 8080:8080 quarkus/quarkus-demo

### POI failed in native
https://github.com/quarkusio/quarkus/issues/10891
https://github.com/oracle/graal/issues/2187
https://github.com/oracle/graal/issues/3191
https://stackoverflow.com/questions/59178299/using-apache-poi-with-graalvm-native-image-classcastexception-from-xmlbeans

### How to use native image agent
https://stackoverflow.com/questions/64450394/how-to-use-the-graalvm-native-image-agent-with-quarkus
https://stackoverflow.com/questions/67046821/how-do-i-run-the-graalvm-native-image-tracing-agent-from-a-spring-native-gradle
https://www.graalvm.org/reference-manual/native-image/Agent/

### Native image agent not found
https://github.com/oracle/graal/issues/1057
