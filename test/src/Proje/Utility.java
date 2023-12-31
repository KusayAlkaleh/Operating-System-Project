package Proje;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utility {
	private int processIdCounter=0;
    // Method to read processes from a file
    public List<Process> readProcessesFromFile(String filePath) {
        List<Process> processes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Process process = parseProcessLine(line);
                if (process != null) {
                    processes.add(process);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return processes;
    }

    // Method to parse a line of text into a Process object
    private Process parseProcessLine(String line) {
        try {
        	
            String[] parts = line.split(",\\s*"); // Regex used to split on comma and optional whitespace
            int processId = processIdCounter++;
            int arrivalTime = Integer.parseInt(parts[0]);
            int priority = Integer.parseInt(parts[1]);
            int cpuTimeRequired = Integer.parseInt(parts[2]);
            int memoryRequirement = Integer.parseInt(parts[3]);
            // The number of resources is the fifth value
            int printerCount = Integer.parseInt(parts[4]);
            int scannerCount = Integer.parseInt(parts[5]);
            // The last two values are assumed to be the modem and cd counts
            int modemCount = Integer.parseInt(parts[6]);
            int cdDriveCount = Integer.parseInt(parts[7]);

            // The boolean flags for printers and scanners are set to true if resourceCount
            // is more than zero
            boolean requiresPrinter = printerCount > 0;
            boolean requiresScanner = scannerCount > 0; // Adjust if you have separate counts for printers and scanners
            boolean requiresModem = modemCount > 0;
            boolean requiresCDDrive = cdDriveCount > 0;

            // Returning a new Process object with the parsed values
            return new Process(
                    processId, arrivalTime, priority, cpuTimeRequired, memoryRequirement,
                    printerCount, // For printerCount if you have only one count for resources
                    scannerCount, // For scannerCount if you have only one count for resources
                    modemCount, cdDriveCount,
                    requiresPrinter, requiresScanner, requiresModem, requiresCDDrive);
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
            e.printStackTrace();
            return null;
        }
    }

}
