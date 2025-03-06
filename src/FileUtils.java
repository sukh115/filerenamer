import java.io.File;

public class FileUtils {
    private final String directoryPath; // 디렉터리 경로 저장
    private final ValidFile validFile; // 파일 유효성 검사 객체

    public FileUtils(String directoryPath, ValidFile validFile) {
        this.directoryPath = directoryPath;
        this.validFile = validFile;
    }

    // 파일 목록 출력
    public void printFileList() {
        if (!validFile.isValidDirectory(directoryPath)) {   // 디렉터리 유효성 검사
            return;
        }

        File folder = new File(directoryPath);
        File[] files = folder.listFiles();

        System.out.println(directoryPath + " 내 파일 목록:");
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(" - " + file.getName());  // 파일 이름 출력
            }
        }
    }

    // 파일 이름 변경 실행
    public void renameFile(String oldName, String newName) {
        File oldFile = new File(directoryPath, oldName);
        File newFile = new File(directoryPath, newName);

        // 파일 유효성 검사 및 중복 파일 확인
        if (!validFile.isValidFile(oldFile.getPath()) || newFile.exists()) {
            System.out.println("이름 변경 실패: " + oldName + " → " + newName);
            return;
        }

        // 파일 이름 변경 실행
        if (oldFile.renameTo(newFile)) {
            System.out.println("파일 이름 변경 성공: " + oldName + " → " + newName);
        }
    }
}
