package model;

import model.impls.Score;
import model.impls.Student;
import model.impls.Subject;
import model.impls.SubjectType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class DataManger {

    //데이터 저장소
    private static HashMap<String, Student> studentList;    //수강생 리스트
    private static List<Subject> subjectList;               //과목 리스트
    private static List<Score> scoreList;                   //점수 리스트

    public DataManger() {
        // 불러와지면 바로 초기화하기!
        setInitData();
    }

    //index 관리 필드
    protected static int studentIndex;
    protected static final String INDEX_TYPE_STUDENT = "ST";
    protected static int subjectIndex;
    protected static final String INDEX_TYPE_SUBJECT = "SU";
    protected static int scoreIndex;
    protected static final String INDEX_TYPE_SCORE = "SC";

    //초기 데이터 생성
    private static void setInitData() {
        studentList = new HashMap<>();
        scoreList = new ArrayList<>();
        subjectList = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SubjectType.MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SubjectType.MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SubjectType.MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SubjectType.MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SubjectType.MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SubjectType.MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SubjectType.CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SubjectType.CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SubjectType.CHOICE
                )
        );
    }

    // index 자동 증가
    protected static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }


    public void addStudent(Student student) {
        studentList.put(student.getStudentId(), student);
    }

    public Student getStudent(String studentId) {
        return studentList.get(studentId);
    }

    public List<Score> getScoresByStudentId(String studentId) {
        List<Score> scores = new ArrayList<>();
        for (Score score : scoreList) {
            if (score.getStudentId().equals(studentId)) {
                scores.add(score);
            }
        }
        return scores;
    }

    public void addScore(Score score) {
        scoreList.add(score);
    }

    public static List<Subject> getSubjects() {
        return subjectList;
    }

    public void removeStudent(String studentId) {
        studentList.remove(studentId);
        scoreList.removeIf(score -> score.getStudentId().equals(studentId));
    }

    public Collection<Student> getAllStudents() {
        return studentList.values();
    }

    public static List<Score> getScoreList() {
        return scoreList;
    }

    public static HashMap<String, Student> getStudentList() {
        return studentList;
    }
}
