import java.io.IOException;

public class getRunTime {
    public static void main(String[] args) throws IOException {
        // Launch notepad app
        Runtime.getRuntime().exec("notepad.exe");

        // This way always works
        // String separator = System.getProperty("file.separator");
        // Runtime.getRuntime()
        // .exec("c:"+separator+"windows"+separator+"notepad.exe");

        // This way used to work (UNIX style paths)
        //Runtime.getRuntime().exec("c:/windows/notepad.exe");
    }

}