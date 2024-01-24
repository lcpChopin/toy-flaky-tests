package com.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

public class FileLogWriter {
    FileLogWriter() throws IOException {
        Files.deleteIfExists(Paths.get("log.txt"));
        Files.createFile(Paths.get("log.txt"));
    }

    public void log(String msg) throws IOException {
        msg = msg + "\n";
        Files.write(Paths.get("log.txt"), msg.getBytes(), StandardOpenOption.APPEND);
    }

    public void warn(String msg) throws IOException {
        log("[WARN] " + msg);
    }

    public String getLogs() throws IOException {
        return new String(Files.readAllBytes(Paths.get("log.txt")));
    }
}
