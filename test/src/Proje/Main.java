package Proje;

import java.util.List;
public class Main {

	public static void main(String[] args) {
		String filePath = "./src/giris.txt"; // Replace with the path to your file
		
		Utility utility=new Utility();

        // Read the list of processes from the file
        List<Process> processes = utility.readProcessesFromFile(filePath);

        // Display all processes
        ProcessDisplay.printAllProcesses(processes);

	}

}
