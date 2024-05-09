package manager;

import type.Status;
import model.Student;
import model.Subject;
import type.SubjectType;

import java.util.*;

import static manager.DataManger.*;

public class StudentManager {
    public static Scanner sc = new Scanner(System.in);

    public static void createStudent() {
        System.out.println("\n==================================");
        System.out.print("등록할 수강생 이름을 입력해 주세요 : ");
        String name = sc.next();

        //새로운 수강생 객체 생성
        Student student = new Student(DataManger.sequence(DataManger.INDEX_TYPE_STUDENT), name);

        System.out.println("\n필수 과목 최소 3개, 선택 과목 최소 2개를 선택하셔야 합니다.");
        System.out.println("\n=== 수강할 과목을 선택해 주세요 ===");
        System.out.println("0. 수강 과목 선택 종료하기");
        //subjectList에 저장된 과목들 출력
        for (int i = 0; i < subjectList.size(); i++) {
            System.out.println(i+1 + ". " + subjectList.get(i).getSubjectName()
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

    public static void findStudentId() {
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


    public static void studentInquiry() {
        if (studentList.isEmpty()) {
            System.out.println("\n==================================");
            System.out.println("등록된 수강생이 없습니다!");
        } else {
            System.out.println("\n==================================");
            System.out.println("등록된 수강생 목록:");

            Iterator<Map.Entry<String, Student>> iterator = studentList.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Student> entry = iterator.next();
                Student student = entry.getValue();
                System.out.print("[" + student.getStudentId() + "]-" + student.getStudentName() +
                        " ,[STATUS]-" + student.getStatus());

                // 선택한 과목 출력
                List<String> subjects = student.getSubjects();
                if (!subjects.isEmpty()) {
                    System.out.print(" ,[SUBJECTS]-");
                    for (int i = 0; i < subjects.size(); i++) {
                        String subjectId = subjects.get(i);
                        // subjectList에서 해당 과목 ID에 해당하는 과목을 찾아서 출력
                        for (Subject subject : subjectList) {
                            if (subject.getSubjectId().equals(subjectId)) {
                                System.out.print(subject.getSubjectName());
                                if (i < subjects.size() - 1) {
                                    System.out.print(", ");
                                } else {
                                    System.out.print("");
                                }
                                break;
                            }
                        }
                    }
                }
                System.out.println();
            }
        }
    }

    //수강생 상태별 목록 조회 메소드
    public static void studentStatusInquiry() {
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
    public static void modifyStudentInfo() {
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
    public static void modifyStudentName(Student student) {
        System.out.print("변경할 이름을 입력하세요 : ");
        String inputName = sc.next();

        //수강생 이름 수정
        student.setStudentName(inputName);

        //수정한 이름을 가진 student 객체 다시 studentList에 저장
        studentList.put(student.getStudentId(), student);
        System.out.println("수강생의 이름이 " + inputName + "(으)로 변경이 완료되었습니다!");
    }

    //수강생 상태 수정
    public static void modifyStudentStatus(Student student) {
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

    public static void deleteStudent() {
        System.out.print("\n삭제할 수강생 ID를 입력해 주세요: ");
        String studentId = sc.next();
        if (studentList.containsKey(studentId)) {
            studentList.remove(studentId);
            scoreList.removeIf(score -> score.getStudentId().equals(studentId));
            System.out.println("수강생 및 관련 점수가 삭제되었습니다.");
        }
        else {
            System.out.println("존재하지 않는 수강생입니다.");
        }
    }

}
