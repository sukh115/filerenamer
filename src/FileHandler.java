import java.io.File;

public class FileHandler {

    // 디렉터리 유효성 검사
    protected boolean isDirectoryValid(String directoryPath) {
        File folder = new File(directoryPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("해당 디렉터리가 존재하지 않거나 유효하지 않습니다. : " + directoryPath);
            return false;
        }
        return true;
    }

    // 파일 유효성 검사
    protected boolean isFileValid(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.out.println(" 해당 파일이 존재하지 않습니다 : " + filePath);
            return false;
        }
        return true;
    }
}
