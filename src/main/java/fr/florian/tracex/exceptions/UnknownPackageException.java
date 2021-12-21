package fr.florian.tracex.exceptions;

public class UnknownPackageException extends Exception {

    private String packageName;

    public UnknownPackageException(String packageName) {
        super("unknown package: " + packageName);
        this.packageName = packageName;
    }

    /*
     *      GETTER
     */

    public String getPackageName() {
        return packageName;
    }
}
