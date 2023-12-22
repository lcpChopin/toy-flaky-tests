package in.natelev.toyflakytests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.stream.Collectors;

public class ExternalODLogger {
    // writes msg to log.txt
    void log(String msg) {
        try (FileWriter file = new FileWriter("log.txt", true)) {
            file.write(msg + "\n");
        } catch (Exception e) {
            System.out.println("Error logging to log.txt.");
        }
    }

    // reads log.txt and return its contents
    String readLogs() {
        try (BufferedReader reader = new BufferedReader(new FileReader("log.txt"))) {
            return reader.lines().map(line -> line + "\n").collect(Collectors.joining());
        } catch (Exception e) {
            System.out.println("Error reading log.txt.");
        }
        return "";
    }

    // clears log.txt
    void clearLogs() {
        try {
            new FileWriter("log.txt").close();
        } catch (Exception e) {
            System.out.println("Error clearing log.txt.");
        }
    }
}
