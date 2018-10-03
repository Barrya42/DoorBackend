package root.Services.Exceptions.Guest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class GuestAlreadyHaveThisDoorException extends RuntimeException
{
    public GuestAlreadyHaveThisDoorException(String guestPhone, String doorPhone)
    {
        super("Guest '" + guestPhone + "' already have door '" + doorPhone + "'");
    }
}
