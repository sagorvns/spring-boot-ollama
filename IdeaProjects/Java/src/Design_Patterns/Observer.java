package Design_Patterns;
//The Observer Pattern is a behavioral design pattern where one object (Subject)
//maintains a list of observers and notifies them automatically of any state changes.
import java.util.*;

interface Observer { void update(String message); }

class User implements Observer {
    private String name;
    public User(String name) { this.name = name; }
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}

class NotificationService {
    private List<Observer> users = new ArrayList<>();

    public void subscribe(Observer user) { users.add(user); }
    public void unsubscribe(Observer user) { users.remove(user); }

    public void notifyUsers(String message) {
        for (Observer user : users) {
            user.update(message);
        }
    }
}

 class Main    {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        Observer user1 = new User("Alice");
        Observer user2 = new User("Bob");

        service.subscribe(user1);
        service.subscribe(user2);

        service.notifyUsers("New Offer Available!");
        // Output:
        // Alice received: New Offer Available!
        // Bob received: New Offer Available!
    }
}
