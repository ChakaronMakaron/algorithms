package helper.classes;

public class PrettyBoolean {
    
    public static boolean all(boolean... values) {
        for (boolean val : values) {
            if (!val) return false;
        }
        return true;
    }

    public static boolean not(boolean a) {
        return !a;
    }

    public static boolean any(boolean... values) {
        for (boolean val : values) {
            if (val) return true;
        }
        return false;
    }
}
