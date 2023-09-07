# Welcome to PayTransact &middot; [![v 1.0.0](https://img.shields.io/badge/version-1.0.0-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)

PayTransact is a web application service to make and process transactions.

## Order of Contents:
- [Getting Started](#getting-started)
- [Dependencies](#dependencies)
- [Setting Up on your Local](#setting-up-on-your-local)
- [Licenses](#licenses)

## Getting Started :

Tools/Environments used as at writing:
- `Maven --version `
```Apache Maven 3.6.2 (40f52333136460af0dc0d7232c0dc0bcf0d9e117; 2019-08-27T16:06:16+01:00)
Maven home: /Users/ifejeremiah/apache-maven-3.6.2
Java version: 11.0.20.1, vendor: Homebrew, runtime: /usr/local/Cellar/openjdk@11/11.0.20.1/libexec/openjdk.jdk/Contents/Home
Default locale: en_NG, platform encoding: UTF-8
OS name: "mac os x", version: "12.6.8", arch: "x86_64", family: "mac" 
```


## Dependencies
This repository is a monorepo containing the following packages:
- [`Spring Boot Starter Web`](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [`Spring Boot DevTools`](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)
- [`Microsoft JDBC Driver For SQL Server`](https://mvnrepository.com/artifact/com.microsoft.sqlserver/mssql-jdbc) v11.2.0.jre11
- [`Spring Boot Starter Test`](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test)
- [`Lombok`](https://mvnrepository.com/artifact/org.projectlombok/lombok)
- [`ModelMapper`](https://mvnrepository.com/artifact/org.modelmapper/modelmapper) v2.3.9


## Setting Up on your Local :
- Switch to the master branch.
- Having Issues with connecting MSSQL Server to your Spring-Boot application? [check out this article](https://gist.github.com/Ifejeremiah/0dacda0c741c3947421f1dd8cb154762) .
- Modify the `application.properties` file from path `src\main\resources\application.properties` with your application properties to fit.
- All packages are located in path `src\main\java\com\interswitch\paytransact`.


## Licenses :
[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![AGPL License](https://img.shields.io/badge/License-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)