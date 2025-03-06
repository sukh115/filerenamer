import java.io.File;
import java.util.Scanner;

public class RenameFile {
    private final String directoryPath; // 디렉터리 경로 저장
    private final ValidFile validFile; // 파일 및 디렉터리 유효성 검사 객체
    private final Scanner scanner; // 사용자 입력을 받기 위한 Scanner 객체
    private final FileUtils fileUtils; // 파일 유틸리티 클래스

    public RenameFile(String directoryPath) {
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
        while (true) {
            System.out.print("변경할 파일 이름 입력: ");
            String oldName = scanner.nextLine();
            System.out.print("새 파일 이름 입력: ");
            String newName = scanner.nextLine();

            if (fileUtils.runRename(oldName, newName)) { // 성공하면 true 반환
                break; // 파일 이름 변경 수행
            }

        }
    }

    // 4. 여러 파일 이름 한꺼번에 변경
    public void renameMultipleFiles() {
        while (true) {
            System.out.print("변경할 파일의 공통 이름 입력: ");
            String baseName = scanner.nextLine();

            System.out.print("시작 번호 입력: ");
            int startNumber = 0;

            if (scanner.hasNextInt()) {
                startNumber = scanner.nextInt();
                scanner.nextLine(); // 개행 문자 제거

                if (!validFile.isValidNumber(startNumber)) {
                    scanner.nextLine(); // 잘못된 입력 제거
                    continue; // 메시지 출력 없이 다시 입력받음
                }
            }else {
                System.out.println("양의 정수를 입력하세요");
                scanner.nextLine();
                continue;
            }



            File folder = new File(directoryPath);
            File[] files = folder.listFiles();
            int count = startNumber;
            boolean anyFileRenamed = false; // 변경된 파일이 하나라도 있는지 확인

            // 파일 이름 변경
            for (File file : files) {
                if (file.isFile()) {
                    String newName = count++ + "_" + baseName;

                    if (!fileUtils.runRename(file.getName(), newName)) {
                        System.out.println("파일 변경 실패 (건너뜀): " + file.getName());
                        continue; // 실패한 파일을 무시하고 다음 파일로 이동
                    }

                    anyFileRenamed = true; // 최소한 하나의 파일이 변경됨
                }
            }

            if (anyFileRenamed) {
                break; // 하나라도 변경 성공한 경우 루프 종료
            }
        }
    }
}


