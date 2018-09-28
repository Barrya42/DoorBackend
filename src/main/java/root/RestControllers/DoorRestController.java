package root.RestControllers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@ComponentScan("root")
public class DoorRestController
{
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class DoorNotFoundException extends RuntimeException
{

    public DoorNotFoundException(String guestPhone)
    {
        super("could not find door '" + guestPhone + "'.");
    }
}