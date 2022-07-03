package jpabook.jpashop.exception;

public class NotEnoughstockException extends RuntimeException{
    public NotEnoughstockException() {
        super();
    }

    public NotEnoughstockException(String message) {
        super(message);
    }

    public NotEnoughstockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughstockException(Throwable cause) {
        super(cause);
    }

    protected NotEnoughstockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
