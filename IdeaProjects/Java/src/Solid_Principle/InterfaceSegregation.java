package Solid_Principle;

public class InterfaceSegregation {

    //🚫 Bad Example (Violating ISP)
    interface Worker {
        void work();
        void eat();
    }

    class Robot1 implements Worker {
        public void work() { System.out.println("Working"); }
        public void eat() { /* ❌ Robots don't eat */ }
    }
    //➡️ Robot is forced to implement eat(), which it doesn’t need.

    //✅ Good Example (Following ISP)
    interface Workable { void work(); }
    interface Eatable { void eat(); }

    class Robot implements Workable {
        public void work() { System.out.println("Working"); }
    }

}
