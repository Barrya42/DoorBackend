package root.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

import root.Entitys.GuestEntity;
import root.Services.GuestService;
import root.Tools.PhoneTools;

@RestController
@RequestMapping("/guests")
public class GuestRestController
{
    @Autowired
    private GuestService guestService;

    @RequestMapping(path = "/{phone}", method = RequestMethod.GET)
    private GuestEntity readGuest(@PathVariable String phone)
    {
        System.out.println("readGuest");
        phone = PhoneTools.preparePhone(phone);
        validateGuest(phone);
        return guestService.findOneByPhone(phone)
                .get();
    }

    @RequestMapping(path = "/add", method = RequestMethod.PUT)

    private GuestEntity addGuest(@RequestParam Map<String, String> param)
    {
        String phone = PhoneTools.preparePhone(param.get("phone"));
        String name = param.get("name");
        Boolean enabled = Boolean.valueOf(param.get("enabled"));
        Optional<GuestEntity> guestEntity = guestService.findOneByPhone(phone);
        if (guestEntity.isPresent())
        {
            throw new GuestAlreadyExistException(phone);
        }
        else
        {
            guestEntity = guestService.addGuest(name, phone, enabled);
        }
        return guestEntity.orElseThrow(() -> new CantBeCreatedException(phone));
    }


    private void validateGuest(String phone)
    {
        String _phone = PhoneTools.preparePhone(phone);
        this.guestService.findOneByPhone(phone)
                .orElseThrow(
                        () -> new GuestNotFoundException(_phone));
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class GuestNotFoundException extends RuntimeException
{

    public GuestNotFoundException(String guestPhone)
    {
        super("could not find guest '" + guestPhone + "'.");
    }
}

@ResponseStatus(HttpStatus.CONFLICT)
class GuestAlreadyExistException extends RuntimeException
{

    public GuestAlreadyExistException(String guestPhone)
    {
        super("Guest with '" + guestPhone + "' already exist.");
    }
}

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class CantBeCreatedException extends RuntimeException
{
    public CantBeCreatedException(String guestPhone)
    {
        super("Guest with '" + guestPhone + "' cant be created.");
    }
}
