
import java.util.HashMap;
import java.util.Scanner;

public class StudentGradeSearch {
    public static void main(String[] args) {
        // 학생 이름을 key로하고 세션 및 과목별 성적을 value으로하는 HashMap 생성
        HashMap<String, HashMap<String, HashMap<String, String>>> studentGrades = new HashMap<>();

        // 테스트용 샘플 데이터
        HashMap<String, HashMap<String, String>> DoyoungSessions = new HashMap<>();
        HashMap<String, String> DoyoungGradesSession1 = new HashMap<>();
        DoyoungGradesSession1.put("객체지향언어", "A");
        DoyoungGradesSession1.put("자바", "B");
        DoyoungGradesSession1.put("SQL", "D");
        DoyoungSessions.put("1회차", DoyoungGradesSession1);

        HashMap<String, String> DoyoungGradesSession2 = new HashMap<>();
        DoyoungGradesSession2.put("객체지향언어", "F");
        DoyoungGradesSession2.put("자바", "B");
        DoyoungGradesSession2.put("SQL", "A");
        DoyoungSessions.put("2회차", DoyoungGradesSession2);

        HashMap<String, String> DoyoungGradesSession3 = new HashMap<>();
        DoyoungGradesSession3.put("객체지향언어", "D");
        DoyoungGradesSession3.put("자바", "C");
        DoyoungGradesSession3.put("SQL", "B");
        DoyoungSessions.put("3회차", DoyoungGradesSession3);

        studentGrades.put("도영", DoyoungSessions);

        HashMap<String, HashMap<String, String>> JennieSessions = new HashMap<>();
        HashMap<String, String> JennieGradesSession1 = new HashMap<>();
        JennieGradesSession1.put("객체지향언어", "A");
        JennieGradesSession1.put("자바", "B");
        JennieGradesSession1.put("SQL", "C");
        JennieSessions.put("1회차", JennieGradesSession1);

        HashMap<String, String> JennieGradesSession2 = new HashMap<>();
        JennieGradesSession2.put("객체지향언어", "F");
        JennieGradesSession2.put("자바", "D");
        JennieGradesSession2.put("SQL", "C");
        JennieSessions.put("2회차", JennieGradesSession2);

        HashMap<String, String> JennieGradesSession3 = new HashMap<>();
        JennieGradesSession3.put("객체지향언어", "B");
        JennieGradesSession3.put("자바", "A");
        JennieGradesSession3.put("SQL", "B");
        JennieSessions.put("3회차", JennieGradesSession3);

        studentGrades.put("제니", JennieSessions);

        // 사용자 입력을 읽기위한 Scanner 객체 생성
        Scanner scanner = new Scanner(System.in);

        // 사용자에게 학생 이름 입력 요청
        System.out.print("학생의 이름을 입력하세요: ");
        String studentName = scanner.nextLine();

        // 사용자에게 과목 입력 요청
        System.out.print("과목을 입력하세요 (객체지향언어, 자바, SQL): ");
        String subject = scanner.nextLine();

        // 학생 레코드 확인
        if (studentGrades.containsKey(studentName)) {
            HashMap<String, HashMap<String, String>> sessions = studentGrades.get(studentName);
            boolean foundSubject = false;       // 해당 과목을 찾았는지 여부 변수 초기화

            // 회차별 순서를 위한 변수
            int sessionNumber = 1;

            // 학생의 모든 세션 반복
            for (String session : sessions.keySet()) {
                HashMap<String, String> grades = sessions.get(session);
                if (grades.containsKey(subject)) {           // 해당 과목이 세션에 있는지 확인
                    foundSubject = true;                         // 과목을 찾았음을 표시
                    String grade = grades.get(subject);        // 해당 과목의 성적 가져오기
                    System.out.println(studentName + " 학생의 " + sessionNumber + "회차의 " + subject + " 성적은: " + grade);        // 성적 출력
                }
                sessionNumber++; // 다음 회차로 이동
            }

            if (!foundSubject) {        // 과목을 찾지 못한 경우
                System.out.println(studentName + " 학생의 " + subject + " 성적이 없습니다.");
            }
        } else {
            System.out.println("학생을 찾을 수 없습니다.");
        }

        // Scanner 닫기
        scanner.close();
    }
}
