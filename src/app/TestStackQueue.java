package app;

import stackqueue.ActionStack;
import stackqueue.ServiceQueue;

public class TestStackQueue {
    public static void main(String[] args) {
        ActionStack stack = new ActionStack(5);
        stack.push("Added student S001");
        stack.push("Updated student S001");
        stack.push("Deleted student S001");
        stack.displayAll();
        System.out.println("Undo: " + stack.pop());

        ServiceQueue queue = new ServiceQueue();
        queue.enqueue("S001 - Transcript request");
        queue.enqueue("S002 - ID card replacement");
        queue.displayAll();
        System.out.println("Processing: " + queue.dequeue());
        queue.displayAll();
    }
}