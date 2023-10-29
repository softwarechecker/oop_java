package throws_package;

public class DuplicateModelNameException extends Exception {
    public DuplicateModelNameException(String msg) {
        super(msg + " уже в массиве");
    }
}