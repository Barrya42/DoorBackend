package root.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import root.Entitys.DoorEntity;
import root.Entitys.GuestEntity;
import root.Services.DoorService;
import root.Services.GuestService;
import root.Tools.PhoneTools;

@RestController
//@RolesAllowed({"user", "admin"})
@RequestMapping("/guests")
public class GuestRestController
{
    @Autowired
    private GuestService guestService;
    @Autowired
    private DoorService doorService;

    @RequestMapping(path = "/getInfo", method = RequestMethod.GET)

    private GuestEntity readGuest(@RequestParam Map<String, String> params)
    {
        String phone = PhoneTools.preparePhone(params.get("guestPhone"));

        return guestService.findOneByPhone(phone)
                .orElseThrow(() -> new GuestNotFoundException(phone));
    }

    @RequestMapping(path = "/allowDoor", method = RequestMethod.POST)
    private ResponseEntity<GuestEntity> allowDoor(@RequestParam Map<String, String> params)
    {
        ResponseEntity<GuestEntity> responseEntity;
        String guestPhone = PhoneTools.preparePhone(params.get("guestPhone"));
        String doorPhone = PhoneTools.preparePhone(params.get("doorPhone"));
        DoorEntity checkingDoor = doorService.findOneByPhone(doorPhone)
                .orElseThrow(() -> new DoorNotFoundException(doorPhone));
        GuestEntity guestEntity = guestService.findOneByPhone(guestPhone)
                .orElseThrow(() -> new GuestNotFoundException(guestPhone));
        Set<DoorEntity> allowedDoors = guestEntity.getAccessedDoors();
        if (allowedDoors.contains(checkingDoor))
        {
            throw new GuestAlreadyHaveThisDoorException(guestPhone, doorPhone);
        }
        else
        {
            guestEntity = guestService.allowDoor(guestEntity, checkingDoor);
            // TODO: 21.09.2018 Доделать!!!
            responseEntity = new ResponseEntity<>(guestEntity, HttpStatus.ACCEPTED);
        }
        return responseEntity;
    }

    @RequestMapping(path = "/dennyDoor", method = RequestMethod.POST)
    private ResponseEntity<GuestEntity> dennyDoor(@RequestParam Map<String, String> params)
    {
        ResponseEntity<GuestEntity> responseEntity;
        String guestPhone = PhoneTools.preparePhone(params.get("guestPhone"));
        String doorPhone = PhoneTools.preparePhone(params.get("doorPhone"));
        DoorEntity checkingDoor = doorService.findOneByPhone(doorPhone)
                .orElseThrow(() -> new DoorNotFoundException(doorPhone));
        GuestEntity guestEntity = guestService.findOneByPhone(guestPhone)
                .orElseThrow(() -> new GuestNotFoundException(guestPhone));
        Set<DoorEntity> allowedDoors = guestEntity.getAccessedDoors();
        if (!allowedDoors.contains(checkingDoor))
        {
            throw new GuestDoesntHaveThisDoorException(guestPhone, doorPhone);
        }
        else
        {
            guestEntity = guestService.dennyDoor(guestEntity, checkingDoor);
            // TODO: 21.09.2018 Доделать!!!
            responseEntity = new ResponseEntity<>(guestEntity, HttpStatus.ACCEPTED);
        }
        return responseEntity;
    }

    @RequestMapping(path = "/checkDoor", method = RequestMethod.GET)

    private ResponseEntity checkDoor(@RequestParam Map<String, String> params)
    {
        ResponseEntity responseEntity;
        String guestPhone = PhoneTools.preparePhone(params.get("guestPhone"));
        String doorPhone = PhoneTools.preparePhone(params.get("doorPhone"));
        DoorEntity checkingDoor = doorService.findOneByPhone(doorPhone)
                .orElseThrow(() -> new DoorNotFoundException(doorPhone));
        GuestEntity guestEntity = guestService.findOneByPhone(guestPhone)
                .orElseThrow(() -> new GuestNotFoundException(guestPhone));
        Set<DoorEntity> allowedDoors = guestEntity.getAccessedDoors();
        if (allowedDoors.contains(checkingDoor))
        {
            responseEntity = new ResponseEntity(HttpStatus.ACCEPTED);
        }
        else
        {
            responseEntity = new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return responseEntity;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)

    private GuestEntity addGuest(@RequestParam Map<String, String> param)
    {
        String phone = PhoneTools.preparePhone(param.get("guestPhone"));
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

@ResponseStatus(HttpStatus.CONFLICT)
class GuestAlreadyHaveThisDoorException extends RuntimeException
{
    public GuestAlreadyHaveThisDoorException(String guestPhone, String doorPhone)
    {
        super("Guest '" + guestPhone + "' already have door '" + doorPhone + "'");
    }
}

@ResponseStatus(HttpStatus.CONFLICT)
class GuestDoesntHaveThisDoorException extends RuntimeException
{
    public GuestDoesntHaveThisDoorException(String guestPhone, String doorPhone)
    {
        super("Guest '" + guestPhone + "'doesn't have door '" + doorPhone + "'");
    }
}
