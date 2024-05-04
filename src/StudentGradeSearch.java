import model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentGradeSearch {
    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    // 데이터 저장소
    private static HashMap<String, Student> studentList;

    // 메인 실행
    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다. 프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentList = new HashMap<>();
    }

    // main view
    private static void displayMainView() {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 전체 회차 성적 조회");
            System.out.println("2. 프로그램 종료");
            System.out.print("메뉴를 선택하세요: ");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayScoreView();
                case 2 -> flag = false; // 프로그램 종료
                default -> System.out.println("잘못된 입력입니다.");
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    // 전체 회차 성적 조회
    private static void displayScoreView() {
        System.out.print("학생의 ID를 입력하세요: ");
        String studentId = sc.next();

        System.out.print("과목을 입력하세요 (Java, 객체지향, Spring, JPA, MySQL, 디자인 패턴, Spring Security, Redis, MongoDB): ");
        String subjectName = sc.next();

        if (studentList.containsKey(studentId)) {
            Student student = studentList.get(studentId);
            Map<Subject, Map<Integer, Score>> scores = student.getScores();
            boolean foundSubject = false;

            for (Map.Entry<Subject, Map<Integer, Score>> entry : scores.entrySet()) {
                Subject subject = entry.getKey();
                if (subject.getSubjectName().equals(subjectName)) {
                    foundSubject = true;
                    Map<Integer, Score> scoreMap = entry.getValue();
                    System.out.println("[" + subjectName + "] 성적 조회");
                    for (Map.Entry<Integer, Score> scoreEntry : scoreMap.entrySet()) {
                        int round = scoreEntry.getKey();
                        Score score = scoreEntry.getValue();
                        System.out.println("회차: " + round + ", 성적: " + score.getScore());
                    }
                    break;
                }
            }

            if (!foundSubject) {
                System.out.println("해당 과목의 성적이 없습니다.");
            }
        } else {
            System.out.println("학생을 찾을 수 없습니다.");
        }
    }
}