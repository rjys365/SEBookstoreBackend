package cn.rjys365.sebookstorebackend.exception;

public class OrderServiceException extends RuntimeException{
    public OrderServiceException(String message) {
        super(message);
    }
}
