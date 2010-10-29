import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandAnalyzer {
    private Console console;

//    public CommandAnalyzer() {
//    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public void tryCommand(String command) {
        ArrayList<String> separated = new ArrayList<String>(Arrays.asList(command.split(" ")));
        String commandName = separated.get(0);
        separated.remove(0);
        System.out.println(commandName);
    }
}
