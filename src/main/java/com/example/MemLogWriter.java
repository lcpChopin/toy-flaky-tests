package com.example;

public class MemLogWriter {
    String logs;

    MemLogWriter() {
        logs = "";
    }

    public void log(String msg) {
        logs += msg + "\n";
    }

    public void warn(String msg) {
        logs += "[WARN] " + msg + "\n";
    }

    private void resetLogs() {
        logs = "";
    }

    public void logStartupTime() {
        resetLogs();
        log("Logging Enabled: " + System.currentTimeMillis());
    }

    public String getLogs() {
        return logs;
    }
}
