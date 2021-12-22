package fr.florian.tracex.priority;

import fr.florian.tracex.utils.ConsoleColors;

import java.util.Arrays;
import java.util.List;

public enum Priority {

    ALL("ALL", ConsoleColors.BLACK, 0),
    TRACE("TRACE", ConsoleColors.WHITE, 100),
    DEBUG("DEBUG", ConsoleColors.BLUE, 200),
    INFO("INFO", ConsoleColors.YELLOW, 300),
    WARN("WARN", ConsoleColors.RED, 400),
    ERROR("ERROR", ConsoleColors.RED, 500),
    FATAL("FATAL", ConsoleColors.RED_BOLD, 600);

    private String name, prefix, color;
    private int level;

    Priority(String name, String color, int level) {
        this.name = name;
        this.prefix = String.format("%-5s", name);
        this.color = color;
        this.level = level;
    }

    public static Priority getPriority(String name) {
        List<Priority> priorities = Arrays.asList(Priority.values());
        for (Priority priority : priorities) {
            if (priority.getName().equals(name)) return priority;
        }
        return null;
    }

    /*
     *      CONVERTER
     */

    public CustomPriority toCustomPriority() {
        return new CustomPriority(getName(), getColor(), getLevel());
    }

    /*
     *      GETTER
     */

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getColor() {
        return color;
    }

    public int getLevel() {
        return level;
    }

    public String getPrefixColored() {
        return getColor() + getPrefix() + ConsoleColors.RESET;
    }
}
