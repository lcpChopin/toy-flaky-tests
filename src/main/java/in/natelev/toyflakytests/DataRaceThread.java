package in.natelev.toyflakytests;

public class DataRaceThread extends Thread {
    private static int i = 0;

    public static int getI() {
        return i;
    }

    public static void incrementI() {
        i++;
    }

    @Override
    public void run() {
        incrementI();
    }
}
