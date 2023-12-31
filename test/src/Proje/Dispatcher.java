package Proje;

public class Dispatcher {
    private Queue realTimeQueue;
    private Queue userPriorityQueue;
    private int currentTimeTick;
    private Resource resourceManager;
    private MemoryManager memoryManager;

    // Constructor
    public Dispatcher(int totalMemory, int printers, int scanners, int modems, int cdDrives,int memory) {
        this.realTimeQueue = new Queue("Real-Time");
        this.userPriorityQueue = new Queue("User Priority");
        this.currentTimeTick = 0;

        // Initialize resourceManager with the available resources
        this.resourceManager = new Resource(printers, scanners, modems, cdDrives,memory);

        // Initialize memoryManager with the total memory available
        this.memoryManager = new MemoryManager(totalMemory);
    }

    // Method to dispatch processes
    public void dispatch() {
        while (!allQueuesAreEmpty()) {
            checkArrivals(); // Check for new arrivals to add to queues
            executeProcesses();
            currentTimeTick++;
        }
    }

    private void adjustPriority(Process process) {
        // Increase priority value to lower the priority (assuming higher number means
        // lower priority)
        int newPriority = process.getPriority() + 1;
        process.setPriority(newPriority);
        System.out.println("Process " + process.getProcessId() + " priority adjusted to: " + newPriority);
    }

    // Method to check for new arrivals
    private void checkArrivals() {
        // Implement logic to add new processes to the queues based on arrival time and
        // priority
    }

    // Method to execute processes
    private void executeProcesses() {
        // Execute Real-Time (High Priority) Processes in FCFS Order
        while (!realTimeQueue.isEmpty()) {
            Process process = realTimeQueue.removeProcess();
            runProcess(process);
            // Implement logic for running the process and check for completion
        }

        // Execute User Priority Processes using Feedback Scheduler
        if (userPriorityQueue.isEmpty()) {
            return; // No user processes to run
        }

        // Implement Feedback Scheduling Logic here
        // ...

        // For Round Robin, you might have a time quantum for each process
        int timeQuantum = 1; // 1 second or appropriate time slice
        Process currentProcess = userPriorityQueue.peekProcess();
        while (currentProcess != null) {
            runProcessForTimeQuantum(currentProcess, timeQuantum);
            // Implement logic for running the process for a time slice
            // Move the process to the end of the queue if not completed

            currentProcess = userPriorityQueue.peekProcess(); // Get the next process
        }
    }

    private void runProcess(Process process) {
        // Check and allocate resources
        if (resourceManager.areResourcesAvailable(process)) {
            resourceManager.allocateResources(process);

            // Simulate running the process
            System.out.println("Running process: " + process.getProcessId());
            process.setState("running");

            // Assuming the process runs to completion
            process.complete();
            System.out.println("Process " + process.getProcessId() + " completed.");

            // Release resources
            resourceManager.releaseResources(process);
        } else {
            System.out.println("Not enough resources for process: " + process.getProcessId());
        }
    }

    // Mixed scheduling (Feedback + Round Robin)
    private void mixedSchedule() {
        final int timeQuantum = 1; // For round-robin
        while (!userPriorityQueue.isEmpty()) {
            Process process = userPriorityQueue.peekProcess();
            userPriorityQueue.removeProcess();

            if (isHighPriority(process)) {
                runFeedbackScheduling(process);
            } else {
                runProcessForTimeQuantum(process, timeQuantum);
                if (!process.isCompleted()) {
                    userPriorityQueue.addProcess(process);
                }
            }
        }
    }

    // Check if the process is high priority
    private boolean isHighPriority(Process process) {
        int highPriorityThreshold = 3;
        return process.getPriority() < highPriorityThreshold;
    }

    // Feedback scheduling for high-priority processes
    private void runFeedbackScheduling(Process process) {
        final int highPriorityTimeQuantum = 2;
        if (resourceManager.areResourcesAvailable(process) &&
                memoryManager.isMemoryAvailable(process)) {
            resourceManager.allocateResources(process);
            memoryManager.allocateMemory(process);

            // Simulating running the high-priority process
            System.out.println("Running high-priority process: " + process.getProcessId());
            process.setState("running");
            process.decrementCpuTime(highPriorityTimeQuantum);

            // Check if the process is completed after execution
            if (process.isCompleted()) {
                System.out.println("High-priority process " + process.getProcessId() + " completed.");
            } else {
                // Adjust priority based on remaining CPU time or other criteria
                adjustPriorityForFeedback(process);
            }

            // Release resources and memory
            resourceManager.releaseResources(process);
            memoryManager.releaseMemory(process);
        } else {
            System.out
                    .println("Resources or memory not available for high-priority process: " + process.getProcessId());
        }
    }

    private void adjustPriorityForFeedback(Process process) {
        // Example logic for adjusting the priority
        // Increase priority (lower the numerical value) if the process is close to
        // completion
        if (process.getCpuTimeRequired() <= 2) { // Threshold for priority increase
            int newPriority = Math.max(0, process.getPriority() - 1); // Ensuring priority doesn't fall below 0
            process.setPriority(newPriority);
            System.out.println("Process " + process.getProcessId() + " priority increased to: " + newPriority);
        }
    }

    private void runProcessForTimeQuantum(Process process, int timeQuantum) {
        if (resourceManager.areResourcesAvailable(process)) {
            resourceManager.allocateResources(process);

            // Update the process state and simulate running for a time quantum
            System.out.println("Running process for time quantum: " + process.getProcessId());
            process.setState("running");
            process.decrementCpuTime(timeQuantum); // Decrement the required CPU time by the time quantum

            if (process.getCpuTimeRequired() <= 0) {
                process.complete();
                System.out.println("Process " + process.getProcessId() + " completed.");
            }

            resourceManager.releaseResources(process);
        } else {
            System.out.println("Not enough resources for process: " + process.getProcessId());
        }
    }

    // Method to check if all queues are empty
    private boolean allQueuesAreEmpty() {
        return realTimeQueue.isEmpty() && userPriorityQueue.isEmpty();
    }

    // Additional methods as needed for your dispatcher logic, like
    // allocateResources, releaseResources, etc.

    // Getters and setters
    // Setters
    public void setRealTimeQueue(Queue realTimeQueue) {
        this.realTimeQueue = realTimeQueue;
    }

    public void setUserPriorityQueue(Queue userPriorityQueue) {
        this.userPriorityQueue = userPriorityQueue;
    }

    public void setCurrentTimeTick(int currentTimeTick) {
        this.currentTimeTick = currentTimeTick;
    }

    public void setResourceManager(Resource resourceManager) {
        this.resourceManager = resourceManager;
    }

    public void setMemoryManager(MemoryManager memoryManager) {
        this.memoryManager = memoryManager;
    }

    // Getters
    public Queue getRealTimeQueue() {
        return realTimeQueue;
    }

    public Queue getUserPriorityQueue() {
        return userPriorityQueue;
    }

    public int getCurrentTimeTick() {
        return currentTimeTick;
    }

    public Resource getResourceManager() {
        return resourceManager;
    }

    public MemoryManager getMemoryManager() {
        return memoryManager;
    }

    public void addProcessToQueue(Process process) {
        if (process.getPriority() == 0) { // Assuming priority 0 is for real-time processes
            realTimeQueue.addProcess(process);
        } else {
            userPriorityQueue.addProcess(process);
        }
    }

}

