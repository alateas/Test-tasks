import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ServerConnection (String host) throws IOException{
        System.out.println("Connecting to... " + host);
        socket = new Socket(host, 4444);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }


}
