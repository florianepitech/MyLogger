package fr.florian.tracex.priority;

import fr.florian.tracex.utils.ConsoleColors;

public class CustomPriority {

    private String name, prefix;
    private String consoleColors;
    private int level;

    public CustomPriority(String name, String consoleColor, int level) {
        this.name = name;
        this.prefix = String.format("%-5s", name);
        this.consoleColors = consoleColor;
        this.level = level;
    }

    /*
     *      GETTER & SETTER
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getConsoleColors() {
        return consoleColors;
    }

    public void setConsoleColors(String consoleColors) {
        this.consoleColors = consoleColors;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPrefixColored() {
        return getConsoleColors() + getPrefix() + ConsoleColors.RESET;
    }

}
