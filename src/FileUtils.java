import java.io.File;

public class FileUtils {
    // 1. 속성
    private String directoryPath;
    private ValidFile validFile;

    // 2. 생성자
    public FileUtils(String directoryPath, ValidFile validFile){
        this.directoryPath = directoryPath;
        this.validFile = validFile;
    };

    // 3. 기능
    // 파일 리스트
    public void listFiles() {
        // 디렉터리 유효성 검사
        if (!validFile.isDirectoryValid(directoryPath)){
            return;
        }

        File folder = new File(directoryPath);
        File[] files = folder.listFiles(); // 폴더 내 파일 목록 가져오기


        // 파일 목록 불러오기
        System.out.println(directoryPath + "내 파일 목록 : ");
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(" - " + file.getName());
            }
        }
    }
}
