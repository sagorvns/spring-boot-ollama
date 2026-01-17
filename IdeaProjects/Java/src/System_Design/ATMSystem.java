package System_Design;

class ATM {
    private int balance = 10000;

    public boolean withdraw(int amount) {
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

    public void deposit(int amount) { balance += amount; }

    public int getBalance() { return balance; }
}

public class ATMSystem {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.deposit(500);
        System.out.println(atm.withdraw(1000)); // true
        System.out.println(atm.getBalance()); // 9500
    }
}

