# messagewithjpa
- Exemple for gerate Message, Types email and SMS

Plugins integration
-
- spring-boot-starter-parent 1.5.3.RELEASE
- spring-boot-starter-web
- spring-boot-starter-test

Tecnologies
-
- Jackson, for map types
- Caching results
- API Restfull
- JPA

How Execute
-
- You will need set your  MAVEN_OPTS = -Xmx1024m -XX:MaxPermSize=128m
- Use Java 8
- Use the command mvn -DforkMode=never -DforkCount=0  clean install package test