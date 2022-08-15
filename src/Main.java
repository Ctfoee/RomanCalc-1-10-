import java.util.*;
public class Main {
    public static final List<String> ROMANS = List.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String numExpression = scan.nextLine();
        System.out.println(calc(numExpression));
    }
    public static String calc(String numExpression) {
        int result;
        int a = 0;
        int b = 0;
        String[] parts = numExpression.split("\\s");
        if (parts.length != 3) {
            throw new CalculatorException();
        }
        String astr = parts[0];
        String sign = parts[1];
        String bstr = parts[2];
        boolean canBeRoman = false;
        try {
            a = Integer.parseInt(astr);
            b = Integer.parseInt(bstr);
        } catch (NumberFormatException e) {
            canBeRoman = true;
            a = ROMANS.indexOf(astr) + 1;
            b = ROMANS.indexOf(bstr) + 1;
        }
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new CalculatorException();
        }
        result = expressionResult(a, b, sign);
        return canBeRoman
                ? Roman.convertArabToRoman(result)
                : String.valueOf(result);
    }
    public static int expressionResult(int a, int b, String sign) {
        return switch (sign) {
            case ("+") -> a + b;
            case ("-") -> a - b;
            case ("*") -> a * b;
            case ("/") -> a / b;
            default -> throw new CalculatorException();
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
