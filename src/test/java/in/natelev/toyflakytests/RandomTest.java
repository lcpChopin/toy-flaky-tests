package in.natelev.toyflakytests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RandomTest {
    @Test
    public void randomBooleanReturnsTrue() {
        assertTrue("Random boolean returned was not true", Random.randomBoolean());
    }
}
