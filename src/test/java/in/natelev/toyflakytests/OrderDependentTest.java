package in.natelev.toyflakytests;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderDependentTest {
    @Test
    public void polluter() {
        // this test should pass
        // remove it when comparing 'polluter&victim' to only 'victim'
        OrderDependent.setI(100);
        assertEquals(100, OrderDependent.getI());
    }

    @Test
    public void victim() {
        // assumes that OrderDependent.i is 0 at start
        OrderDependent.incrementI();
        assertEquals(1, OrderDependent.getI());
    }
}
