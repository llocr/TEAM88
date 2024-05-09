package model.impls;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<String> subjects;
    private Status status;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjects = new ArrayList<>();
        this.status = Status.GREEN;         //수강생 상태 기본값 = GREEN
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Status getStatus() {
        return status;
    }

    public void setSubject(String subjectId) {
        //수강과목 리스트에 선택한 과목 ID 값 추가
        subjects.add(subjectId);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<String> getSubjects() {
        return subjects;
    }
}