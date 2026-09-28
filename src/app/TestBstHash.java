package app;

import hashing.StudentHashTable;
import model.Student;
import tree.StudentBST;

public class TestBstHash {
    public static void main(String[] args) {
        StudentBST bst = new StudentBST();
        StudentHashTable hash = new StudentHashTable(11);

        Student s1 = new Student("S003", "Kamal", "IT", 85);
        Student s2 = new Student("S001", "Nimal", "SE", 70);
        Student s3 = new Student("S002", "Sunil", "IT", 90);

        bst.insert(s1); bst.insert(s2); bst.insert(s3);
        hash.put(s1); hash.put(s2); hash.put(s3);

        System.out.println("Duplicate insert: " + bst.insert(s1));
        System.out.println("--- BST (sorted by ID) ---");
        bst.displayInOrder();
        System.out.println("Hash search S002: " + hash.get("S002"));
        System.out.println("Hash search S999: " + hash.get("S999"));
    }
}