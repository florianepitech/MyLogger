package fr.florian.mylogger;

enum MyLogType {

    DEBUG("DEBUG",      "[DEBUG]   ", MyLoggerColor.ANSI_WHITE +    "[DEBUG]   " + MyLoggerColor.ANSI_RESET),
    INFO("INFO",        "[INFO]    ",MyLoggerColor.ANSI_YELLOW +    "[INFO]    " + MyLoggerColor.ANSI_RESET),
    ERROR("ERROR",      "[ERROR]   ", MyLoggerColor.ANSI_RED +      "[ERROR]   " + MyLoggerColor.ANSI_RESET),
    EXIT("EXIT",        "[EXIT]    ",MyLoggerColor.ANSI_RED +       "[EXIT]    " + MyLoggerColor.ANSI_RESET),
    SUCCESS("SUCCESS",  "[SUCCESS] ",MyLoggerColor.ANSI_GREEN +     "[SUCCESS] " + MyLoggerColor.ANSI_RESET),
    FAIL("FAIL",        "[FAIL]    ",MyLoggerColor.ANSI_RED +       "[FAIL]    " + MyLoggerColor.ANSI_RESET);

    private String name, prefix, prefixColored;
    private boolean saveInDB;

    MyLogType(String name, String prefix, String prefixTerminal) {
        this.name = name;
        this.prefix = prefix;
        this.prefixColored = prefixTerminal;
    }

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

    public String getPrefixColored() {
        return prefixColored;
    }

    public void setPrefixColored(String prefixColored) {
        this.prefixColored = prefixColored;
    }

    public boolean isSaveInDB() {
        return saveInDB;
    }

    public void setSaveInDB(boolean saveInDB) {
        this.saveInDB = saveInDB;
    }
}
