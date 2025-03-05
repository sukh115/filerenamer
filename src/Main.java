import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils();
        FileRenamer fileRenamer = new FileRenamer();
        Scanner scanner = new Scanner(System.in);

        System.out.print("파일이 있는 디렉터리 경로 입력: ");
        String directoryPath = scanner.nextLine();


        // 파일 이름 변경 실행
        fileRenamer.renameFiles(directoryPath, scanner);

        scanner.close();
    }
}