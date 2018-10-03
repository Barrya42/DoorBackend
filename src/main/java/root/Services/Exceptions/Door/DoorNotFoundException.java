package root.Services.Exceptions.Door;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DoorNotFoundException extends RuntimeException
{

    public DoorNotFoundException(String guestPhone)
    {
        super("could not find door '" + guestPhone + "'.");
    }
}
