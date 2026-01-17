package Design_Patterns;
//The Strategy Pattern is a behavioral design pattern that lets you define a 
//family of algorithms, put each into a separate class, and make them interchangeable at runtime.
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) { System.out.println("Paid $" + amount + " using Credit Card."); }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(int amount) { System.out.println("Paid $" + amount + " using PayPal."); }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    public ShoppingCart(PaymentStrategy paymentStrategy) { this.paymentStrategy = paymentStrategy; }
    public void checkout(int amount) { paymentStrategy.pay(amount); }
}

 class Main1 {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart(new PayPalPayment());
        cart.checkout(500); // Paid $500 using PayPal.
    }
}
