package Proje;

public class Process {
    // Attributes
    private int processId;
    private int arrivalTime;
    private int priority;
    private int cpuTimeRequired;
    private int memoryRequirement;
    private boolean requiresPrinter;
    private boolean requiresScanner;
    private boolean requiresModem;
    private boolean requiresCDDrive;
    private String state; // Could be "running", "waiting", "suspended", etc.
    private boolean isCompleted;
    // Additional attributes for resource counts
    private int printerCount;
    private int scannerCount;
    private int modemCount;
    private int cdDriveCount;

    // Error handling
    private boolean hasError;
    private String errorMessage;

    // Constructor
    public Process(int processId, int arrivalTime, int priority, int cpuTimeRequired,
            int memoryRequirement, int printerCount, int scannerCount, int modemCount,
            int cdDriveCount, boolean requiresPrinter, boolean requiresScanner,
            boolean requiresModem, boolean requiresCDDrive) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.cpuTimeRequired = cpuTimeRequired;
        this.memoryRequirement = memoryRequirement;
        this.requiresPrinter = requiresPrinter;
        this.requiresScanner = requiresScanner;
        this.requiresModem = requiresModem;
        this.requiresCDDrive = requiresCDDrive;
        this.state = "waiting"; // Default state

        // Existing assignments...
        this.printerCount = printerCount;
        this.scannerCount = scannerCount;
        this.modemCount = modemCount;
        this.cdDriveCount = cdDriveCount;
        // Assume no error initially
        this.hasError = false;
        this.errorMessage = "";
    }

    // Mark the process as completed
    public void complete() {
        this.isCompleted = true;
        this.setState("completed");
    }

    // Decrease the CPU time required by the process
    public void decrementCpuTime(int timeQuantum) {
        this.cpuTimeRequired -= timeQuantum;
        if (this.cpuTimeRequired <= 0) {
            this.complete();
        }
    }

    // Method to get the status of the process for display
    public String getStatus() {
        if (hasError) {
            return "ERROR"; // You can customize this message based on your needs
        } else if (isCompleted) {
            return "COMPLETED";
        } else {
            return state; // This will return "running", "waiting", "suspended", etc.
        }
    }

    // Error handling methods
    public boolean hasError() {
        return hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setError(String errorMessage) {
        this.hasError = true;
        this.errorMessage = errorMessage;
    }

    // Resource count getters
    public int getPrinterCount() {
        return printerCount;
    }

    public int getScannerCount() {
        return scannerCount;
    }

    public int getModemCount() {
        return modemCount;
    }

    public int getCdDriveCount() {
        return cdDriveCount;
    }

    // Getters
    public int getProcessId() {
        return processId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getCpuTimeRequired() {
        return cpuTimeRequired;
    }

    public int getMemoryRequirement() {
        return memoryRequirement;
    }

    public boolean requiresPrinter() {
        return requiresPrinter;
    }

    public boolean requiresScanner() {
        return requiresScanner;
    }

    public boolean requiresModem() {
        return requiresModem;
    }

    public boolean requiresCDDrive() {
        return requiresCDDrive;
    }

    // Check if the process has completed
    public boolean isCompleted() {
        return isCompleted;
    }

    public String getState() {
        return state;
    }

    // Setters
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setState(String state) {
        this.state = state;
    }

    // Other methods as required, like toString() for easy display
    @Override
    public String toString() {
        return "Process{" +
                "ID=" + processId +
                ", Arrival=" + arrivalTime +
                ", Priority=" + priority +
                ", CPU Time=" + cpuTimeRequired +
                ", Memory=" + memoryRequirement +
                ", Printer=" + requiresPrinter +
                ", Scanner=" + requiresScanner +
                ", Modem=" + requiresModem +
                ", CD Drive=" + requiresCDDrive +
                ", State='" + state + '\'' +
                '}';
    }
}
