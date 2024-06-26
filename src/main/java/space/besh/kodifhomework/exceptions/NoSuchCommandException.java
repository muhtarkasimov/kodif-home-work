package space.besh.kodifhomework.exceptions;

public class NoSuchCommandException extends InvalidInputException {

    public NoSuchCommandException(String message) {
        super("bash: " + message + ": command not found");
    }
}
