package model;

/**
 * Represents a university student record.
 */
public class Student {
    private String studentId;
    private String name;
    private String programme;
    private double marks;

    public Student(String studentId, String name, String programme, double marks) {
        this.studentId = studentId;
        this.name = name;
        this.programme = programme;
        setMarks(marks);
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getProgramme() { return programme; }
    public double getMarks() { return marks; }

    public void setName(String name) { this.name = name; }
    public void setProgramme(String programme) { this.programme = programme; }

    public void setMarks(double marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
        this.marks = marks;
    }

    @Override
    public String toString() {
        return String.format("ID: %-10s | Name: %-20s | Programme: %-15s | Marks: %.2f",
                studentId, name, programme, marks);
    }
}