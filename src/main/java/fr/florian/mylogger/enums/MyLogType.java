package fr.florian.mylogger.enums;

import fr.florian.mylogger.utils.MyLoggerColor;

public enum MyLogType {

    DEBUG("DEBUG",      "DEBUG", MyLoggerColor.ANSI_WHITE, 1),
    INFO("INFO",        "INFO ", MyLoggerColor.ANSI_YELLOW, 2),
    ERROR("ERROR",      "ERROR", MyLoggerColor.ANSI_RED, 3),
    EXIT("EXIT",        "EXIT ", MyLoggerColor.ANSI_RED, 4),
    DONE("DONE",        "DONE ", MyLoggerColor.ANSI_GREEN, 5),
    FAIL("FAIL",        "FAIL ", MyLoggerColor.ANSI_RED, 6);

    private String name, prefix, color;
    private int level;

    MyLogType(String name, String prefix, String color, int level) {
        this.name = name;
        this.prefix = prefix;
        this.color = color;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrefixColored() {
        return getColor() + getPrefix() + MyLoggerColor.ANSI_RESET;
    }
}
