package in.natelev.toyflakytests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.Test;

public class ExternalODLoggerTest {
    // polluter
    @Test
    public void testStringLog() {
        ExternalODLogger logger = new ExternalODLogger();
        logger.log("polluter");

        assertEquals("polluter\n", logger.readLogs());
    }

    // victim
    @Test
    public void testDefaultEmpty() {
        ExternalODLogger logger = new ExternalODLogger();
        assertEquals("", logger.readLogs());
    }

    // cleaner
    @Test
    public void testClearLogs() {
        ExternalODLogger logger = new ExternalODLogger();
        logger.log("polluter");
        logger.clearLogs();

        assertEquals("", logger.readLogs());
    }

    @AfterClass
    public static void deleteLogTxt() {
        try {
            Files.deleteIfExists(Paths.get("log.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
