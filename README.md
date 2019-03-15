# Robo rally
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9551cfb1be2c4646a922e5e798830533)](https://www.codacy.com/app/inf112-Bender/Bender?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=inf112-v19/Bender&amp;utm_campaign=Badge_Grade)

## Build and run
* Execute `mvn clean install` in the root folder of the project
* This will create three jar files in *target/*
   * *libgdx-app.jar*: the libgdx application
   * *server-app.jar*: the server application
   * *client-app.jar*: the client application
* To run a jar file type `java -jar sample.jar`

## Tests
* Execute `mvn test` in the root folder of the project to run all tests
* The server tests has some os-delay problems and must be run manually (mvn will skip these tests)

## Wiki
* [How to use maven](https://github.com/inf112-v19/Bender/wiki/How-to-use-maven)
* [Server client specification](https://github.com/inf112-v19/Bender/wiki)
