# Top-up

### Add library ojdbc6 
````
 mvn org.apache.maven.plugins:maven-install-plugin:3.0.0-M1:install-file -Dfile=./ojdbc6.jar -DgroupId=com.dmlcc.jar -DartifactId=dml-ojdbc6 -Dversion=1.0-SNAPSHOT -Dpackaging=jar -DlocalRepositoryPath=C:/Users/i.sadeghi@office.dml.com/.m2/repository
````

### Build project with maven plugin
````
mvn clean install
````

### Swagger UI
````
http://localhost:8080/api/swagger-ui.html#/
````

### Settings
````
* Activate lombok annotations:
    - Settings (win: Ctrl + Alt + S) -> Plugins -> Search for "Lombok Plugin" /Click Browse repositories./Choose Lombok Plugin/Install/Restart IntelliJ
````