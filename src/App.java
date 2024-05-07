import model.*;

import java.util.*;

public class App {
    //스캐너
    private static Scanner sc = new Scanner(System.in);

    //데이터 저장소
    private static HashMap<String, Student> studentList;    //수강생 리스트
    private static List<Subject> subjectList;               //과목 리스트
    private static List<Score> scoreList;                   //점수 리스트

    //index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";


    //메인 실행
    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다. 프로그램을 종료합니다.");
        }
    }

    //초기 데이터 생성
    private static void setInitData() {
        studentList = new HashMap<>();
        scoreList = new ArrayList<>();
        subjectList = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SubjectType.MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SubjectType.MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SubjectType.MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SubjectType.MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SubjectType.MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SubjectType.MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SubjectType.CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SubjectType.CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SubjectType.CHOICE
                )
        );
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
            System.out.println("0. 이전 메뉴로 돌아가기");
            System.out.println("1. 수강생 등록하기");
            System.out.println("2. 수강생 전체 목록 조회");
            System.out.println("3. 수강생 정보 수정하기");
            System.out.print("관리 메뉴를 선택하세요 : ");
            int input = sc.nextInt();

            switch (input) {
                case 0 -> flag = false;
                case 1 -> createStudent();
                case 2 -> studentInquiry();
                case 3 -> modifyStudentInfo();
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
        -JB-
        3. 수강생의 특정 과목 회차별 등급 조회
        4. 메인 화면 이동
         */

        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("수강생 관리 페이지");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 이전 메뉴로 돌아가기");
            System.out.print("관리 메뉴를 선택하세요 : ");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore();
                case 2 -> fixScore();
//                case 3 -> searchGrade();
                case 3 -> flag = false; // 우선은 3으로 이전메뉴를 설정했습니다.
                default -> {
                    System.out.println("잘못된 입력입니다.");
                }
            }
        }
    }


    private static void createScore(){
        System.out.println("\n==================================");
        System.out.println("조회할 학생의 등록코드를 입력해주세요 : ");
        String studentId = sc.next(); // ST1 -- ST2 같은 형식임

        // 학생의 고유 코드로 등록되어 있는 과목을 찾는다.
        Student student = studentList.get(studentId);
        if(student == null){
            System.out.println("해당 학생이 존재하지 않습니다.");
            // 오류 반환
        }
        // 학생이 수강하는 과목 출력
        System.out.println(student.getStudentName() + "이 수강하는 과목입니다.");
        for(String subject : student.getSubjects()){ // SU1, SU2 이런식으로 출력됩니다.
            System.out.print(subject + " ");
        }

        System.out.println("\n==================================");
        System.out.println("등록할 과목을 선택해주세요.");
        String subjectId = sc.next();
        // 입력한 과목이 유효한지 확인
        boolean foundSubject = false;
        for (Subject subject : subjectList) {
            if (subject.getSubjectId().equals(subjectId)) {
                foundSubject = true;
                break;
            }
        }

        if(!foundSubject){
            System.out.println("해당 과목은 등록되어 있지 않습니다.");
        }

        // 우선적인 회차와 스코어 등록
        System.out.println("등록하실 회차를 입력해주세요 : ");
        int round = sc.nextInt();
        System.out.println("등록하실 점수를 입력해주세요 : ");
        int scores = sc.nextInt();

        // 점수 등록
        scoreList.add(new Score(sequence("SCORE"),
                subjectId, studentId, round, scores));

    }

    private static void fixScore(){
        System.out.println("\n==================================");
        System.out.print("수정할 과목을 입력해주세요 :  ");
        String subjectName = sc.next();
        Student student = studentList.get(subjectName);


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
        for (int i = 1; i < subjectList.size(); i++) {
            System.out.println(i + ". " + subjectList.get(i).getSubjectName()
                    + ", (" + subjectList.get(i).getSubjectType().getValue() + ")");
        }

        boolean flag = true;
        boolean[] checkSubject = new boolean[subjectList.size() + 1]; //중복 확인 배열
        int mandatory = 0;      //필수과목
        int choice = 0;         //선택과목

        while (flag) {
            System.out.print("\n과목 번호 입력 : ");
            int inputNum = sc.nextInt();

            if (inputNum > 0 && inputNum <= subjectList.size()) {
                //이미 선택된 과목인지 확인하기
                if (!checkSubject[inputNum]) {
                    Subject selectedSubject = subjectList.get(inputNum - 1);

                    //선택한 과목의 Id값 넣어주기
                    student.setSubject(selectedSubject.getSubjectId());
                    System.out.println(selectedSubject.getSubjectName() + " 과목이 추가되었습니다.");

                    //선택한 과목 체크하기
                    checkSubject[inputNum] = true;

                    //필수과목인지 선택과목인지 확인하고, 값 올려주기
                    if (selectedSubject.getSubjectType().equals(SubjectType.MANDATORY)) mandatory++;
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
                if (mandatory >= 3 && choice >= 2) {
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

    private static void studentInquiry() {
        if(studentList.isEmpty()){
            System.out.println("\n==================================");
            System.out.print("등록된 수강생이 없습니다! ");
        } else {
            //Iterator 로 studentList 값 조회
            Iterator<Student> iterator = studentList.values().iterator();
            //studentList hashNext 로 다음 값이 없을 때까지 반복!
            while (iterator.hasNext()) {
                Student value = iterator.next();
                System.out.print("[" + value.getStudentId() + "]-" + value.getStudentName() + " | ");
            }
        }
    }

    //수강생 정보 수정 메소드
    private static void modifyStudentInfo() {
        System.out.println("\n==================================");
        //학생 아이디 받기
        System.out.print("상태를 관리할 수강생의 ID를 입력해 주세요 : ");
        String studentId = sc.next();

        if (!studentList.containsKey(studentId)) {
            //studentList에 일치하는 studentID가 없을 경우
            System.out.println("ID가 일치하는 수강생이 없습니다.");
        } else {
            boolean flag = true;
            while (flag) {
                System.out.println("\n==================================");
                Student findStudent = studentList.get(studentId);
                System.out.println(findStudent.getStudentName() + " 수강생 정보 변경하기");
                System.out.println("0. 이전 메뉴로 돌아가기");
                System.out.println("1. 이름 변경");
                System.out.println("2. 상태 변경");
                System.out.print("번호 입력 : ");
                int input = sc.nextInt();

                switch (input) {
                    case 0 -> flag = false;
                    case 1 -> modifyStudentName(findStudent);
                    case 2 -> modifyStudentStatus(findStudent);
                    default -> {
                        System.out.println("잘못된 입력입니다.");
                    }
                }
            }
        }
    }

    //수강생 이름 수정
    private static void modifyStudentName(Student student) {
        System.out.print("변경할 이름을 입력하세요 : ");
        String inputName = sc.next();

        //수강생 이름 수정
        student.setStudentName(inputName);

        //수정한 이름을 가진 student 객체 다시 studentList에 저장
        studentList.put(student.getStudentId(), student);
        System.out.println("수강생의 이름이 " + inputName + "(으)로 변경이 완료되었습니다!");
    }

    //수강생 상태 수정
    private static void modifyStudentStatus(Student student) {
        //Status List 생성
        Status[] statusList = Status.values();

        System.out.println("현재 " + student.getStudentName() + " 수강생의 상태 : " + student.getStatus());
        System.out.println("어떤 상태로 수정하시겠습니까?");

        for (Status status : statusList) {
            System.out.println(status.ordinal() + ". " + status.name());
        }

        System.out.print("상태 선택 : ");
        int inputNum = sc.nextInt();

        if (inputNum > statusList.length - 1 || inputNum < 0) {
            System.out.println("유효하지 않은 값입니다.");
        } else {
            //수강생 상태 수정
            student.setStatus(statusList[inputNum]);

            //수정한 상태를 가진 student 객체 다시 studentList에 저장
            studentList.put(student.getStudentId(), student);
            System.out.println("상태가 변경되었습니다!");
        }
    }

}