public class Main {
    public static void main(String[] args){
        Console console = new Console();
        CommandAnalyzer commandAnalyzer = new CommandAnalyzer();

        commandAnalyzer.setConsole(console);
        console.setCommandAnalyzer(commandAnalyzer);
        console.startReadLoop();
    }
}
