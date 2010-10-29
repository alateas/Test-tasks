import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class User extends Thread {
    private String nick;
    private Socket socket;
    private BufferedReader reader = null;
    private PrintWriter writer = null;
    private ChatController chat;

    public User(Socket socket, ChatController chat)  throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        nick = reader.readLine();
        this.chat = chat;
        System.out.println("Client " + nick + " connected");
        this.start();
    }

    public void run() {
        String input;

        try {
            while ((input = reader.readLine()) != null) {
                if (input.equalsIgnoreCase("exit")){
                    break;
                }
                if(input.equalsIgnoreCase("userlist")) {
                    chat.printUserList();
                }
                chat.printUserMessage(nick, input);
            }
        } catch (IOException e) {}
        finally {
            closeResources();
        }

    }

    private void closeResources() {
        chat.removeUser(this);
        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {}
    }

    public void sendString(String message) {
        writer.println(message);
    }

    @Override public String toString() {
        return nick;
    }
}
