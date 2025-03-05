import java.io.File;

public class FileUtils {
    public static void listFiles(String directoryPath) {
        File folder = new File(directoryPath);
        File[] files = folder.listFiles(); // 폴더 내 파일 목록 가져오기

        // 파일 유무 검사
        if (files == null) {
            System.out.println("해당 디렉터리를 찾을 수 없습니다.");
            return;
        }

        // 파일 목록 불러오기
        System.out.println(directoryPath + "내 파일 목록 : ");
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(" - " + file.getName());
            }
        }
    }
}
