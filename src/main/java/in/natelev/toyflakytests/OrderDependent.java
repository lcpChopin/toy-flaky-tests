package in.natelev.toyflakytests;

public class OrderDependent {
    private static int i = 0;

    public static void resetI() {
        i = 0;
    }

    public static void incrementI() {
        i++;
    }

    public static void setI(int newI) {
        i = newI;
    }

    public static int getI() {
        return i;
    }
}
