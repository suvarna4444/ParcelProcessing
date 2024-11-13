package main;
public class Log {
    private static Log instance;
    private StringBuffer logBuffer;

    // Private constructor to prevent instantiation
    private Log() {
        logBuffer = new StringBuffer();
    }

    // Method to get the single instance of the Log class
    public static synchronized Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    // Method to add an event to the log
    public void addEvent(String event) {
        logBuffer.append(event).append("\n");
    }

    // Alias for addEvent to match the usage in AppView
    public void addLog(String event) {
        addEvent(event);
    }

    // Method to get the log contents
    public String getLog() {
        return logBuffer.toString();
    }

    // Method to clear the log
    public void clearLog() {
        logBuffer.setLength(0);
    }

    // Method to write the log to a file
    public void writeLogToFile(String filename) {
        try (java.io.FileWriter writer = new java.io.FileWriter(filename)) {
            writer.write(logBuffer.toString());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    // Method to represent the log as a string
    @Override
    public String toString() {
        return logBuffer.toString();
    }
}
