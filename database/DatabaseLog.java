package database;

public class DatabaseLog {
    public static boolean debug = true;

    public static void log(String message, String className) {
        if (debug)
            System.out.println(String.format("%s: %s", className, message));
    }
}
