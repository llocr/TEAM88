package model;

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
}
