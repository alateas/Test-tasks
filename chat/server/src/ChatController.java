import java.util.ArrayList;

public class ChatController {
    private ArrayList<User> users = new ArrayList<User>();

    public ChatController() {
        System.out.println("Welcome to Server side");
    }

    public void addUser(User user) {
        users.add(user);    
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void printUserMessage(String nick, String message) {
        String finalString = "<" + nick + "> : " + message;
        for (User user : users) {
            user.sendString(finalString);    
        }
        System.out.println(finalString);
        
    }

    public void printUserList() {
        System.out.print("User list: ");
        for(User user : users) {
            System.out.print(user + ", ");
        }
        System.out.println();
    }
}
