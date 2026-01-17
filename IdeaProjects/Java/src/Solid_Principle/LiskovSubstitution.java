package Solid_Principle;

public class LiskovSubstitution {

    //🚫 Bad Example (Violating LSP)
    class Bird1 {
        void fly() { System.out.println("Flying"); }
    }

    class Penguin1 extends Bird1 { } // ❌ Penguins can't fly
    //➡️ If we call penguin.fly(), it breaks expectations.

    //✅ Good Example (Following LSP)
    abstract class Bird { }
    class FlyingBird extends Bird {
        void fly() { System.out.println("Flying"); }
    }
    class Penguin extends Bird { } // ✅ Now, Penguin is a Bird but doesn't break behavior.

}
