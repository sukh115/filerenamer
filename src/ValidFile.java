import java.io.File;

public class ValidFile {

    private static final String INVALID_CHARACTERS = "[<>:\"/\\\\|?*]"; // 금지된 문자 목록(윈도우 기준)

    // 디렉터리 유효성 검사
    public boolean isValidDirectory(String directoryPath) {
        File folder = new File(directoryPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("해당 디렉터리가 존재하지 않거나 유효하지 않습니다. : " + directoryPath);
            return false;
        }
        return true;
    }

    // 파일 유효성 검사
    public boolean isValidFile(String filePath) {
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
        if (fileName.matches(".*" + INVALID_CHARACTERS + ".*")) { // 금지된 문자 포함 여부 확인
            System.out.println("파일 이름에 사용할 수 없는 문자가 포함되어 있습니다: " + fileName);
            return false;
        }
        return true;
    }
    // 숫자 유효성 검사
    public boolean isValidNumber(Integer number) {
        if (number == null) {
            System.out.println("숫자가 입력되지 않았습니다.");
            return false;
        }
        if (number < 0) {
            System.out.println("유효하지 않은 숫자 입력 : " + number);
            return false;
        }
        return true;
    }

}
