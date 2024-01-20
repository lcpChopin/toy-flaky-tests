package in.natelev.toyflakytests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// both tests will pass individually, but fail when running polluter first
// when cleaner is run between polluter and victim, all tests pass
// when nearCleaner is run between polluter and victim, victim still fails
public class OrderDependentTest {
    @Test
    public void polluter() {
        int expected = 100;

        OrderDependent.setI(100);

        assertEquals(expected, OrderDependent.getI());
    }

    @Test
    public void victim() {
        // assumes that OrderDependent.i is 0 at start
        int expected = 1;

        OrderDependent.incrementI();

        assertEquals(expected, OrderDependent.getI());
    }

    @Test
    public void cleaner() {
        int expected = 0;

        OrderDependent.resetI();

        assertEquals(expected, OrderDependent.getI());
    }

    @Test
    public void nearCleaner() {
        // this method _almost_ cleans up after the polluter.
        // like the cleaner, it runs resetI(),
        // but it then pollutes i by setting it to 10
        int expected = 10;

        OrderDependent.resetI();

        OrderDependent.setI(10);

        assertEquals(expected, OrderDependent.getI());
    }
}
