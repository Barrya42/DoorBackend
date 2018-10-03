package root.Services.Exceptions.Guest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class GuestAlreadyExistException extends RuntimeException
{

    public GuestAlreadyExistException(String guestPhone)
    {
        super("Guest with '" + guestPhone + "' already exist.");
    }
}
