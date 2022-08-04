import java.util.Scanner;
public class Calculator {
    public static void main(String[] args) {
        System.out.println("Enter an expression with rim or arab numbers");
        System.out.println("    Only natural numbers [1; 10] supported");
        System.out.println("    For arab numbers: First number most be greater than the second");
        System.out.println("        (Operations: +, -, *, /)");
        Scanner scan = new Scanner(System.in);
        String numExpression = scan.nextLine();
        definitionSign(numExpression);
    }
    public static void definitionSign(String numExpression) {
        String astr = "";
        String bstr = "";
        String sign = "";
        String[] expression;
        String [] signs = "\\+, \\-, \\*, \\/".split("");
        for (var forOperation: signs){
            if (numExpression.contains(forOperation.substring(1))){
                sign = forOperation.substring(1);
                expression = numExpression.split(sign);
                astr = expression[0];
                bstr = expression[1];
                definitionRimOrNot(astr, bstr, sign);
            }
        }
        if (sign.isEmpty()){
            System.out.println("Enter an expression with +, -, * or /");
            System. exit(0);
        }
    }
    public static void definitionRimOrNot(String astr, String bstr, String sign){
        boolean aRim = (astr.contains("I") || astr.contains("X") || astr.contains("V"));
        boolean bRim = (bstr.contains("I") || bstr.contains("X") || bstr.contains("V"));
        int a = 0;
        int b = 0;
        if (aRim && bRim){
            astr = astr.trim();
            bstr = bstr.trim();
            Rimskie chisla = new Rimskie(astr, bstr, sign);
            chisla.getIntNumbers(astr, bstr, sign);
        }else if (!aRim && !bRim){
            astr = astr.trim();
            bstr = bstr.trim();
            a = Integer.parseInt(astr);
            b = Integer.parseInt(bstr);
            if (a > 0 && a < 11 && b > 0 && b < 11){
                solutionForArab(a, b, sign);
            }else {
                System.out.println("Both of numbers most be between 1 and 10 inclusively");
                System. exit(0);
            }
        }else{
            System.out.println("Both of numbers most be arab or rim");
            System. exit(0);
        }
    }
    public static void solutionForArab(int a, int b, String sign){
        switch (sign){
            case ("+"):
                System.out.println(a + b);
                break;
            case ("-"):
                System.out.println(a - b);
                break;
            case ("*"):
                System.out.println(a * b);
                break;
            case ("/"):
                System.out.println(a / b);
                break;
        }
    }
}
class Rimskie{
    String astr;
    String bstr;
    String sign;
    Rimskie(String astr, String bstr, String sign){
        this.astr = astr;
        this.bstr = bstr;
        this.sign = sign;
    }
    public void getIntNumbers(String astr, String bstr, String sign){
        int a = 0;
        int b = 0;
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
            if (a > b){
                countRim(a, b, sign);
            }else{
                System.out.println("For arab numbers: First number most be greater than the second");
                System. exit(0);
            }
        }else{
            System.out.println("Both of numbers most be between 1 and 10 inclusively");
            System. exit(0);
        }
    }
    public void countRim(int a, int b, String sign){
        int solutionArab = 0;
        switch (sign){
            case ("+"):
                solutionArab = a + b;
                break;
            case ("-"):
                solutionArab = a - b;
                break;
            case ("*"):
                solutionArab = a * b;
                break;
            case ("/"):
                solutionArab = a / b;
                break;
        }
        solutionRim(solutionArab);
    }
    public void solutionRim(int solutionArab){
        int amountOfX;
        int amountOfI;
        String solution = "Rim answer: ";
        if (solutionArab / 100 == 1){ solution = solution.concat("C");}
        else if(solutionArab % 10 == 0){
            amountOfX = solutionArab / 10;
            for (int i = 0; i < amountOfX; i++){
                solution = solution.concat("X");
            }
        }else if(solutionArab % 10 == 9){
            solution = solution.concat("I");
            amountOfX = solutionArab / 10;
            for (int i = 0; i < amountOfX; i++){
                solution = solution.concat("X");
            }
        }else if((solutionArab % 10 > 0 && solutionArab % 10 < 4)){
            amountOfI = solutionArab % 10;
            amountOfX = solutionArab / 10;
            for (int i = 0; i < amountOfX; i++){
                solution = solution.concat("X");
            }
            for (int j = 0; j < amountOfI; j++){
                solution = solution.concat("I");
            }
        }else if (solutionArab % 10 == 4){
            amountOfX = solutionArab / 10;
            for (int i = 0; i < amountOfX; i++){
                solution = solution.concat("X");
            }
            solution = solution.concat("I");
            solution = solution.concat("V");
        }else if (solutionArab % 10 > 5 && solutionArab % 10 < 9){
            amountOfI = (solutionArab % 10) - 5;
            amountOfX = solutionArab / 10;
            for (int i = 0; i < amountOfX; i++){
                solution = solution.concat("X");
            }
            solution = solution.concat("V");
            for (int j = 0; j < amountOfI; j++){
                solution = solution.concat("I");
            }
        }else {
            amountOfX = solutionArab / 10;
            for (int i = 0; i < amountOfX; i++){
                solution = solution.concat("X");
            }
            solution = solution.concat("V");
        }
        System.out.println(solution);
        System. exit(0);
    }
}

