package System_Design;

import java.util.*;

public class SplitwiseApp {

    private static class User {
        String userId;
        String name;
        public User(String userId, String name) {
            this.userId = userId;
            this.name = name;
        }
    }

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Map<String, Double>> balanceSheet = new HashMap<>();

    // Add a new user
    public void addUser(String userId, String name) {
        users.put(userId, new User(userId, name));
        balanceSheet.put(userId, new HashMap<>());
    }

    // Add expense with equal split
    public void addExpense(String paidBy, double amount, List<String> participants) {
        int numParticipants = participants.size();
        double splitAmount = amount / numParticipants;

        for (String user : participants) {
            if (user.equals(paidBy)) continue;

            // user owes 'splitAmount' to paidBy
            balanceSheet.get(user).put(paidBy,
                    balanceSheet.get(user).getOrDefault(paidBy, 0.0) + splitAmount);

            // paidBy is owed by 'user'
            balanceSheet.get(paidBy).put(user,
                    balanceSheet.get(paidBy).getOrDefault(user, 0.0) - splitAmount);
        }
    }

    // Print all balances
    public void showBalances() {
        boolean isEmpty = true;
        for (String user1 : balanceSheet.keySet()) {
            for (Map.Entry<String, Double> entry : balanceSheet.get(user1).entrySet()) {
                String user2 = entry.getKey();
                double amount = entry.getValue();
                if (amount > 0) {
                    System.out.printf("%s owes %s: ₹%.2f\n",
                            users.get(user1).name, users.get(user2).name, amount);
                    isEmpty = false;
                }
            }
        }
        if (isEmpty) System.out.println("No balances");
    }

    // Sample run
    public static void main(String[] args) {
        SplitwiseApp app = new SplitwiseApp();
        app.addUser("u1", "Alice");
        app.addUser("u2", "Bob");
        app.addUser("u3", "Charlie");

        app.addExpense("u1", 1200, Arrays.asList("u1", "u2", "u3"));
        app.addExpense("u2", 600, Arrays.asList("u2", "u3"));

        app.showBalances();
    }
}

