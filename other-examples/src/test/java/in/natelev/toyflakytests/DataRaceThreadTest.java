package in.natelev.toyflakytests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DataRaceThreadTest {
    @Test
    public void dataRaceDoesNotOccur() throws InterruptedException {
        for (int i = 0; i < 1_000; i++) {
            new DataRaceThread().start();
            DataRaceThread.incrementI();
        }
        assertEquals("Data race occurred", 1999, DataRaceThread.getI());
    }
}
