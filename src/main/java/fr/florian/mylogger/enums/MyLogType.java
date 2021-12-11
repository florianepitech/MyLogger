package fr.florian.mylogger.enums;

import fr.florian.mylogger.utils.MyLoggerColor;

public enum MyLogType {

    TRACE("TRACE", "TRACE", MyLoggerColor.WHITE, 1),
    DEBUG("DEBUG", "DEBUG", MyLoggerColor.BLUE, 2),
    INFO("INFO", "INFO ", MyLoggerColor.YELLOW, 3),
    WARN("WARN", "WARN ", MyLoggerColor.RED, 4),
    ERROR("ERROR", "ERROR", MyLoggerColor.RED, 5),
    FATAL("FATAL", "FATAL", MyLoggerColor.RED_BOLD, 6);

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

    public int getLevel() {
        return level;
    }

    public String getPrefixColored() {
        return getColor() + getPrefix() + MyLoggerColor.RESET;
    }
}
