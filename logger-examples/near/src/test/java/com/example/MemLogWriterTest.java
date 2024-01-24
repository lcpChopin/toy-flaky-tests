package com.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MemLogWriterTest {
    @Test // polluter
    public void testLogsWithNewline() {
        MemLogWriter.get().log("Test");
        assertTrue(MemLogWriter
                .get().getLogs().endsWith("\n"));
    }

    @Test // victim
    public void testWarnStartsWithWarn() {
        MemLogWriter.get().warn("Test");
        assertTrue(MemLogWriter
                .get().getLogs().startsWith("[WARN]"));
    }

    @Test // near-cleaner
    public void testLogStartupTime() {
        long time = System.currentTimeMillis();
        MemLogWriter.get().logStartupTime();
        assertTrue(MemLogWriter.get().getLogs()
                .startsWith("Log Started: " + time));
    }
}
