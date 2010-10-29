import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleListenerThread extends Thread {
    private BufferedReader reader;
    private ChatController chat;

    public ConsoleListenerThread(ChatController chat, BufferedReader reader) {
        this.chat = chat;
        this.reader = reader;
        start();
    }
    
    public void run() {
        String input;
        try {
            while ((input = reader.readLine()) != null) {
                chat.sendMessage(input);
                if (input.equalsIgnoreCase("exit")) break;
            }
        } catch (IOException e) {}
    }
}
