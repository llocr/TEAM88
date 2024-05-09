package manager;

import model.*;
import type.Grade;
import type.Status;
import type.SubjectType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static manager.DataManger.*;

public class ScoreManager {
    public static Scanner sc = new Scanner(System.in);

    /*
    1. 과목별 시험 회차 및 점수 등록
     */
    public static void createScore() {
        // 변수 초기화
        Student student = null;
        String studentId = "";
        String sbName = "";
        String subjectId = "";
        SubjectType type = SubjectType.MANDATORY;
        int round = 0;
        int scores = 0;
        boolean validInput = false;

        // 학생 선택
        while (!validInput) {
            System.out.println("\n==================================");
            System.out.print("조회할 학생의 등록코드를 입력해주세요 : ");
            studentId = sc.next();

            student = studentList.get(studentId);
            if (student == null) {
                System.out.println("해당 학생이 존재하지 않습니다.");
            } else {
                System.out.println(student.getStudentName() + "이 수강하는 과목입니다.");
                for (String subjId : student.getSubjects()) {
                    for (Subject subj : subjectList) {
                        if (subj.getSubjectId().equals(subjId)) {
                            System.out.println(subj.getSubjectId() + " - " + subj.getSubjectName());
                        }
                    }
                }
                validInput = true;
            }
        }

        // 과목 선택
        validInput = false;
        while (!validInput) {
            System.out.println("\n==================================");
            System.out.print("등록할 과목 ID를 입력해주세요 : ");
            subjectId = sc.next();

            for (Subject subj : subjectList) {
                if (subj.getSubjectId().equals(subjectId)) {
                    sbName = subj.getSubjectName();
                    type = subj.getSubjectType();
                    validInput = true;
                    break;
                }
            }
            if (!validInput) {
                System.out.println("해당 과목은 등록되어 있지 않습니다.");
            }
        }

        // 회차와 점수 입력
        while (validInput) {
            System.out.print("등록하실 회차를 입력해주세요 : ");
            round = sc.nextInt();
            System.out.print("등록하실 점수를 입력해주세요 : ");
            scores = sc.nextInt();

            if ((round > 10) || (round < 1)) {
                System.out.println("회차는 1회차부터 10회차까지 있습니다. 다시 입력해주세요 :)");
            } else if ((scores < 0) || (scores > 100)) {
                System.out.println("점수는 0점부터 100점까지 입력이 가능합니다. 다시 입력해주세요 :)");
            }

            // 이미 등록된 회차인지 확인
            int finalRound = round;
            String finalSubjectId = subjectId;
            String finalStudentId = studentId;
            boolean isRoundAlreadyRegistered = scoreList.stream()
                    .anyMatch(score -> score.getStudentId().equals(finalStudentId) &&
                            score.getSubjectId().equals(finalSubjectId) &&
                            score.getRound() == finalRound);

            if (isRoundAlreadyRegistered) {
                System.out.println("이미 " + round + "회차에 대한 점수가 등록되어 있습니다. 다른 회차를 입력해주세요.");
            } else {
                validInput = false;  // 유효한 회차와 점수를 받았으므로 반복 종료
            }
        }

        // 점수 등록
        Score test = new Score(DataManger.sequence(DataManger.INDEX_TYPE_SCORE), subjectId, studentId, round, scores, type);
        scoreList.add(test);
        System.out.println(test.getScoreId());
        System.out.println("학생 : " + student.getStudentName());
        System.out.println("과목명 : " + sbName + "에 " + round + "회차 " + scores + "(" + test.getGrade() + ")을 등록했습니다.");
    }

    /*
    2. 과목별 회차 점수 조정
     */
    public static void updateScore() {
        System.out.print("\n수정할 점수의 수강생 ID를 입력해 주세요: ");
        String studentId = sc.next();

        List<Score> scores = findScoresByStudentId(studentId);
        if (scores.isEmpty()) {
            System.out.println("등록된 점수 정보가 없습니다.");
            return;
        }
        displayScores(scores);

        System.out.print("수정할 과목 ID를 입력해 주세요: ");
        String subjectId = sc.next();
        System.out.print("회차를 입력해 주세요: ");
        int round = sc.nextInt();

        boolean updated = false;
        for (Score score : scores) {
            if (score.getSubjectId().equals(subjectId) && score.getRound() == round) {
                System.out.print("새로운 점수를 입력해 주세요: ");
                while (!updated) {
                    int newScore = sc.nextInt();
                    if (0 <= newScore && newScore <= 100) {
                        score.setScoreAndGrade(newScore);
                        System.out.println("점수가 수정되었습니다.");
                        updated = true;
                    } else {
                        System.out.print("0에서 100 사이의 정수를 입력하세요: ");
                    }
                }
                if (updated) {
                    break;
                }
            }
        }
        if (!updated) {
            System.out.println("해당 과목의 점수 정보를 찾을 수 없습니다.");
        }
    }

    /*
    2-1. 수강생 아이디로 점수 정보 가져오기
     */
    public static List<Score> findScoresByStudentId(String studentId) {
        List<Score> scores = new ArrayList<>();
        for (Score score : scoreList) {
            if (score.getStudentId().equals(studentId)) {
                scores.add(score);
            }
        }
        if (scores.isEmpty()) {
            System.out.println("점수 정보를 찾을 수 없습니다: 학생 ID - " + studentId + scores);
        }
        return scores;
    }

    /*
    2-2. 수강생 점수 보여 주기
     */
    public static void displayScores(List<Score> scores) {
        for (Score score : scores) {
            System.out.printf("과목 ID: %s, 회차: %d, 점수: %d, 등급: %s\n", score.getSubjectId(), score.getRound(), score.getScore(), score.getGrade());
        }
    }

    /*
3. 특정 과목 회차별 등급 조회
*/
    public static void displayGradeView() {
        System.out.print("학생 ID를 입력하세요: ");
        String studentId = sc.next();

        // 학생 이름과 수강 과목 출력
        if (!studentList.containsKey(studentId)) {
            System.out.println("해당 학생이 존재하지 않습니다.");
            return;
        }
        String studentName = studentList.get(studentId).getStudentName();
        System.out.println(studentName + " 학생이 수강중인 과목 입니다.");

        // 수강 중인 과목 목록 출력
        Student student = studentList.get(studentId);
        List<String> subjects = student.getSubjects();
        for (String subjectId : subjects) {
            for (Subject subject : subjectList) {
                if (subject.getSubjectId().equals(subjectId)) {
                    System.out.println(subject.getSubjectId() + " - " + subject.getSubjectName());
                    break;
                }
            }
        }

        // 조회할 과목 선택
        System.out.print("조회할 과목의 ID를 입력하세요: ");
        String subjectId = sc.next();

        // 해당 과목의 성적 조회
        if (getSubjectNameById(subjectId) == null) {
            System.out.println("해당 과목의 성적을 조회 할 수 없습니다. 올바른 과목 코드를 입력해주세요 :)");
        } else {
            System.out.println("=== " + getSubjectNameById(subjectId) + " 과목의 성적 ==="); // 과목 이름을 함께 출력
        // 회차 선택
            System.out.print("조회할 회차를 입력하세요: ");
            int round = sc.nextInt();

            Grade grade = findGrade(subjectId, studentId, round);
            if (grade == Grade.N) {
                System.out.println("해당 회차의 학점이 없습니다.");
            } else {
                System.out.println(round + "회차 : " + grade);
            }
        }
    }

    /*
    3-1. 과목 ID로 Subject 이름 가져오기
     */
    public static String getSubjectNameById(String subjectId) {
        // 과목 리스트를 반복하면서 과목 ID와 일치하는 과목을 찾음
        for (Subject subject : subjectList) {
            if (subject.getSubjectId().equals(subjectId)) {
                return subject.getSubjectName();  /* 과목 ID도 같이 출력 할려면 : subject.getSubjectId() 추가 */
            }
        }
        // 일치하는 과목 ID가 없을 경우
        return null;
    }

    /*
    3-2. 해당하는 Grade 찾기
     */
    public static Grade findGrade(String subjectId, String studentId, int round) {
        for (Score score : scoreList) {
            if (score.getSubjectId().equals(subjectId) && score.getStudentId().equals(studentId) && score.getRound() == round) {
                // 저장된 학점을 가져옴
                return score.getGrade();
            }
        }
        // 해당 회차에 대한 학점이 없을 경우 기본값으로 N을 반환
        return Grade.N;
    }

    /*
    4. 수강생의 과목별 평균 등급 조회
     */
    public static void averageInquiry() {
        System.out.print("평균 등급을 조회할 학생의 ID를 입력하세요: ");
        String studentId = sc.next();

        // 학생 이름 출력
        if (!studentList.containsKey(studentId)) {
            System.out.println("해당 학생이 존재하지 않습니다.");
            return;
        }
        String studentName = studentList.get(studentId).getStudentName();
        System.out.println("학생 이름: " + studentName);

        // 각 과목의 평균 등급 계산 및 출력
        for (Subject subject : subjectList) {
            int totalScore = 0;
            int count = 0;
            for (Score score : scoreList) {
                if (score.getStudentId().equals(studentId) && score.getSubjectId().equals(subject.getSubjectId())) {
                    totalScore += score.getScore();
                    count++;
                }
            }
            if (count > 0) {
                double averageScore = (double) totalScore / count;
                Grade averageGrade = GradeCalculator.calculateGrade((int) averageScore, subject.getSubjectType());
                System.out.println("과목: " + subject.getSubjectName() + ", 평균 등급: " + averageGrade);
            }
        }
    }

    /*
    5. 상태별 수강생들의 평균 등급 조회
     */
    public static void studentStatusAverage() {
        // 현재 상태별 인원수를 출력하는 구문
        System.out.println("\n==================================");
        System.out.println("현재 상태별 인원수\n");

        int redAmount = 0;
        int greenAmount = 0;
        int yellowAmount = 0;

        // 모든 상태를 가져옴
        Status[] statusList = Status.values();

        // 각 상태별 학생 수를 계산
        for (Status status : statusList) {
            int count = (int) studentList.values().stream()
                    .filter(student -> student.getStatus() == status)
                    .count();

            // 상태에 따라 적절한 변수에 학생 수를 저장
            switch (status) {
                case RED:
                    redAmount = count;
                    break;
                case GREEN:
                    greenAmount = count;
                    break;
                case YELLOW:
                    yellowAmount = count;
                    break;
            }
        }

        // 상태별 학생 수의 합계를 출력 (예시)
        System.out.println("RED 학생 수: " + redAmount);
        System.out.println("GREEN 학생 수: " + greenAmount);
        System.out.println("YELLOW 학생 수: " + yellowAmount);
        System.out.println("==================================\n");

        System.out.print("조회할 상태를 입력해주세요 (RED, GREEN, YELLOW): ");
        String inputStatus = sc.next().toUpperCase();

        Status status;
        try {
            status = Status.valueOf(inputStatus);
        } catch (IllegalArgumentException e) {
            System.out.println("유효하지 않은 상태입니다. RED, GREEN, YELLOW 중 하나를 입력해야 합니다.");
            return;
        }

        // 해당 상태의 학생들 중 필수 과목 점수의 평균을 정수로 계산
        double averageScore = studentList.values().stream()
                .filter(student -> student.getStatus() == status)
                .flatMap(student -> scoreList.stream()
                        .filter(score -> score.getStudentId().equals(student.getStudentId()))
                        .filter(score -> {
                            Subject subject = subjectList.stream()
                                    .filter(s -> s.getSubjectId().equals(score.getSubjectId()))
                                    .findFirst().orElse(null);
                            return subject != null && subject.getSubjectType() == SubjectType.MANDATORY;
                        }))
                .mapToInt(Score::getScore)
                .average()
                .orElse(0.0);

        // 정수로 변환
        int averageScoreAsInt = (int) Math.floor(averageScore);
        Grade grade = GradeCalculator.calculateGrade(averageScoreAsInt, SubjectType.MANDATORY);

        System.out.println(status + " 상태의 학생들의 필수 과목 평균 등급 : " + grade + "("+averageScoreAsInt+"점)");
    }
}