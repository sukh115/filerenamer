import java.io.File;
import java.util.Scanner;

public class FileRenamer {
    private final String directoryPath; // 디렉터리 경로 저장
    private final ValidFile validFile; // 파일 및 디렉터리 유효성 검사 객체
    private final Scanner scanner; // 사용자 입력을 받기 위한 Scanner 객체
    private final FileUtils fileUtils; // 파일 유틸리티 클래스

    public FileRenamer(String directoryPath) {
        this.directoryPath = directoryPath;
        this.validFile = new ValidFile();
        this.scanner = new Scanner(System.in);
        this.fileUtils = new FileUtils(directoryPath, validFile);
    }

    // 2. 파일 이름 변경 작업 카테고리 선택
    public int selectRenameCategory() {

        if (!validFile.isValidDirectory(directoryPath)) {  // 디렉터리 유효성 검사
            return -1;
        }

        fileUtils.printFileList(); // 현재 파일 목록 출력

        int choice;
        while (true) {
            System.out.println("\n[1] 단일 파일 이름 변경");
            System.out.println("[2] 여러 파일 한꺼번에 변경");
            System.out.print("원하는 작업 번호 입력: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1 || choice == 2) {
                    return choice;
                }
            } else {
                scanner.nextLine();
            }
            System.out.println("올바른 숫자를 입력하세요 (1 또는 2).");
        }
    }

    // 3. 단일 파일 이름 변경 실행
    public void renameSingleFile() {
        System.out.print("변경할 파일 이름 입력: ");
        String oldName = scanner.nextLine();
        System.out.print("새 파일 이름 입력: ");
        String newName = scanner.nextLine();

        fileUtils.renameFile(oldName, newName); // 파일 이름 변경 수행
    }

    // 4. 여러 파일 이름 한꺼번에 변경
    public void renameMultipleFiles() {
        System.out.print("변경할 파일의 공통 이름 입력: ");
        String baseName = scanner.nextLine();
        System.out.print("시작 번호 입력: ");
        int startNumber = scanner.nextInt();
        scanner.nextLine();

        File folder = new File(directoryPath);
        File[] files = folder.listFiles();
        int count = startNumber;

        for (File file : files) {
            if (file.isFile()) {
                String newName = baseName + "_" + count++;  // 새로운 이름 생성
                fileUtils.renameFile(file.getName(), newName); // 파일 이름 변경 수행
            }
        }
        System.out.println("Batch Rename 완료!");
    }
}
