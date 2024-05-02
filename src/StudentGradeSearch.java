import java.util.HashMap;
import java.util.Scanner;

public class StudentGradeSearch {
    public static void main(String[] args) {
        // 학생 이름을 키로하고 과목 및 성적을 값으로하는 HashMap 생성
        HashMap<String, HashMap<String, Integer>> studentGrades = new HashMap<>();

        // 테스트용 데이터
        HashMap<String, Integer> johnGrades = new HashMap<>();
        johnGrades.put("수학", 90);
        johnGrades.put("과학", 85);
        johnGrades.put("역사", 95);
        studentGrades.put("존", johnGrades);

        HashMap<String, Integer> maryGrades = new HashMap<>();
        maryGrades.put("수학", 80);
        maryGrades.put("과학", 75);
        maryGrades.put("역사", 85);
        studentGrades.put("메리", maryGrades);

        // 사용자 입력을 읽기위한 Scanner 객체 생성
        Scanner scanner = new Scanner(System.in);

        // 사용자에게 학생 이름을 입력하도록 요청
        System.out.print("학생 이름을 입력하세요: ");
        String studentName = scanner.nextLine();

         // 학생이 레코드에 있는지 확인
        if (studentGrades.containsKey(studentName)) {
            // 사용자에게 과목을 입력하도록 요청
            System.out.print("과목을 입력하세요: ");
            String subject = scanner.nextLine();

            // 지정된 과목에 대한 학생의 성적 가져오기
            HashMap<String, Integer> grades = studentGrades.get(studentName);
            if (grades.containsKey(subject)) {
                int grade = grades.get(subject);
                System.out.println(studentName + " 학생의 " + subject + " 성적은: " + grade);
            } else {
                System.out.println(studentName + " 학생의 " + subject + " 성적이 없습니다.");
            }
        } else {
            System.out.println("학생을 찾을 수 없습니다.");
        }

        // Scanner 닫기
        scanner.close();
    }
}