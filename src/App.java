import manager.DataManger;
import manager.ScoreManager;
import manager.StudentManager;

import model.*;
import java.util.*;
public class App {
    public static void main(String[] args) {
        DataManger dataManger = new DataManger();
        StudentManager studentManager = new StudentManager(dataManger);
        ScoreManager scoreManager = new ScoreManager(dataManger);

        try {
            displayMainView(studentManager, scoreManager);
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다. 프로그램을 종료합니다.");
        }
    }

    //main view
    private static void displayMainView(StudentManager studentManager, ScoreManager scoreManager) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(studentManager, sc); // 수강생 관리
                case 2 -> displayScoreView(scoreManager, sc); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> System.out.println("잘못된 입력입니다.\n되돌아갑니다!");

            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    //수강생 관리 뷰
    private static void displayStudentView(StudentManager studentManager, Scanner sc) {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("수강생 관리 페이지");
            System.out.println("0. 이전 메뉴로 돌아가기");
            System.out.println("1. 수강생 등록하기");
            System.out.println("2. 수강생 아이디 검색");
            System.out.println("3. 수강생 전체 목록 조회");
            System.out.println("4. 수강생 정보 수정하기");
            System.out.println("5. 수강생 상태별 목록 조회");
            System.out.println("6. 수강생 삭제하기");
            System.out.print("관리 메뉴를 선택하세요 : ");
            int input = sc.nextInt();

            switch (input) {
                case 0 -> flag = false;
                case 1 -> studentManager.createStudent();
                case 2 -> studentManager.findStudentId();
                case 3 -> studentManager.studentInquiry();
                case 4 -> studentManager.modifyStudentInfo();
                case 5 -> studentManager.studentStatusInquiry();
                case 6 -> studentManager.deleteStudent();
                default -> {
                    System.out.println("잘못된 입력입니다.");
                }
            }
        }
    }

    //점수 관리 뷰
    private static void displayScoreView(ScoreManager scoreManager, Scanner sc) {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("수강생 관리 페이지");
            System.out.println("0. 이전 메뉴로 돌아가기");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 수강생의 과목별 평균 등급 조회");
            System.out.println("5. 상태별 수강생들의 평균 등급 조회");
            System.out.print("관리 메뉴를 선택하세요 : ");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> scoreManager.createScore();
                case 2 -> scoreManager.updateScore();
                case 3 -> scoreManager.displayGradeView();
                case 4 -> scoreManager.averageInquiry();
                case 5 -> scoreManager.studentStatusAverage();
                case 0 -> flag = false;
                default -> {
                    System.out.println("잘못된 입력입니다.");
                }
            }
        }
    }
}