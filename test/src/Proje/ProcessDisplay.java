package Proje;

import java.util.List;

public class ProcessDisplay {

    // Method to print the header row
    public static void printHeader() {
        System.out.printf("%-3s %-6s %-7s %-3s %-6s %-3s %-3s %-5s %-2s %-7s%n",
                "Pid", "varış", "öncelik", "cpu", "MBytes", "prn", "scn", "modem", "cd", "status");
        System.out.println("======================================================================");
    }

    // Method to print each process row
    public static void printProcess(Process process) {
    	if(process.getCpuTimeRequired()>20)
    	{
    		System.out.println(process.getProcessId()+"\tHATA - proses zaman aşımı (20 sn de tamamlanamadı)");
    	}
    	else if(process.getPriority()==0 &&process.getMemoryRequirement()>64)
    	{
    		System.out.println(process.getProcessId()+"\tHATA - Gerçek zamanlı proses (64MB)  tan daha fazla bellek talep ediyor - proses silindi");
    	}
    	else if(process.getMemoryRequirement()>960)
    	{
    		System.out.println(process.getProcessId()+"\tHATA - proses (960MB)  tan daha fazla bellek talep ediyor - proses silindi");
    	}
    	else
    	{
    		System.out.printf("%d   \t%s\t   %d\t\t%d\t%d\t%d\t%d\t%d\t%d\t%s\n",
                    process.getProcessId(),
                    process.getArrivalTime(),
                    process.getPriority(),
                    process.getCpuTimeRequired(),
                    process.getMemoryRequirement(),
                    process.getPrinterCount() ,
                    process.getScannerCount() ,
                    process.getModemCount() ,
                    process.getCdDriveCount() ,
                    process.getState()
            );
    	}
    	
    	
        
    }

    // Method to print all processes
    public static void printAllProcesses(List<Process> processes) {
        printHeader();
        for (Process process : processes) {
            printProcess(process);
        }
    }
}
