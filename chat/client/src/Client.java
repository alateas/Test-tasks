import java.io.*;

public class Client {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcome to chat");
        if (args.length < 1) {
            System.out.println("use: client hostname");
            System.exit(-1);
        }

        System.out.print("Enter your nickname: ");
        String nick = consoleReader.readLine();

        ServerConnection serverConnection = new ServerConnection(args[0]);
        ChatController chat = new ChatController(serverConnection, nick);
        new ServerListenerThread(chat);
        new ConsoleListenerThread(chat, consoleReader);
    }
}