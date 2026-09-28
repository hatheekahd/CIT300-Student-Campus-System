package stackqueue;

/**
 * Stack (array based) to keep recent actions / undo history.
 */
public class ActionStack {
    private String[] items;
    private int top;

    public ActionStack(int capacity) {
        items = new String[capacity];
        top = -1;
    }

    public boolean isEmpty() { return top == -1; }
    public boolean isFull() { return top == items.length - 1; }

    public void push(String action) {
        if (isFull()) {
            // drop the oldest action to make room
            for (int i = 1; i <= top; i++) {
                items[i - 1] = items[i];
            }
            top--;
        }
        items[++top] = action;
    }

    public String pop() {
        if (isEmpty()) return null;
        return items[top--];
    }

    public String peek() {
        return isEmpty() ? null : items[top];
    }

    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No recent actions.");
            return;
        }
        System.out.println("--- Recent Actions (latest first) ---");
        for (int i = top; i >= 0; i--) {
            System.out.println((top - i + 1) + ". " + items[i]);
        }
    }
}