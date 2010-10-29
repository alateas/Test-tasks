public class ChatController {

    private ServerConnection server;

    public ChatController(ServerConnection server, String nick) {
        this.server = server;
        server.sendMessage(nick);
    }

    public void sendMessage(String message) {
        server.sendMessage(message);
    }

    public String readLine() {
        return server.readLine();
    }
}
