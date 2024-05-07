package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Score {
    private String scoreId;
    private String subjectId;
    private String studentId;
    private int round;
    private int score;
    private Grade grade;

    private static final int[] mandatoryThresholds = {95, 90, 80, 70, 60, 0};
    private static final Grade[] mandatoryGrades = {Grade.A, Grade.B, Grade.C, Grade.D, Grade.F, Grade.N};

    private static final int[] choiceThresholds = {90, 80, 70, 60, 50, 0};
    private static final Grade[] choiceGrades = {Grade.A, Grade.B, Grade.C, Grade.D, Grade.F, Grade.N};

    public Score(String scoreId, String subjectId, String studentId, int round, int score) {
        this.scoreId = scoreId;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.round = round;
        this.score = score;
    }


    // score 점수를 반환
    public int getScore() {
        return score;
    }

    public Grade calculateGrade(int score, SubjectType type) {
        int[] thresholds;
        Grade[] grades;

        if (type == SubjectType.MANDATORY) {
            thresholds = mandatoryThresholds;
            grades = mandatoryGrades;
        } else {
            thresholds = choiceThresholds;
            grades = choiceGrades;
        }

        for (int i = 0; i < thresholds.length; i++) {
            if (score >= thresholds[i]) {
                grade = grades[i];
                return grade;
            }
        }
        grade = Grade.N;
        return grade; // Default, should not happen
    }

    // 학점을 반환하는 메서드
    public Grade getGrade() {
        return this.grade;
    }


    public int setScore(int score) {
        this.score = score;
        return score;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public int getRound() {
        return round;
    }


    public static HashMap<String, List<Score>> scoreMap = new HashMap<>();








}