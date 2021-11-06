## MyLogger

It's just a simple logger very easy to use and easy to add in your project.

Nice to have :
 - Implement TelegramAPI !
 - Add more prefix

###How to use

Very simple !

```java
public static void main(String[]args) {
    MyLogger.debug(String message);
    MyLogger.info(String message);
    MyLogger.error(String message);
    MyLogger.exit(String message);
}
```

###How to install

Install with maven like this

```xml
<dependency>
    <groupId>fr.florian.mylogger</groupId>
    <artifactId>MyLogger</artifactId>
    <version>1.0</version>
</dependency>
```

### Parameter

Change prefix with the name of your soft

`MyLogger.setPrefix(String name);`

Disable or enable color of prefix

`MyLogger.setPrintColored(boolean printColored);`