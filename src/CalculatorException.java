public class CalculatorException extends RuntimeException {
    public CalculatorException() {
        super("Enter an expression with +, -, * or /; both of numbers must be the same type and in range [1, 10]");
    }
}
