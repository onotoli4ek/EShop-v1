package dao.exception;


public class NotUniqueUserLoginException extends DaoBusinessException {
    public NotUniqueUserLoginException(String message) {
        super(message);
    }

    public NotUniqueUserLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
