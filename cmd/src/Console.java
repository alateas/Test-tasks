import java.io.*;

public class Console {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private CommandAnalyzer commandAnalyzer;
    
//    public Console() {
//
//    }

    public void setCommandAnalyzer(CommandAnalyzer commandAnalyzer) {
        this.commandAnalyzer = commandAnalyzer;
    }

    public void startReadLoop() {
        String input;
        try {
            System.out.print(">>");
            while ((input = reader.readLine()) != null) {
                if (needExit(input)) {
                    break;
                }
                commandAnalyzer.tryCommand(input);
                System.out.print(">>");
            }
        } catch (IOException e) {}
    }

    private boolean needExit(String command) {
        if (command.equalsIgnoreCase("exit")) {
            return true;    
        }
        return false;
    }

    public void println(String string) {
        System.out.println(string);    
    }
}
