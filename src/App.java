/*import model.Score;
import model.Student;
import model.Subject;
import model.SubjectType;*/

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
                default -> System.out.println("잘못된 입력입니다.\n되돌아갑니다!");

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
            System.out.println("2. 수강생 아이디 검색");
            System.out.println("3. 수강생 전체 목록 조회");
            System.out.println("4. 수강생 정보 수정하기");
            System.out.println("5. 수강생 상태별 목록 조회");
            System.out.print("관리 메뉴를 선택하세요 : ");
            int input = sc.nextInt();

            switch (input) {
                case 0 -> flag = false;
                case 1 -> createStudent();
                case 2 -> findStudentId();
                case 3 -> studentInquiry();
                case 4 -> modifyStudentInfo();
                case 5 -> studentStatusInquiry();
                default -> {
                    System.out.println("잘못된 입력입니다.");
                }
            }
        }
    }

    //점수 관리 뷰
    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("수강생 관리 페이지");
            System.out.println("0. 이전 메뉴로 돌아가기");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 수강생의 과목별 평균 등급 조회");
            System.out.print("관리 메뉴를 선택하세요 : ");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore();
                case 2 -> updateScore();
                case 3 -> displayGradeView();
                case 4 -> averageInquiry();
                case 0 -> flag = false;
                default -> {
                    System.out.println("잘못된 입력입니다.");
                }
            }
        }
    }


    private static void createScore() {
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
            System.out.println("조회할 학생의 등록코드를 입력해주세요 : ");
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
            System.out.println("등록할 과목을 선택해주세요.");
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
            System.out.println("등록하실 회차를 입력해주세요 : ");
            round = sc.nextInt();
            System.out.println("등록하실 점수를 입력해주세요 : ");
            scores = sc.nextInt();

            if ((round > 10) || (round < 1)) {
                System.out.println("회차는 1회차부터 10회차까지 있습니다. 다시 입력해주세요 :)");
            } else if ((scores < 0) || (scores > 100)) {
                System.out.println("점수는 0점부터 100점까지 입력이 가능합니다. 다시 입력해주세요 :)");
            } else {
                validInput = false;
            }
        }

        Score test = new Score(sequence(INDEX_TYPE_SCORE),
                subjectId, studentId, round, scores, type);
        // 점수 등록
        Score test = new Score(sequence(INDEX_TYPE_SCORE), subjectId, studentId, round, scores, type);
        scoreList.add(test);
        System.out.println("학생 : " + student.getStudentName());
        System.out.println("과목명 : " + sbName + "에 " + round + "회차 " + scores + "(" + test.getGrade() + ")을 등록했습니다.");
        scoreList.add(test);

    }

    private static void displayGradeView() {
        System.out.print("학생 ID를 입력하세요: ");
        String studentId = sc.next();

        // 학생 이름과 수강 과목 출력
        if (!studentList.containsKey(studentId)) {
            System.out.println("해당 학생이 존재하지 않습니다.");
            return;
        }
        String studentName = studentList.get(studentId).getStudentName();
        System.out.println(studentName+"학생이 수강중인 과목 입니다.");

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
        System.out.println("=== " + getSubjectNameById(subjectId) + " 과목의 성적 ===");         //  과목 이름을 함께 출력

        for (int round = 1; ; round++) {
            Grade grade = findGrade(subjectId, studentId, round);
            if (grade == Grade.N) {
                break; // 해당 회차의 학점이 없으면 중지
            }
            System.out.println(round + "회차: " + grade);
        }
    }
    // 과목 ID를 기반으로 과목 이름을 가져옴
    private static String getSubjectNameById(String subjectId) {
        // 과목 리스트를 반복하면서 과목 ID와 일치하는 과목을 찾음
        for (Subject subject : subjectList) {
            if (subject.getSubjectId().equals(subjectId)) {
               return subject.getSubjectName();  /* 과목 ID도 같이 출력 할려면 : subject.getSubjectId() 추가 */
            }
        }
        // 일치하는 과목 ID가 없을 경우
        return "알 수 없는 과목";
    }

    private static Grade findGrade(String subjectId, String studentId, int round) {
        for (Score score : scoreList) {
            if (score.getSubjectId().equals(subjectId) && score.getStudentId().equals(studentId) && score.getRound() == round) {
                // 저장된 학점을 가져옴
                return score.getGrade();
            }
        }
        // 해당 회차에 대한 학점이 없을 경우 기본값으로 N을 반환
        return Grade.N;
    }


    private static void averageInquiry() {
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

    private static void displayGradeView() {
        System.out.print("학생의 ID를 입력하세요: ");
        String studentId = sc.next();

        System.out.print("과목을 입력하세요 (Java, 객체지향, Spring, JPA, MySQL, 디자인 패턴, Spring Security, Redis, MongoDB): ");
        String subjectName = sc.next();

        // 학생을 검색합니다.
        Student student = studentList.get(studentId);

        // 학생이 존재하는지 확인합니다.
        if (student == null) {
            System.out.println("해당 학생을 찾을 수 없습니다.");
            return;
        }

        // 주어진 과목의 점수를 검색합니다.
//        Map<Integer, Score> scores = student.getScores().get(subjectName);

        // 주어진 과목에 대한 점수가 있는지 확인합니다.
//        if (scores == null || scores.isEmpty()) {
//            System.out.println("해당 학생의 성적이 없습니다.");
//            return;
//        }
//
//        // 점수를 출력합니다.
//        System.out.println("[" + subjectName + "] 성적 조회");
//        for (Map.Entry<Integer, Score> entry : scores.entrySet()) {
//            int round = entry.getKey();
//            Score score = entry.getValue();
//            System.out.println("회차: " + round + ", 성적: " + score.getScore());
//        }
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

    private static void findStudentId() {
        System.out.println("\n==================================");
        System.out.print("ID를 찾을 수강생 이름을 입력해 주세요 : ");
        String input = sc.next();

        List<Map.Entry<String, Student>> findStudentList = studentList.entrySet().stream()
                .filter(student -> input.equals(student.getValue().getStudentName()))
                .toList();

        if(findStudentList.isEmpty()) {
            System.out.println("해당하는 수강생이 없습니다.");
        } else {
            System.out.println(input + " 수강생들의 ID 입니다");
            for (Map.Entry<String, Student> findStudent : findStudentList) {
                System.out.println(findStudent.getKey());
            }
            System.out.println("조회가 완료되었습니다!");
        }
    }


    private static void studentInquiry() {
        if (studentList.isEmpty()) {
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

    //수강생 상태별 목록 조회 메소드
    private static void studentStatusInquiry() {
        System.out.println("\n==================================");
        System.out.println("상태별 수강생 조회하기");

        Status[] statusList = Status.values();
        for (Status status : statusList) {
            System.out.println(status.ordinal() + ". " + status.name());
        }

        System.out.print("번호 입력 : ");
        int input = sc.nextInt();

        if (input > statusList.length - 1 || input < 0) {
            System.out.println("유효하지 않은 값입니다.");
        } else {
            //Stream 활용하여 상태가 같은 studentList 가져오기
            List<Map.Entry<String, Student>> studentStatusList =
                    studentList.entrySet().stream()
                            .filter(student -> student.getValue().getStatus().ordinal() == input)
                            .toList();

            if (studentStatusList.isEmpty()) {
                System.out.println("해당 상태의 학생이 없습니다.");
            } else {
                System.out.println("\n" + statusList[input] + " 상태 수강생 조회");
                for (Map.Entry<String, Student> findStudent : studentStatusList) {
                    System.out.println(findStudent.getKey() + " : " + findStudent.getValue().getStudentName());
                }

                System.out.println("조회가 완료되었습니다!");
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

    private static void updateScore() {
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

        // 과목의 SubjectType 찾기
        SubjectType subjectType = SubjectType.MANDATORY;
        for (Subject subject : subjectList) {
            if (subject.getSubjectId().equals(subjectId)) {
                subjectType = subject.getSubjectType();
                break;
            }
        }

        boolean updated = false;
        for (Score score : scores) {
            if (score.getSubjectId().equals(subjectId) && score.getRound() == round) {
                System.out.print("새로운 점수를 입력해 주세요: ");
                int newScore = sc.nextInt();

                Grade newGrade = GradeCalculator.calculateGrade(newScore, subjectType);

                score.setGrade(newGrade);
                score.setScore(newScore);
                System.out.println("점수가 수정되었습니다.");
                updated = true;
                break;
            }
        }

        if (!updated) {
            System.out.println("해당 과목의 점수 정보를 찾을 수 없습니다.");
        }
    }

    private static List<Score> findScoresByStudentId(String studentId) {
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


    private static void displayScores(List<Score> scores) {
        for (Score score : scores) {
            System.out.printf("과목 ID: %s, 회차: %d, 점수: %d, 등급: %s\n", score.getSubjectId(), score.getRound(), score.getScore(), score.getGrade());
        }
    }


}