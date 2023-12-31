package Proje;

import java.util.LinkedList;

public class Queue {
    // Attributes
    private LinkedList<Process> processQueue;
    private String queueType; // Real-Time or User Priority

    // Constructor
    public Queue(String queueType) {
        this.processQueue = new LinkedList<>();
        this.queueType = queueType;
    }

    // Method to add a process to the queue
    public void addProcess(Process process) {
        processQueue.add(process);
    }

    // Method to remove a process from the queue
    public Process removeProcess() {
        return processQueue.poll();
    }

    // Method to get the next process without removing it
    public Process peekProcess() {
        return processQueue.peek();
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return processQueue.isEmpty();
    }

    // Method to get the size of the queue
    public int size() {
        return processQueue.size();
    }

    // Method to get the type of the queue
    public String getQueueType() {
        return queueType;
    }

    // Additional methods as required, like displaying all processes in the queue
    public void displayQueue() {
        for (Process process : processQueue) {
            System.out.println(process);
        }
    }
}
