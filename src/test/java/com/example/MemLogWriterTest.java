package com.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MemLogWriterTest {
    private static MemLogWriter logWriter = new MemLogWriter();

    // polluter
    @Test
    public void testLogsWithNewline() {
        logWriter.log("Test");

        assertTrue(logWriter.getLogs().endsWith("\n"));
    }

    // victim
    @Test
    public void testWarnStartsWithWarn() {
        logWriter.warn("Test");

        assertTrue(logWriter.getLogs().startsWith("[WARN]"));
    }

    // near-cleaner
    @Test
    public void testLogStartupTime() {
        long time = System.currentTimeMillis();
        logWriter.logStartupTime();

        assertTrue(logWriter.getLogs().startsWith("Logging Enabled: " + time));
    }
}
