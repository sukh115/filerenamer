import java.io.File;

public class FileUtils extends FileHandler {
    public void listFiles(String directoryPath) {

        if (!isDirectoryValid(directoryPath)){
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
