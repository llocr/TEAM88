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
}
