import java.util.*;

public class Main {
    public static final List<String> ROMANS = List.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");
    public static final int[] ARAB = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static void main(String[] args) {
        System.out.println("Enter an expression with roman or arab numbers; (Operations: +, -, *, /) ");
        System.out.println("Only natural numbers [1; 10] supported");
        System.out.println("    For arab numbers: First number must be greater than the second");
        Scanner scan = new Scanner(System.in);
        String numExpression = scan.nextLine();
        String result = calc(numExpression);
        System.out.println(result);
    }
    public static String calc(String numExpression) {
        int result;
        String astr = "0";
        String bstr = "0";
        String sign = "";
        int a = 0;
        int b = 0;
        boolean checkA = false; // Для проверки, одного ли типа a и b
        boolean checkB = false;
        String[] expression;
        String[] signs = {"\\+", "\\-", "\\*", "\\/"};
        for (var forOperation : signs) {
            if (numExpression.contains(forOperation.substring(1))) {
                sign = forOperation.substring(1);
                expression = numExpression.split(forOperation);
                astr = expression[0];
                bstr = expression[1];
            }
        }
        if (sign.isEmpty()) {
            throw new RuntimeException("Enter an expression with +, -, * or /");
        }
        astr = astr.trim();
        bstr = bstr.trim();
        try {
            a = Integer.parseInt(astr);
            b = Integer.parseInt(bstr);
        } catch (NumberFormatException e) {
            for (int i = 0; i < ROMANS.size(); i++) {
                if (astr.equals(ROMANS.get(i))) {
                    checkA = true;
                    a = i + 1;
                }
                if (bstr.equals(ROMANS.get(i))) {
                    checkB = true;
                    b = i + 1;
                }
            }
        }
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new RuntimeException("Both of numbers must be between 1 and 10 inclusively");
        }
        if (checkA && checkB) {
            result = expressionResult(a, b, sign);
            return Roman.convertArabToRoman(result);
        } else if (!checkA && !checkB) {
            a = Integer.parseInt(astr);
            b = Integer.parseInt(bstr);
            result = expressionResult(a, b, sign);
            return String.valueOf(result);
        } else {
            throw new RuntimeException("Both of numbers must be between 1 and 10 inclusively and both must be written in same type");
        }
    }
    public static int expressionResult (int a, int b, String sign){
        return switch (sign) {
            case ("+") -> a + b;
            case ("-") -> a - b;
            case ("*") -> a * b;
            case ("/") -> a / b;
            default -> throw new RuntimeException();
        };
    }
}
class Roman{
    public static Map<String, Integer> romanMap = new LinkedHashMap<>() {{
        put("C", 100);
        put("XC", 90);
        put("L", 50);
        put("XL", 40);
        put("X", 10);
        put("IX", 9);
        put("V", 5);
        put("IV", 4);
        put("I", 1);
    }};
    public static String convertArabToRoman(int integerResult) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : romanMap.entrySet()) {
            if (integerResult >= entry.getValue()) {
                integerResult -= entry.getValue();
                result.append(entry.getKey()).append(convertArabToRoman(integerResult));
                return result.toString();
            }
        }
        return "";
    }
}
