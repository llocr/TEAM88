package model;

import java.awt.*;
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
public Map<Subject, Map<Integer, Score>> getScores() {
        return scores;
}
    public String getStudentName() {
        return studentName;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setSubject(Subject subject) {
        scores.put(subject, new HashMap<>());
    }


}