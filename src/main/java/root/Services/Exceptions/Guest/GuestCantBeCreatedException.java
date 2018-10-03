package root.Services.Exceptions.Guest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GuestCantBeCreatedException extends RuntimeException
{
    public GuestCantBeCreatedException(String guestPhone)
    {
        super("Guest with '" + guestPhone + "' cant be created.");
    }
}
