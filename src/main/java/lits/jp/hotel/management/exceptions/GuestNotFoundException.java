package lits.jp.hotel.management.exceptions;

public class GuestNotFoundException extends RuntimeException {

    public GuestNotFoundException(String message) {
        super(message);
    }

}
