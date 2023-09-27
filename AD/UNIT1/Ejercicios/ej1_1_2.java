import java.io.File;
import java.util.Arrays;

public class ej1_1_2 {
    public static void main(String[] args) {

        if (args.length==1){
            File f = new File(args[0]);
            System.out.println(f);
        }
        File f = new File("ewq");
        System.out.println(Arrays.toString(f.list()));
        if(f.exists()){
            System.out.println("si exist.");
        }else{
            System.out.println("No exist.");
        }
    }
}
