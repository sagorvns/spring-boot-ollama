package Solid_Principle;

public class  SingleResponsibility {

    //Bad Example (Violating SRP)
    class Employee {
        void calculateSalary() { /* Salary calculation logic */ }
        void printSalarySlip() { /* Printing logic */ }
    }

    //Good Example (Following SRP)
    class SalaryCalculator {
        void calculateSalary() { /* Salary calculation logic */ }
    }

    class SalaryPrinter {
        void printSalarySlip() { /* Printing logic */ }
    }



}
