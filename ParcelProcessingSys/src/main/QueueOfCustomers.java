package main;

import java.util.LinkedList;
import java.util.Queue;

public class QueueOfCustomers {
    private Queue<Customer> customerQueue;

    // Constructor
    public QueueOfCustomers() {
        this.customerQueue = new LinkedList<>();
    }

    // Method to add a customer to the queue
    public void addCustomer(Customer customer) {
        customerQueue.offer(customer);
    }

    // Method to remove a customer from the queue
    public Customer removeCustomer() {
        return customerQueue.poll();
    }

    // Method to peek at the next customer in the queue
    public Customer peekNextCustomer() {
        return customerQueue.peek();
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return customerQueue.isEmpty();
    }

    // Method to get the size of the queue
    public int getSize() {
        return customerQueue.size();
    }

    // Method to represent the queue as a string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Customer customer : customerQueue) {
            sb.append(customer).append("\n");
        }
        return sb.toString();
    }
}
