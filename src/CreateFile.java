
// NOT A PART OF PROJECT
import java.io.File;
import java.io.IOException;
public class CreateFile {
    public static void main(String[] args) {
        String path = "/Users/jatinsingh/IdeaProjects/thrillio/pages/fuckyou.html";
        File file = new File(path);
            try {
                boolean flag = file.createNewFile();
                if(flag)
                    System.out.println("file created");
                else System.out.println("file not created");
            } catch (IOException e) {
                e.printStackTrace();
            }


    }

}
