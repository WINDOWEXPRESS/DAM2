import java.io.IOException;
import java.util.Scanner;

public class U2A1_Shutdowner {
    private static final int MS = 1000;
    public static void main(String[] args) throws IOException {
        // Ask for the required information to prepare the command
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("Select your option (s-shutdown / r-reboot / h-hibernate): ");
        String shutdownOption = keyboard.nextLine();        
        
        System.out.print("How much seconds will the command wait to be run? (0 means immediately): ");
        String shutdownTime = keyboard.nextLine();        
        
        // Prepare the command
        String command;
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            command = "C:/Windows/System32/shutdown -" + shutdownOption + " -t " + shutdownTime;
        } else {
            command = "shutdown -" + shutdownOption + " -t " + shutdownTime;
        }
        
        // Prepare the process and launch it
        ProcessBuilder shutdowner = new ProcessBuilder(command.split("\\s"));
   
        
        // Show the command to be run
        System.out.print("El comando a ejecutar es:  ");
        for (String commandPart: shutdowner.command()) {
            System.out.print(commandPart + " ");
        }

        try {
            Thread.sleep(Integer.parseInt(shutdownTime)*MS);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        shutdowner.start();
        System.out.println("");
    }    
}
