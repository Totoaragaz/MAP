package exceptions;

public class CreditLimit extends RuntimeException{
    public CreditLimit(String message) {
        super(message);
    }
}