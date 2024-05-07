package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<String> subjectId;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjectId = new ArrayList<>();
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public List<String> getSubjectId() {
        return subjectId;
    }

    public void setSubject(String subjectId) {
        //수강과목 리스트에 선택한 과목 ID 값 추가
        this.subjectId.add(subjectId);
    }


}