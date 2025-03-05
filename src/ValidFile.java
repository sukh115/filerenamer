import java.io.File;

public class ValidFile {

    private static final String INVALID_CHARACTERS = "[<>:\"/\\\\|?*]";

    // 디렉터리 유효성 검사
    public boolean isDirectoryValid(String directoryPath) {
        File folder = new File(directoryPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("해당 디렉터리가 존재하지 않거나 유효하지 않습니다. : " + directoryPath);
            return false;
        }
        return true;
    }

    // 파일 유효성 검사
    public boolean isFileValid(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println(" 해당 파일이 존재하지 않습니다 : " + filePath);
            return false;
        }
        return true;
    }
    // 파일 이름 유효성 검사 (공백 + 금지된 문자 체크)
    public boolean isValidFileName(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            System.out.println("파일 이름이 비어 있습니다.");
            return false;
        }
        if (fileName.matches(".*" + INVALID_CHARACTERS + ".*")) {
            System.out.println("파일 이름에 사용할 수 없는 문자가 포함되어 있습니다: " + fileName);
            return false;
        }
        return true;
    }
}
