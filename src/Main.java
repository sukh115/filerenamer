import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exit = "";

        while (!exit.equalsIgnoreCase("exit")) { // "exit" 입력 전까지 반복
            // 1. 디렉터리 입력 후 확인
            System.out.print("파일이 있는 디렉터리 경로 입력 (종료하려면 'exit' 입력): ");
            String directoryPath = scanner.nextLine();

            if (directoryPath.equalsIgnoreCase("exit")) {
                break; // "exit" 입력 시 while 루프 종료
            }

            RenameFile renameFile = new RenameFile(directoryPath);

            // 2. 파일 이름 변경 작업 카테고리 고르기
            int choice = renameFile.selectRenameCategory();

            // 3. 선택한 작업 실행
            if (choice == 1) {
                renameFile.renameSingleFile();
            } else if (choice == 2) {
                renameFile.renameMultipleFiles();
            }

        }

        System.out.println("프로그램을 종료합니다.");
        scanner.close();
    }
}
