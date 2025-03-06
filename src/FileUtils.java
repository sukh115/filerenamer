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
    public boolean runRename(String oldName, String newName ) {
        File oldFile = new File(directoryPath, oldName);
        File newFile = new File(directoryPath, newName);

        // 파일 유효성 검사
        if (!validFile.isValidFile(oldFile.getPath())) {
            System.out.println("존재하지 않는 파일입니다 : " + oldName);
            return false;
        }
        // 파일 중복 검사
        if (newFile.exists()){
            System.out.println("이미 같은 이름의 파일이 존재합니다 : " + newName);
            return false;
        }
        // 파일 이름 유효성 검사 (공백 + 금지된 문자 체크)
        if (!validFile.isValidFileName(newName)){
            return false;
        }


        // 파일 이름 변경 실행
        if (oldFile.renameTo(newFile)) {
            System.out.println("파일 이름 변경 성공: " + oldName + " → " + newName);
            return true;
        } else {
            System.out.println("이름 변경 실패: " + oldName + " → " + newName);
            return false;
        }
    }
}
