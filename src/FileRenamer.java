import java.io.File;
import java.util.Scanner;

public class FileRenamer {
    // 속성
    private String directoryPath;
    private ValidFile validFile;
    private Scanner scanner;
    private FileUtils fileUtils;

    // 생성자
    public FileRenamer(String directoryPath) {
        this.directoryPath = directoryPath;
        this.validFile = new ValidFile();
        this.scanner = new Scanner(System.in);
        this.fileUtils = new FileUtils(directoryPath, validFile);
    }

    // 파일 이름 변경 작업
    public void renameFiles() {
        if (!validFile.isDirectoryValid(directoryPath)) {
            return;
        }

        fileUtils.listFiles();

        int choice;
        while (true) {
            System.out.println("\n[1] 단일 파일 이름 변경");
            System.out.println("[2] 여러 파일 한꺼번에 변경 (Batch Rename)");
            System.out.print("원하는 작업 번호 입력: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // 개행 문자 제거
                if (choice == 1 || choice == 2) {
                    break;
                }
            } else {
                scanner.nextLine(); // 잘못된 입력 제거
            }
            System.out.println("올바른 숫자를 입력하세요 (1 또는 2).");
        }

        if (choice == 1) {
            renameSingleFile();
        } else {
            batchRenameFiles();
        }
    }

    // 단일 파일 변경 처리
    private void renameSingleFile() {
        String oldName, newName;

        //  기존 파일 이름 입력 (유효할 때까지 반복)
        while (true) {
            System.out.print("변경할 파일 이름 입력: ");
            oldName = scanner.nextLine();

            // 파일 이름 유효성 검사
            if (!validFile.isValidFileName(oldName)) {
                continue;
            }
            // 파일 유무 유효성검사
            if (!validFile.isFileValid(directoryPath + "/" + oldName)){
                continue;

            }
            break;
        }

        //  새 파일 이름 입력 (유효할 때까지 반복)
        while (true) {
            System.out.print("새 파일 이름 입력: ");
            newName = scanner.nextLine();
            // 파일 이름 유효성 검사
            if (!validFile.isValidFileName(newName)) {
                continue;
            }
            // 동일한 이름의 파일이 이미 존재하는지 검사
            File newFile = new File(directoryPath, newName);
            if (newFile.exists()) {
                System.out.println("이미 같은 이름의 파일이 존재합니다: " + newName);
                System.out.println("다른 파일 이름을 입력하세요.");
                continue;
            }
            break;
        }

        File oldFile = new File(directoryPath, oldName);
        File newFile = new File(directoryPath, newName);


        rename(oldFile, newFile);
    }

    // 여러 개 파일 Batch Rename 처리
    private void batchRenameFiles() {
        String baseName;
        int startNumber;

        //  공통 이름 입력
        while (true) {
            System.out.print("변경할 파일의 공통 이름 입력: ");
            baseName = scanner.nextLine();
            if (validFile.isValidFileName(baseName)) {
                break;
            }
        }

        //  시작 번호 입력
        while (true) {
            System.out.print("시작 번호 입력: ");
            if (scanner.hasNextInt()) {
                startNumber = scanner.nextInt();
                scanner.nextLine(); // 개행 문자 제거
                if (startNumber >= 0) {
                    break;
                }
            } else {
                scanner.nextLine(); // 잘못된 입력 제거
            }
            System.out.println("시작 번호는 0 이상이어야 합니다.");
        }

        File folder = new File(directoryPath);
        File[] files = folder.listFiles();

        //  변경할 파일이 없을 경우 다시 입력 요청
        if (files == null || files.length == 0) {
            System.out.println("변경할 파일이 없습니다. 다시 입력하세요.");
            batchRenameFiles();
            return;
        }

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

                // 🔹 기존에 같은 이름의 파일이 있는지 확인
                if (newFile.exists()) {
                    System.out.println("⚠이미 같은 이름의 파일이 존재합니다: " + newName);
                    continue;
                }

                rename(file, newFile);
                count++;
            }
        }
        System.out.println("Batch Rename 완료!");
    }

    // 파일 이름 변경 로직 (공통)
    private boolean rename(File oldFile, File newFile) {
        if (!validFile.isFileValid(oldFile.getPath())) {
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
