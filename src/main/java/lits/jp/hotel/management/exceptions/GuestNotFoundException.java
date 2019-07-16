package lits.jp.hotel.management.exceptions;


// add response status
public class GuestNotFoundException extends RuntimeException {

    public GuestNotFoundException(String message) {
        super(message);
    }

}
