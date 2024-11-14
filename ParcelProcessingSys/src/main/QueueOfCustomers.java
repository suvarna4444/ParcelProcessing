package main;

import java.util.LinkedList;
import java.util.Queue;

public class QueueOfCustomers {
    // Queue to store customers
    private Queue<Customer> customerQueue;

    // Constructor initializes the customer queue
    public QueueOfCustomers() {
        this.customerQueue = new LinkedList<>();
    }

    // Adds a customer to the queue
    public void addCustomer(Customer customer) {
        customerQueue.offer(customer);
    }

    // Removes and returns the next customer from the queue
    public Customer removeCustomer() {
        return customerQueue.poll();
    }

    // Returns the next customer in the queue without removing them
    public Customer peekNextCustomer() {
        return customerQueue.peek();
    }

    // Checks if the queue is empty
    public boolean isEmpty() {
        return customerQueue.isEmpty();
    }

    // Returns the number of customers in the queue
    public int getSize() {
        return customerQueue.size();
    }

    // Returns a string representation of the customers in the queue
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Customer customer : customerQueue) {
            sb.append(customer).append("\n");
        }
        return sb.toString();
    }
}
