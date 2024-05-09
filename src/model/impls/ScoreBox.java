package model.impls;

public class ScoreBox {
    // studentId , StuList<회차, score>를 받아서 따로 관리하는 느낌이다.
//    private HashMap<String, List<Score>> scoreMap; // studentID , Score list
    protected Grade grade;


    // 점수 등록
    public void registerScore(String sequence, String studentId, String subjectId, int round, int score, SubjectType type) {
        this.grade = GradeCalculator.calculateGrade(score, type); // 등급 계산

        // Score 객체 생성
        Score newScore = new Score(sequence, subjectId, studentId, round, score, type);

//        scoreMap.put(studentId, newScore);
        System.out.println("과목명 : " + subjectId + "에 " + round + "회차 " + score + "(" + newScore.getGrade() + ")을 등록했습니다.");
    }
}
