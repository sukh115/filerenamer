import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 디렉터리 입력 후 확인
        System.out.print("파일이 있는 디렉터리 경로 입력: ");
        String directoryPath = scanner.nextLine();
        FileRenamer fileRenamer = new FileRenamer(directoryPath);

        // 2. 파일 이름 변경 작업 카테고리 고르기
        int choice = fileRenamer.selectRenameCategory();

        // 3. 선택한 작업 실행
        if (choice == 1) {
            fileRenamer.renameSingleFile(); // 3. 단일 파일 이름 변경
        } else if (choice == 2) {
            fileRenamer.renameMultipleFiles(); // 4. 여러 파일 이름 한꺼번에 변경
        } else {
            System.out.println("파일 변경 작업이 취소되었습니다.");
        }

        scanner.close();
    }
}
