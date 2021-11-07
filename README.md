## MyLogger

It's just a simple logger very easy to use and easy to add in your project.

Nice to add in future update :
 - Implement TelegramAPI !
 - Save log in database

### How to use

Very simple !

```java
MyLogger.debug("Test !");
MyLogger.info("Test !");
MyLogger.error("Test !");
MyLogger.exit("Test !");
```

Render

```
00000001 [LOGGER] [DEBUG] 06/11/2021 16h10:12.234 : Test !
00000002 [LOGGER] [INFO]  06/11/2021 16h10:12.306 : Test !
00000003 [LOGGER] [ERROR] 06/11/2021 16h10:12.306 : Test !
00000004 [LOGGER] [EXIT]  06/11/2021 16h10:12.306 : Test !
```


### How to install

Install with maven like that after cloned the repositorie

```shell
mvn install
```

Add this into your pom.xml

```xml
<dependency>
    <groupId>fr.florian.mylogger</groupId>
    <artifactId>MyLogger</artifactId>
    <version>1.0</version>
</dependency>
```

### Parameter

Change prefix with the name of your program

```java
MyLogger.setPrefix(String name);
```

Disable or enable color for log type

```java
MyLogger.setPrintColored(boolean printColored);
```

Change the format of date

```java
MyLogger.setDateFormat(String dateFormat);
```

Change zone id for the formatted date

```java
MyLogger.setZoneId(ZoneId zoneId);
```

And many other little and easy parameters...