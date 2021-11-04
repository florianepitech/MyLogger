enum MyLoggerType {

    DEBUG("DEBUG", "[DEBUG]", MyLoggerColor.ANSI_WHITE + "[DEBUG]" + MyLoggerColor.ANSI_RESET),
    INFO("INFO", "[INFO] ",MyLoggerColor.ANSI_YELLOW + "[INFO] " + MyLoggerColor.ANSI_RESET),
    ERROR("ERROR", "[ERROR]", MyLoggerColor.ANSI_RED + "[ERROR]" + MyLoggerColor.ANSI_RESET),
    EXIT("EXIT", "[EXIT] ",MyLoggerColor.ANSI_RED + "[EXIT] " + MyLoggerColor.ANSI_RESET);

    private String name, prefix, prefixTerminal;
    private boolean saveInDB;

    MyLoggerType(String name, String prefix, String prefixTerminal) {
        this.name = name;
        this.prefix = prefix;
        this.prefixTerminal = prefixTerminal;
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

    public String getPrefixTerminal() {
        return prefixTerminal;
    }

    public void setPrefixTerminal(String prefixTerminal) {
        this.prefixTerminal = prefixTerminal;
    }

    public boolean isSaveInDB() {
        return saveInDB;
    }

    public void setSaveInDB(boolean saveInDB) {
        this.saveInDB = saveInDB;
    }
}
