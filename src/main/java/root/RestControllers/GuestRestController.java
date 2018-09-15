package root.RestControllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import root.Entitys.GuestEntity;
import root.Services.GuestService;

@RestController
@RequestMapping("/guests/{phone}")
public class GuestRestController
{
    private GuestService guestService;

    @RequestMapping(method = RequestMethod.GET)
    private GuestEntity readGuest(@PathVariable String phone)
    {
        validateGuest(phone);
        return guestService.findOneByPhone(phone)
                .get();
    }


    private void validateGuest(String phone)
    {
        this.guestService.findOneByPhone(phone)
                .orElseThrow(
                        () -> new GuestNotFoundException(phone));
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class GuestNotFoundException extends RuntimeException
{

    public GuestNotFoundException(String guestPhone)
    {
        super("could not find user '" + guestPhone + "'.");
    }
}
