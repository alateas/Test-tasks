import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ChatController chat = new ChatController();
        ServerSocket servers;
        Socket clientSocket;

        try {
            servers = new ServerSocket(4444);
            try {
                System.out.println("Waiting for a clients...");
                while((clientSocket = servers.accept()) != null) {
                    chat.addUser(new User(clientSocket, chat));
                }
            } catch (IOException e) {
                System.out.println("Accept user connection error");
            }
            servers.close();
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 4444");
            System.exit(-1);
        }
    }
}