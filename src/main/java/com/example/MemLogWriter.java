package com.example;

public class MemLogWriter {
    private static // singleton pattern
    MemLogWriter instance = new MemLogWriter();
    String logs;

    private MemLogWriter() {
        logs = "";
    }

    public void log(String msg) {
        logs += msg + "\n";
    }

    public void warn(String msg) {
        log("[WARN] " + msg);
    }

    public void resetLogs() {
        logs = "";
    }

    public void logStartupTime() {
        resetLogs();
        log("Log Started: " + System.currentTimeMillis());
    }

    public String getLogs() {
        return logs;
    }

    public static MemLogWriter get() {
        return instance;
    }
}
