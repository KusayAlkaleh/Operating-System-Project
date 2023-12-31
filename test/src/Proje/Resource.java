package Proje;

public class Resource {
    // Attributes for resource availability
    private int printers= 2;
    private int scanners= 1;
    private int modems= 1;
    private int cdDrives= 2;
    private int memory = 1024; // MBytes

    // Constructor
    public Resource(int printers, int scanners, int modems, int cdDrives,int memory) {
        this.printers = printers;
        this.scanners = scanners;
        this.modems = modems;
        this.cdDrives = cdDrives;
        this.memory = memory;
    }

    // Method to check if resources are available for a process
    public boolean areResourcesAvailable(Process process) {
        return process.requiresPrinter() && printers > 0 ||
                process.requiresScanner() && scanners > 0 ||
                process.requiresModem() && modems > 0 ||
                process.requiresCDDrive() && cdDrives > 0||
                process.getMemoryRequirement()>0 && memory > 0;
    }

    // Method to allocate resources to a process
    public void allocateResources(Process process) {
        if (process.requiresPrinter())
            printers--;
        if (process.requiresScanner())
            scanners--;
        if (process.requiresModem())
            modems--;
        if (process.requiresCDDrive())
            cdDrives--;
    }

    // Method to release resources from a process
    public void releaseResources(Process process) {
        if (process.requiresPrinter())
            printers++;
        if (process.requiresScanner())
            scanners++;
        if (process.requiresModem())
            modems++;
        if (process.requiresCDDrive())
            cdDrives++;
    }

    public int getPrinters() {
        return printers;
    }

    public void setPrinters(int printers) {
        this.printers = printers;
    }

    public int getScanners() {
        return scanners;
    }

    public void setScanners(int scanners) {
        this.scanners = scanners;
    }

    public int getModems() {
        return modems;
    }

    public void setModems(int modems) {
        this.modems = modems;
    }

    public int getCdDrives() {
        return cdDrives;
    }

    public void setCdDrives(int cdDrives) {
        this.cdDrives = cdDrives;
    }

    // Additional methods as needed, like displaying resource status
    public void displayResourceStatus() {
        System.out.println("Available Printers: " + printers);
        System.out.println("Available Scanners: " + scanners);
        System.out.println("Available Modems: " + modems);
        System.out.println("Available CD Drives: " + cdDrives);
    }
}
