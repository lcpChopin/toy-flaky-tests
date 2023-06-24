package in.natelev.toyflakytests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AsyncWaitThreadTest {
    @Test
    public void asyncWaitsLineUp() throws InterruptedException {
        AsyncWaitThread thread1 = new AsyncWaitThread();
        AsyncWaitThread thread2 = new AsyncWaitThread();
        thread1.start();
        thread2.start();
        Thread.sleep(500);
        assertTrue("all threads are not 1. thread1=" + thread1.getI() + ", thread2=" + thread2.getI(),
                thread1.getI() == 1 && thread1.getI() == thread2.getI());
    }
}
