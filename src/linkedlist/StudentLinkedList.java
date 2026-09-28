package linkedlist;

import model.Student;

/**
 * Singly linked list to store and manage student records.
 */
public class StudentLinkedList {

    // Node of the linked list
    private static class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public StudentLinkedList() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    /** Adds a student at the end of the list. Returns false if the ID already exists. */
    public boolean add(Student student) {
        if (search(student.getStudentId()) != null) {
            return false; // duplicate ID
        }
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    /** Finds a student by ID. Returns null if not found. */
    public Student search(String studentId) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    /** Updates name, programme and marks of an existing student. Returns false if not found. */
    public boolean update(String studentId, String newName, String newProgramme, double newMarks) {
        Student s = search(studentId);
        if (s == null) {
            return false;
        }
        s.setName(newName);
        s.setProgramme(newProgramme);
        s.setMarks(newMarks);
        return true;
    }

    /** Deletes a student by ID. Returns the deleted student, or null if not found. */
    public Student delete(String studentId) {
        if (head == null) {
            return null;
        }
        if (head.data.getStudentId().equalsIgnoreCase(studentId)) {
            Student removed = head.data;
            head = head.next;
            size--;
            return removed;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data.getStudentId().equalsIgnoreCase(studentId)) {
                Student removed = current.next.data;
                current.next = current.next.next;
                size--;
                return removed;
            }
            current = current.next;
        }
        return null;
    }

    /** Displays all students in the list. */
    public void displayAll() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }
        Node current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.data);
            current = current.next;
            count++;
        }
    }

    /** Returns all students as an array (useful for BST and hash table loading). */
    public Student[] toArray() {
        Student[] arr = new Student[size];
        Node current = head;
        int i = 0;
        while (current != null) {
            arr[i++] = current.data;
            current = current.next;
        }
        return arr;
    }
}