package Solid_Principle;

public class DependencyInversion {

    //🚫 Bad Example (Violating DIP)
    class MySQLDatabase1 {
        void connect() { System.out.println("Connected to MySQL"); }
    }

    class Application1 {
        MySQLDatabase1 db = new MySQLDatabase1(); // ❌ Tight coupling
    }
    //➡️ The Application class is tightly coupled with MySQLDatabase.

    //✅ Good Example (Following DIP)
    interface Database {
        void connect();
    }

    class MySQLDatabase implements Database {
        public void connect() { System.out.println("Connected to MySQL"); }
    }

    class Application {
        Database db;
        Application(Database db) { this.db = db; }
    }

}
