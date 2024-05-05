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
        this.subjects = new ArrayList<>();
    }

    public Map<Subject, Map<Integer, Score>> getScores() {
        return scores;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setSubject(Subject subject) {
        scores.put(subject, new HashMap<>());
    }

    // 과목 입력 시 학생의 지금 까지 받았던 회차와 점수를 가져오는 메서드
    public void setSubjectScore(Subject subject){
        // 회차 , 점수라는 값이 필요
    }
}