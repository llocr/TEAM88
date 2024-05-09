import model.*;


public class App {
    public static void main(String[] args) {
        DataManger dataManger = new DataManger();
        StudentManager studentManager = new StudentManager(dataManger);
        try {
            studentManager.displayMainView();
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다. 프로그램을 종료합니다.");
        }
    }

}