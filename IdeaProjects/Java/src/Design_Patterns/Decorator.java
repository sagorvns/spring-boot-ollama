package Design_Patterns;
//The Decorator Pattern is a structural design pattern that allows adding new 
//behavior to objects dynamically without changing their structure. It uses composition instead of inheritance.
interface Coffee {
    String getDescription();
    double cost();
}

class SimpleCoffee implements Coffee {
    public String getDescription() { return "Simple Coffee"; }
    public double cost() { return 10; }
}

class MilkDecorator implements Coffee {
    private Coffee coffee;
    public MilkDecorator(Coffee coffee) { this.coffee = coffee; }

    public String getDescription() { return coffee.getDescription() + ", Milk"; }
    public double cost() { return coffee.cost() + 2; }

    public static void main(String[] args) {
        Coffee coffee = new MilkDecorator(new SimpleCoffee());
        System.out.println(coffee.getDescription() + " costs " + coffee.cost());
        // Output: Simple Coffee, Milk costs 12.0
    }
}

