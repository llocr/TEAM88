package model;

public class Score {
    private String scoreId;
    private String subjectId;
    private String studentId;
    private int round;
    private int score;
    private Grade grade;
    private SubjectType subjectType;

    public Score(String scoreId, String subjectId, String studentId, int round, int score, SubjectType type) {
        this.scoreId = scoreId;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.round = round;
        this.score = score;
        this.subjectType = type;
        this.grade = GradeCalculator.calculateGrade(score, type);
    }

    public Grade getGrade() {
        return grade;
    }

    public int getScore() {
        return score;
    }

    public void setScoreAndGrade(int newScore) {
        this.score = newScore;
        this.grade = GradeCalculator.calculateGrade(newScore, this.subjectType);
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
}