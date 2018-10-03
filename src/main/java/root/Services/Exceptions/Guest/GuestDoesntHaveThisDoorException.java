package root.Services.Exceptions.Guest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class GuestDoesntHaveThisDoorException extends RuntimeException
{
    public GuestDoesntHaveThisDoorException(String guestPhone, String doorPhone)
    {
        super("Guest '" + guestPhone + "'doesn't have door '" + doorPhone + "'");
    }
}
