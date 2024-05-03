import model.Score;
import model.Student;
import model.Subject;
import model.SubjectType;

import java.util.*;

import static model.Score.scoreMap;


public class App {
    //스캐너
    private static Scanner sc = new Scanner(System.in);

    //데이터 저장소
    private static HashMap<String, Student> studentList = new HashMap<>();
    private static List<Subject> subjectList = new ArrayList<>();

    //index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";
    private static HashMap<String, HashMap<String, HashMap<String, String>>> studentGrades = new HashMap<>();


    //메인 실행
    public static void main(String[] args) {
        setInitData();
            displayMainView();

    }
    //초기 데이터 생성
    private static void setInitData() {
        // 예시 과목
        subjectList.add(new Subject("S001", "Java", SubjectType.MANDATORY));
        subjectList.add(new Subject("S002", "Database", SubjectType.MANDATORY));
        subjectList.add(new Subject("S003", "Web Development", SubjectType.CHOICE));

        // 예시 학생
        Student testStudent1 = new Student(sequence(INDEX_TYPE_STUDENT), "Alice");
        studentList.put(testStudent1.getStudentId(), testStudent1);

        // 예시 점수
        List<Score> scoresForAlice = new ArrayList<>();
        scoresForAlice.add(new Score(sequence(INDEX_TYPE_SCORE), "S001", testStudent1.getStudentId(), 1, 88)); // Java, 1st round, score 88
        scoresForAlice.add(new Score(sequence(INDEX_TYPE_SCORE), "S001", testStudent1.getStudentId(), 2, 92)); // Java, 2nd round, score 92
        scoresForAlice.add(new Score(sequence(INDEX_TYPE_SCORE), "S002", testStudent1.getStudentId(), 1, 85)); // Database, 1st round, score 85
        scoresForAlice.add(new Score(sequence(INDEX_TYPE_SCORE), "S003", testStudent1.getStudentId(), 1, 90)); // Web Dev, 1st round, score 90

        scoreMap.put(testStudent1.getStudentId(), scoresForAlice);


    }

    // index 자동 증가
    private static String sequence(String type) {
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

    //main view
    private static void displayMainView() {
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
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    //수강생 관리 뷰
    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("수강생 관리 페이지");
            System.out.println("1. 수강생 등록하기");
            System.out.println("2. 수강생 전체 목록 조회");
            System.out.println("3. 이전 메뉴로 돌아가기");
            System.out.print("관리 메뉴를 선택하세요 : ");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent();
                //case 2 만들어서 추가해주세요!
                case 3 -> flag = false;
                default -> {
                    System.out.println("잘못된 입력입니다.");
                }
            }
        }
    }


    //점수 관리 뷰
    private static void displayScoreView() {
        /*
        1. 수강생의 과목별 시험 회차 및 점수 등록
        2. 수강생의 과목별 회차 점수 수정
        3. 수강생의 특정 과목 회차별 등급 조회
        4. 메인 화면 이동
         */
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> updateScore(); // 점수 관리
//                case 3 -> 3. 수강생의 특정 과목 회차별 등급 조회 //
                case 4 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                }
            }
        }
    }

    private static void createStudent() {
        System.out.println("\n==================================");
        System.out.print("등록할 수강생 이름을 입력해 주세요 : ");
        String name = sc.next();

        //새로운 수강생 객체 생성
        Student student = new Student(sequence(INDEX_TYPE_STUDENT), name);

        System.out.println("\n필수 과목 최소 3개, 선택 과목 최소 2개를 선택하셔야 합니다.");
        System.out.println("\n=== 수강할 과목을 선택해 주세요 ===");
        System.out.println("0. 수강 과목 선택 종료하기");
        //subjectList에 저장된 과목들 출력
        for (int i = 1; i<subjectList.size(); i++) {
            System.out.println(i + ". " + subjectList.get(i).getSubjectName()
                    + ", (" + subjectList.get(i).getSubjectType().getValue() + ")");
        }

        boolean flag = true;
        boolean[] checkSubject = new boolean[subjectList.size()+1]; //중복 확인 배열
        int mandatory = 0;      //필수과목
        int choice = 0;         //선택과목

        while (flag) {
            System.out.print("\n과목 번호 입력 : ");
            int inputNum = sc.nextInt();

            if(inputNum > 0 && inputNum <= subjectList.size()) {
                //이미 선택된 과목인지 확인하기
                if(!checkSubject[inputNum]) {
                    Subject selectedSubject = subjectList.get(inputNum - 1);
                    student.setSubject(selectedSubject);
                    System.out.println(selectedSubject.getSubjectName() + " 과목이 추가되었습니다.");

                    //선택한 과목 체크하기
                    checkSubject[inputNum] = true;

                    //필수과목인지 선택과목인지 확인하고, 값 올려주기
                    if(selectedSubject.getSubjectType().equals(SubjectType.MANDATORY)) mandatory++;
                    else choice++;

                    System.out.println("\n현재 선택된 과목 갯수입니다.");
                    System.out.println("필수과목 : " + mandatory);
                    System.out.println("선택과목 : " + choice);
                } else {
                    //이미 선택된 과목이면 알려 주고 다시 선택도록
                    System.out.println("이미 선택된 과목입니다.");
                }

            } else if (inputNum == 0) {
                //프로그램 종료 원하면, 필수과목과 선택과목 최소 개수가 채워졌는지 확인
                if(mandatory >= 3 && choice >= 2) {
                    flag = false;
                } else {
                    System.out.println("수강할 과목 개수가 부족합니다. 더 선택해 주세요.");
                }

                System.out.println("\n현재 선택된 과목 갯수입니다.");
                System.out.println("필수과목 : " + mandatory);
                System.out.println("선택과목 : " + choice);
            } else {
                System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
            }
        }

        //studentList에 수강생 등록
        studentList.put(student.getStudentId(), student);
        System.out.println("\n" + name + " 수강생 등록 성공!");
    }


    private static void updateScore() {
        System.out.println("\n==================================");
        System.out.print("수정할 점수의 수강생 이름을 입력해 주세요: ");
        String studentName = sc.next();

        // 학생 정보 출력
        String studentId = findStudentIdByName(studentName);
        if (studentId == null) {
            System.out.println("학생 정보를 찾을 수 없습니다.");
            return;
        }
        Student student = studentList.get(studentId);
        System.out.println("학생 정보 : " + student.getStudentName() + " - " + student.getStudentId());
        // 점수 정보 가져오기
        List<Score> scores = scoreMap.get(studentId);
        if (scores == null) {
            System.out.println("등록된 점수 정보가 없습니다.");
            return;
        }
        Iterator<Score> scoreIterator = scores.iterator();
        while (scoreIterator.hasNext()) {
            Score score = scoreIterator.next();
            System.out.printf("과목 ID: " + score.getSubjectId()+ "\n" + "회차: " + score.getRound()+ "\n" + "점수: " + score.getScore()+ "\n");
        }
        System.out.print("수정할 점수의 과목 ID를 입력해 주세요: ");
        String subjectId = sc.next();
        System.out.print("회차를 입력해 주세요: ");
        int round = sc.nextInt();

        // 해당 점수 찾기
        boolean found = false;
        for (Score score : scores) {
            if (score.getSubjectId().equals(subjectId) && score.getRound() == round) {
                System.out.printf("현재 점수: " + score.getScore() + "\n");
                found = true;
                System.out.print("새로운 점수를 입력해 주세요: ");
                int newScore = sc.nextInt();
                score.setScore(newScore);
                System.out.println("점수가 수정되었습니다.");
                break;
            }
        }

        if (!found) {
            System.out.println("해당 과목의 점수 정보를 찾을 수 없습니다.");
        }
    }
    private static String findStudentIdByName(String name) {
        for (Map.Entry<String, Student> entry : studentList.entrySet()) {
            if (entry.getValue().getStudentName().equalsIgnoreCase(name)) {
                return entry.getKey();
            }
        }
        return null;
    }
}