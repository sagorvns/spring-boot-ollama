package Basic;

final class Employee
{
    private final String pancardNumber;
    private Employee(String pancardNumber)
    {
        this.pancardNumber=pancardNumber;
    }
    public String getPancardNumber(){
        return pancardNumber;
    }
}
public class Immutable {

    public static void main(String[] args) {

       System.out.println("Hello Java!");
    }
}