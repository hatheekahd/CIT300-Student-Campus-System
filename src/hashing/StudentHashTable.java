package hashing;

import model.Student;

/**
 * Hash table using separate chaining for fast Student ID lookup.
 */
public class StudentHashTable {

    private static class Entry {
        Student student;
        Entry next;
        Entry(Student student) { this.student = student; }
    }

    private Entry[] table;
    private int size;

    public StudentHashTable(int capacity) {
        table = new Entry[capacity];
    }

    private int hash(String id) {
        int h = 0;
        for (char c : id.toUpperCase().toCharArray()) {
            h = (h * 31 + c) % table.length;
        }
        return h;
    }

    public boolean put(Student s) {
        if (get(s.getStudentId()) != null) return false; // duplicate
        int idx = hash(s.getStudentId());
        Entry e = new Entry(s);
        e.next = table[idx];
        table[idx] = e;
        size++;
        return true;
    }

    public Student get(String id) {
        Entry cur = table[hash(id)];
        while (cur != null) {
            if (cur.student.getStudentId().equalsIgnoreCase(id)) return cur.student;
            cur = cur.next;
        }
        return null;
    }

    public boolean remove(String id) {
        int idx = hash(id);
        Entry cur = table[idx], prev = null;
        while (cur != null) {
            if (cur.student.getStudentId().equalsIgnoreCase(id)) {
                if (prev == null) table[idx] = cur.next;
                else prev.next = cur.next;
                size--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    public int getSize() { return size; }
}