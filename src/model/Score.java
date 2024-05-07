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

    public Grade setGrade() {
        switch(score){
            case 10:
            case 9:
                grade = Grade.A;
                break;
            case 8:
                grade = Grade.B;
                break;
            case 7:
                grade = Grade.C;
                break;
            case 6:
                grade = Grade.D;
                break;
            case 5:
            case 4:
            case 3:
            case 2:
            case 1:
                grade = Grade.F;
                break;
            default:
                grade = Grade.N;
        }

        return grade;
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

    public boolean matches(String studentId, String subjectId, int round) {
        return this.studentId.equals(studentId) && this.subjectId.equals(subjectId) && this.round == round;
    }

    public static HashMap<String, List<Score>> scoreMap = new HashMap<>();

    private static Score findScore(String studentId, String subjectId, int round) {
        List<Score> scores = scoreMap.getOrDefault(studentId, new ArrayList<>());
        for (Score score : scores) {
            if (score.matches(studentId, subjectId, round)) {
                return score;
            }
        }
        return null; // 점수 정보가 없을 경우 null 반환
    }
}
