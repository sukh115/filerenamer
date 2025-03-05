import java.io.File;

public class FileRenamer {
    public static void renameFile(String directoryPath, String oldName, String newName){
        File  oldFile = new File(directoryPath, oldName); // 기존 파일
        File newFile = new File(directoryPath, newName); // 신규 파일

        // 기존 파일이 존재하는지 확인
        if (!oldFile.exists()) {
            System.out.println("해당 파일이 존재하지 않습니다." + oldName);
            return;
        }

        // 파일 이름 변경 시도
        if (oldFile.renameTo(newFile)) {
            System.out.println("파일 이름 변경 성공 : " + oldName +  " 에서 " + newName +" 으로 변경");
        }else {
            System.out.println("파일 이름 변경 실패");
        }


    }
}
