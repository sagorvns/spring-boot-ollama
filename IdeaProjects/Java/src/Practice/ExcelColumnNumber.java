package Practice;

public class ExcelColumnNumber {

    public int titleToNumber(String columnTitle) {
        int result = 0;

        for (int i = 0; i < columnTitle.length(); i++) {
            char c = columnTitle.charAt(i);
            int value = c - 'A' + 1; // 'A' = 65 in ASCII → 'A' maps to 1

            result = result * 26 + value;
        }

        return result;
    }

    public static void main(String[] args) {
        ExcelColumnNumber solution = new ExcelColumnNumber();

        System.out.println("A → " + solution.titleToNumber("A"));    // 1
        System.out.println("Z → " + solution.titleToNumber("Z"));    // 26
        System.out.println("AA → " + solution.titleToNumber("AA"));  // 27
        System.out.println("AB → " + solution.titleToNumber("AB"));  // 28
        System.out.println("ZY → " + solution.titleToNumber("ZY"));  // 701
    }
}
