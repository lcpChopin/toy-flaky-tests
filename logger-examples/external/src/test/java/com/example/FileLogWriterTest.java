package com.example;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

public class FileLogWriterTest {
    private static FileLogWriter logWriter;

    @BeforeClass
    public static void setUp() {
        try {
            logWriter = new FileLogWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // polluter
    @Test
    public void testLogsWithNewline() throws IOException {
        logWriter.log("Test");

        assertTrue(logWriter.getLogs().endsWith("\n"));
    }

    // victim
    @Test
    public void testWarnStartsWithWarn() throws IOException {
        logWriter.warn("Test");

        assertTrue(logWriter.getLogs().startsWith("[WARN]"));
    }
}
