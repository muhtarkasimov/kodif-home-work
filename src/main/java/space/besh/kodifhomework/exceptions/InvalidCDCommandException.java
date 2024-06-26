package space.besh.kodifhomework.exceptions;

public class InvalidCDCommandException extends InvalidInputException {

    public InvalidCDCommandException(String message) {
        super("cd: no such file or directory: " + message);
    }
}
