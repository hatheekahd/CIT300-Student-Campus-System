package app;

import java.util.Scanner;

import graph.CampusGraph;
import hashing.StudentHashTable;
import linkedlist.StudentLinkedList;
import model.Student;
import stackqueue.ActionStack;
import stackqueue.ServiceQueue;
import tree.StudentBST;

/**
 * University Student Record and Campus Route Management System.
 * Menu-driven console application integrating linked list, stack, queue,
 * BST, hash table and graph.
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static final StudentLinkedList list = new StudentLinkedList();
    private static final ActionStack history = new ActionStack(20);
    private static final ServiceQueue requests = new ServiceQueue();
    private static final StudentBST bst = new StudentBST();
    private static final StudentHashTable hash = new StudentHashTable(31);
    private static final CampusGraph graph = new CampusGraph();

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = readInt("Enter your choice: ", 1, 16);
            switch (choice) {
                case 1: addStudent(); break;
                case 2: updateStudent(); break;
                case 3: deleteStudent(); break;
                case 4: list.displayAll(); break;
                case 5: addRequest(); break;
                case 6: processRequest(); break;
                case 7: history.displayAll(); break;
                case 8: bst.displayInOrder(); break;
                case 9: searchHash(); break;
                case 10: addLocation(); break;
                case 11: removeLocation(); break;
                case 12: addConnection(); break;
                case 13: removeConnection(); break;
                case 14: graph.displayNetwork(); break;
                case 15: traverse(); break;
                case 16: System.out.println("Goodbye!"); break;
            }
        } while (choice != 16);
    }

    private static void printMenu() {
        System.out.println("\n===== Student Record & Campus Route System =====");
        System.out.println(" 1. Add Student Record");
        System.out.println(" 2. Update Student Record");
        System.out.println(" 3. Delete Student Record");
        System.out.println(" 4. Display All Records using Linked List");
        System.out.println(" 5. Add Service Request to Queue");
        System.out.println(" 6. Process Next Service Request");
        System.out.println(" 7. Display Recent Actions using Stack");
        System.out.println(" 8. Display Students using BST");
        System.out.println(" 9. Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS or DFS");
        System.out.println("16. Exit");
    }

    // ---------- Input helpers with validation ----------

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(line);
                if (v >= min && v <= max) return v;
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static double readMarks(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                double m = Double.parseDouble(line);
                if (m >= 0 && m <= 100) return m;
                System.out.println("Invalid marks. Marks must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric marks.");
            }
        }
    }

    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input cannot be empty.");
        }
    }

    // ---------- Student operations ----------

    private static void addStudent() {
        String id = readText("Student ID: ");
        if (list.search(id) != null) {
            System.out.println("Error: Student ID already exists.");
            return;
        }
        String name = readText("Name: ");
        String programme = readText("Programme: ");
        double marks = readMarks("Marks (0-100): ");

        Student s = new Student(id, name, programme, marks);
        list.add(s);
        bst.insert(s);
        hash.put(s);
        history.push("Added student " + id);
        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {
        String id = readText("Student ID to update: ");
        Student s = list.search(id);
        if (s == null) {
            System.out.println("Error: Student not found.");
            return;
        }
        System.out.println("Current: " + s);
        String name = readText("New Name: ");
        String programme = readText("New Programme: ");
        double marks = readMarks("New Marks (0-100): ");
        // Same Student object is shared by list, BST and hash table
        list.update(id, name, programme, marks);
        history.push("Updated student " + id);
        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() {
        String id = readText("Student ID to delete: ");
        Student removed = list.delete(id);
        if (removed == null) {
            System.out.println("Error: Student not found.");
            return;
        }
        bst.delete(id);
        hash.remove(id);
        history.push("Deleted student " + id + " (" + removed.getName() + ")");
        System.out.println("Student deleted successfully.");
    }

    private static void searchHash() {
        String id = readText("Student ID to search: ");
        Student s = hash.get(id);
        if (s == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Found: " + s);
        }
    }

    // ---------- Service requests ----------

    private static void addRequest() {
        String id = readText("Student ID: ");
        if (list.search(id) == null) {
            System.out.println("Error: Student not found. Cannot add request.");
            return;
        }
        String req = readText("Request description: ");
        requests.enqueue(id + " - " + req);
        history.push("Queued request for " + id);
        System.out.println("Request added to queue. Pending: " + requests.getSize());
    }

    private static void processRequest() {
        String r = requests.dequeue();
        if (r == null) {
            System.out.println("No pending service requests.");
            return;
        }
        history.push("Processed request: " + r);
        System.out.println("Processing: " + r);
    }

    // ---------- Campus graph ----------

    private static void addLocation() {
        String name = readText("Location name: ");
        System.out.println(graph.addLocation(name)
                ? "Location added." : "Error: Location already exists.");
    }

    private static void removeLocation() {
        String name = readText("Location name to remove: ");
        System.out.println(graph.removeLocation(name)
                ? "Location removed." : "Error: Location not found.");
    }

    private static void addConnection() {
        String a = readText("From location: ");
        String b = readText("To location: ");
        System.out.println(graph.addConnection(a, b)
                ? "Connection added."
                : "Error: Invalid (location missing, same location, or connection exists).");
    }

    private static void removeConnection() {
        String a = readText("From location: ");
        String b = readText("To location: ");
        System.out.println(graph.removeConnection(a, b)
                ? "Connection removed."
                : "Error: Connection unavailable or location missing.");
    }

    private static void traverse() {
        String start = readText("Start location: ");
        int t = readInt("1. BFS  2. DFS : ", 1, 2);
        if (t == 1) graph.bfs(start);
        else graph.dfs(start);
    }
}