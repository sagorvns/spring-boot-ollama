package Solid_Principle;

public class OpenClosed {

    //Bad Example (Violating OCP)
    class DiscountCalculatorOld {
        double getDiscount(String type, double amount) {
            if (type.equals("Silver")) return amount * 0.1;
            else if (type.equals("Gold")) return amount * 0.2;
            return 0;
        }
    }
    //➡️ Adding a new discount type requires modifying the existing class, breaking OCP.

    //✅ Good Example (Following OCP)
    interface Discount {
        double applyDiscount(double amount);
    }

    class SilverDiscount implements Discount {
        public double applyDiscount(double amount) { return amount * 0.1; }
    }

    class GoldDiscount implements Discount {
        public double applyDiscount(double amount) { return amount * 0.2; }
    }

    class DiscountCalculator {
        double getDiscount(Discount discount, double amount) {
            return discount.applyDiscount(amount);
        }
    }
}
