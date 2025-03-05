import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("파일이 있는 디렉터리 경로 입력: ");
        String directoryPath = scanner.nextLine();

        // 파일 목록 출력
        FileUtils.listFiles(directoryPath);

        System.out.print("변경할 파일 이름 입력: ");
        String oldName = scanner.nextLine();

        System.out.print("새 파일 이름 입력: ");
        String newName = scanner.nextLine();

        // 파일 이름 변경 실행
        FileRenamer.renameFile(directoryPath, oldName, newName);

        scanner.close();
    }
}