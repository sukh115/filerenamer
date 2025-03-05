import java.io.File;
import java.util.Scanner;

public class FileRenamer extends FileHandler {
    // 1. 속성
    private String directoryPath;
    private FileHandler fileHandler;
    private Scanner scanner;

    // 2. 생성자
    public FileRenamer(String directoryPath){
        this.directoryPath = directoryPath;
        this.fileHandler = new FileHandler();
        this.scanner = new Scanner(System.in);
    }

    // 3. 기능
    // 파일 이름 변경 작업을 여기서 처리 (단일 or 여러 개)
    public void renameFiles() {
        // 디렉터리 유효성 검사
        if (!isDirectoryValid(directoryPath)) {
            return;
        }

        //  파일 목록 출력
        FileUtils fileUtils = new FileUtils(directoryPath, fileHandler);
        fileUtils.listFiles();

        //  사용자 선택 (1: 단일 변경, 2: Batch Rename)
        System.out.println("\n[1] 단일 파일 이름 변경");
        System.out.println("[2] 여러 파일 한꺼번에 변경 (Batch Rename)");
        System.out.print("원하는 작업 번호 입력: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 제거

        if (choice == 1) {
            renameSingleFile();
        } else if (choice == 2) {
            batchRenameFiles();
        } else {
            System.out.println("잘못된 입력입니다.");
        }
    }

    //  단일 파일 변경 처리
    private void renameSingleFile() {
        System.out.print("변경할 파일 이름 입력: ");
        String oldName = scanner.nextLine();

        System.out.print("새 파일 이름 입력: ");
        String newName = scanner.nextLine();

        File oldFile = new File(directoryPath, oldName);
        File newFile = new File(directoryPath, newName);
        rename(oldFile, newFile);
    }

    // 🔹 여러 개 파일 Batch Rename 처리
    private void batchRenameFiles() {
        System.out.print("변경할 파일의 공통 이름 입력: ");
        String baseName = scanner.nextLine();

        System.out.print("시작 번호 입력: ");
        int startNumber = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 제거

        File folder = new File(directoryPath);
        File[] files = folder.listFiles();

        int count = startNumber;
        for (File file : files) {
            if (file.isFile()) {
                String extension = "";
                int dotIndex = file.getName().lastIndexOf(".");
                if (dotIndex != -1) {
                    extension = file.getName().substring(dotIndex);
                }

                String newName = baseName + "_" + count + extension;
                File newFile = new File(directoryPath, newName);
                rename(file, newFile);
                count++;
            }
        }
        System.out.println("Batch Rename 완료!");
    }

    // 파일 이름 변경 로직 (공통)
    private boolean rename(File oldFile, File newFile) {
        if (!isFileValid(oldFile.getPath())) {
            return false;
        }

        if (oldFile.renameTo(newFile)) {
            System.out.println("파일 이름 변경 성공: " + oldFile.getName() + " → " + newFile.getName());
            return true;
        } else {
            System.out.println("파일 이름 변경 실패: " + oldFile.getName());
            return false;
        }
    }
}
