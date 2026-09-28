package stackqueue;

/**
 * Queue (linked nodes) to handle student service requests in order of arrival (FIFO).
 */
public class ServiceQueue {

    private static class Node {
        String request;
        Node next;
        Node(String request) { this.request = request; }
    }

    private Node front, rear;
    private int size;

    public boolean isEmpty() { return front == null; }
    public int getSize() { return size; }

    public void enqueue(String request) {
        Node n = new Node(request);
        if (rear == null) {
            front = rear = n;
        } else {
            rear.next = n;
            rear = n;
        }
        size++;
    }

    public String dequeue() {
        if (isEmpty()) return null;
        String r = front.request;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return r;
    }

    public String peek() {
        return isEmpty() ? null : front.request;
    }

    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }
        System.out.println("--- Pending Service Requests ---");
        Node cur = front;
        int i = 1;
        while (cur != null) {
            System.out.println(i++ + ". " + cur.request);
            cur = cur.next;
        }
    }
}