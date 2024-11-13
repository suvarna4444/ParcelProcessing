package main;

import java.util.LinkedList;
import java.util.Queue;

public class QueueOfCustomers {
    private Queue<Customer> customerQueue;
    public QueueOfCustomers() {
        this.customerQueue = new LinkedList<>();
    }
    public void addCustomer(Customer customer) {
        customerQueue.offer(customer);
    }
    public Customer removeCustomer() {
        return customerQueue.poll();
    }
    public Customer peekNextCustomer() {
        return customerQueue.peek();
    }
    public boolean isEmpty() {
        return customerQueue.isEmpty();
    }
    public int getSize() {
        return customerQueue.size();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Customer customer : customerQueue) {
            sb.append(customer).append("\n");
        }
        return sb.toString();
    }
}
