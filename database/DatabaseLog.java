package database;

import java.util.Date;

/**
 * Created by Alex on 8/30/2015.
 */
public class DatabaseLog {
    public static boolean debug = true;

    public static void log(String message, String className) {
        if (debug)
            System.out.println(String.format("%s: %s", className, message));
    }
}
