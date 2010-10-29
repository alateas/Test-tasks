import java.io.BufferedReader;
import java.io.IOException;

public class ServerListenerThread extends Thread {
    private ChatController chat;

    public ServerListenerThread(ChatController chat) throws IOException{
        this.chat = chat;
        start();
    }

    public void run() {
        String input;

        while ((input = chat.readLine()) != null) {
            System.out.println(input);
        }
    }
}
