package tree;

import model.Student;

/**
 * Binary Search Tree ordered by Student ID.
 */
public class StudentBST {

    private static class Node {
        Student data;
        Node left, right;
        Node(Student data) { this.data = data; }
    }

    private Node root;

    public boolean insert(Student s) {
        if (search(s.getStudentId()) != null) return false; // duplicate
        root = insertRec(root, s);
        return true;
    }

    private Node insertRec(Node node, Student s) {
        if (node == null) return new Node(s);
        if (s.getStudentId().compareToIgnoreCase(node.data.getStudentId()) < 0) {
            node.left = insertRec(node.left, s);
        } else {
            node.right = insertRec(node.right, s);
        }
        return node;
    }

    public Student search(String id) {
        Node cur = root;
        while (cur != null) {
            int cmp = id.compareToIgnoreCase(cur.data.getStudentId());
            if (cmp == 0) return cur.data;
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        return null;
    }

    public boolean delete(String id) {
        if (search(id) == null) return false;
        root = deleteRec(root, id);
        return true;
    }

    private Node deleteRec(Node node, String id) {
        if (node == null) return null;
        int cmp = id.compareToIgnoreCase(node.data.getStudentId());
        if (cmp < 0) {
            node.left = deleteRec(node.left, id);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, id);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node min = node.right;
            while (min.left != null) min = min.left;
            node.data = min.data;
            node.right = deleteRec(node.right, min.data.getStudentId());
        }
        return node;
    }

    /** Inorder traversal prints students sorted by ID. */
    public void displayInOrder() {
        if (root == null) {
            System.out.println("No records in BST.");
            return;
        }
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }
}