package Proje;

public class MemoryManager {
    // Attributes
    private int totalMemory;
    private int reservedForRealTime;
    private int availableMemory;

    // Constructor
    public MemoryManager(int totalMemory) {
        this.totalMemory = totalMemory;
        this.reservedForRealTime = 64;
        this.availableMemory = totalMemory; // Initially, all memory is available
    }

    // Method to check if memory is available for a process
    public boolean isMemoryAvailable(Process process) {
        return availableMemory >= process.getMemoryRequirement();
    }

    // Method to allocate memory to a process
    public void allocateMemory(Process process) {
        if (isMemoryAvailable(process)) {
            availableMemory -= process.getMemoryRequirement();
        } else {
            // Handle the case where there is not enough memory
            // This could involve waiting, rejecting the process, or other logic
        }
    }

    // Method to release memory from a process
    public void releaseMemory(Process process) {
        availableMemory += process.getMemoryRequirement();
    }

    // Getters and Setters
    public int getTotalMemory() {
        return totalMemory;
    }

    public int getAvailableMemory() {
        return availableMemory;
    }

    public void setAvailableMemory(int availableMemory) {
        this.availableMemory = availableMemory;
    }

    // Additional methods as needed, like displaying memory status
    public void displayMemoryStatus() {
        System.out.println("Total Memory: " + totalMemory + " MB");
        System.out.println("Available Memory: " + availableMemory + " MB");
    }
}
