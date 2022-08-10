import java.util.Objects;
import java.util.Scanner;
public class Main {
    static final int [] arabNums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    static final String [] romaNums = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    public static void main (String[] args){
        System.out.println("Enter an expression with rim or arab numbers");
        System.out.println("    Only natural numbers [1; 10] supported");
        System.out.println("    For arab numbers: First number most be greater than the second");
        System.out.println("        (Operations: +, -, *, /)");
        Scanner scan = new Scanner(System.in);
        String numExpression = scan.nextLine();
        String result = calc(numExpression);
        System.out.println(result);
    }
    public static String calc(String numExpression) {
        String result;
        String astr = "";
        String bstr = "";
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
            System.out.println("Enter an expression with +, -, * or /");
            System.exit(0);
        }
        astr = astr.trim();
        bstr = bstr.trim();
        for (int i = 0; i < 11; i++){
            if (Objects.equals(astr, romaNums[i])){
                a = arabNums[i];
                checkA = true;
            }
            if (Objects.equals(bstr, romaNums[i])){
                b = arabNums[i];
                checkB = true;
            }
        }
        if (checkA && checkB){
            result = Roman.getIntNumbers(astr, bstr, sign);
            return result;
        }
        else if(!checkA && !checkB){
            a = Integer.parseInt(astr);
            b = Integer.parseInt(bstr);
            result = expressionResult(a, b, sign);
            return result;
        }
        else{
            System.out.println("Both of numbers most be arab or roman");
            System.out.println("Both of numbers most be between 1 and 10 inclusively (If roman)");
            System. exit(0);
        }
        return "0";
    }
    public static String expressionResult (int a, int b, String sign){
        String resultstr;
        int result = 0;
        switch (sign){
            case ("+"):
                result = a + b;
                break;
            case ("-"):
                result = a - b;
                break;
            case ("*"):
                result = a * b;
                break;
            case ("/"):
                result = a / b;
                break;
        }
        resultstr = String.valueOf(result);
        return resultstr;
    }
}
class Roman extends Main{
    public static String getIntNumbers(String astr, String bstr, String sign){
        int a = 0;
        int b = 0;
        String result;
        String[] rimskie = new String[10];
        rimskie[0] = "I";
        rimskie[1] = "II";
        rimskie[2] = "III";
        rimskie[3] = "IV";
        rimskie[4] = "V";
        rimskie[5] = "VI";
        rimskie[6] = "VII";
        rimskie[7] = "VIII";
        rimskie[8] = "IX";
        rimskie[9] = "X";
        for(int i = 0; i < 10; i++){
            if(astr.equals(rimskie[i])){
                a = i + 1;
            }
        }
        for(int j = 0; j < 10; j++) {
            if (bstr.equals(rimskie[j])) {
                b = j + 1;
            }
        }
        if (a > 0 && b > 0){
            if (a <= b && sign.contains("-")){
                System.out.println("For arab numbers: Result of minus operation can't be 0 or less");
                System. exit(0);
            }else{
                result = countRim(a, b, sign);
                return result;
            }
        }else{
            System.out.println("Both of numbers most be between 1 and 10 inclusively");
            System. exit(0);
        }
        return "0";
    }
    public static String countRim(int a, int b, String sign){
        String result;
        int integerResult = 0;
        switch (sign){
            case ("+"):
                integerResult = a + b;
                break;
            case ("-"):
                integerResult = a - b;
                break;
            case ("*"):
                integerResult = a * b;
                break;
            case ("/"):
                integerResult = a / b;
                break;
        }
        result = answerInRoman(integerResult);
        return result;
    }
    public static String answerInRoman(int integerResult){
        String result = "Roman answer: ";
        int a;
        if (integerResult == 100){
            result = result.concat("C");
        }
        if (integerResult % 100 >= 90){
            a = integerResult % 100 - 90;
            result = result.concat("XC");
            if (a / 4 < 1){
                for (int i = 0; i < a; i++){
                    result = result.concat("I");
                }
            }else if(a / 4 == 1){
                result = result.concat("IV");
            }else if (a / 4 > 1 && a / 9 < 1){
                result = result.concat("V");
                for (int i = 0; i < a - 5; i++){
                    result = result.concat("I");
                }
            }else if (a / 9 == 1){
                result = result.concat("IX");
            }
        }
        if (integerResult % 100 >= 50 && integerResult % 100 < 90){
            a = integerResult % 100 - 50;
            result = result.concat("L");
            for (int i = 0; i < a % 10; i++){
                result = result.concat("X");
            }
            if (a / 4 < 1){
                for (int i = 0; i < a; i++){
                    result = result.concat("I");
                }
            }else if(a / 4 == 1){
                result = result.concat("IV");
            }else if (a / 4 > 1 && a / 9 < 1){
                result = result.concat("V");
                for (int i = 0; i < a - 5; i++){
                    result = result.concat("I");
                }
            }else if (a / 9 == 1){
                result = result.concat("IX");
            }
        }
        if (integerResult % 100 >= 40 && integerResult % 100 < 50){
            a = integerResult % 100 - 40;
            result = result.concat("XL");
            if (a / 4 < 1){
                for (int i = 0; i < a; i++){
                    result = result.concat("I");
                }
            }else if(a / 4 == 1){
                result = result.concat("IV");
            }else if (a / 4 > 1 && a / 9 < 1){
                result = result.concat("V");
                for (int i = 0; i < a - 5; i++){
                    result = result.concat("I");
                }
            }else if (a / 9 == 1){
                result = result.concat("IX");
            }
        }
        if (integerResult % 100 < 40){
            a = integerResult % 100;
            for (int i = 0; i < a / 10; i++){
                result = result.concat("X");
            }
            a = a % 10;
            if (a / 4 < 1){
                for (int i = 0; i < a; i++){
                    result = result.concat("I");
                }
            }else if(a / 4 == 1){
                result = result.concat("IV");
            }else if (a / 4 > 1 && a / 9 < 1){
                result = result.concat("V");
                for (int i = 0; i < a - 5; i++){
                    result = result.concat("I");
                }
            }else if (a / 9 == 1){
                result = result.concat("IX");
            }
        }
        return result;
    }
}
