package Design_Patterns;

class Singleton {
    private static Singleton instance;
    private Singleton() { } // Private constructor to prevent instantiation
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    public static void main(String[] args) {
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();
        System.out.println(obj1 == obj2); // true (same instance)
    }
}

