package app;

import linkedlist.StudentLinkedList;
import model.Student;

public class TestLinkedList {
    public static void main(String[] args) {
        StudentLinkedList list = new StudentLinkedList();
        list.add(new Student("S001", "Kamal", "IT", 85));
        list.add(new Student("S002", "Nimal", "SE", 70));
        System.out.println("Duplicate add: " + list.add(new Student("S001", "X", "IT", 50)));
        list.displayAll();
        list.update("S002", "Nimal P", "IT", 75);
        list.delete("S001");
        list.displayAll();
    }
}