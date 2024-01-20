package in.natelev.toyflakytests;

public class AsyncWaitThread extends Thread {
    private int i = 0;

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.i = 1;
    }

    public int getI() {
        return i;
    }
}
