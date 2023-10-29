package throws_package;

public class NoSuchModelNameException extends Exception {
    public NoSuchModelNameException(String msg) {
        super(msg + " не найден в списке");
    }
}