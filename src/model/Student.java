package model;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String studentId;
    private String studentName;
    private Map<Subject, Map<Integer, Score>> scores;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.scores = new HashMap<>();
    }
}
