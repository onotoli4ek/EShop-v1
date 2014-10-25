package dao.exception;


public class NotUniqueUserEmailException extends DaoBusinessException {
    public NotUniqueUserEmailException(String message) {
        super(message);
    }

    public NotUniqueUserEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
