package in.natelev.toyflakytests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import in.natelev.toyflakytests.SystemTime.Message;

public class SystemTimeTest {
    @Test
    public void bothMessagesAreEqual() {
        Message msg = SystemTime.getMessage("message");
        Message replicaMsg = SystemTime.getReplicaMessage("message");
        assertTrue("Both messages are not equal", msg.equals(replicaMsg));
    }
}
