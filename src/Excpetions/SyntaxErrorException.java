package Excpetions;

public class SyntaxErrorException extends RuntimeException {

    public SyntaxErrorException(String message) {
        super(message);
    }
}
