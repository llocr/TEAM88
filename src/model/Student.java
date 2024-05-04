package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<String> subjects;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjects = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setSubject(String subjectId) {
        //수강과목 리스트에 선택한 과목 ID 값 추가
        subjects.add(subjectId);
    }
}
