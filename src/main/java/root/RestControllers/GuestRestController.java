package root.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

import root.Entitys.DoorEntity;
import root.Entitys.GuestEntity;
import root.Services.DoorService;
import root.Services.Exceptions.Door.DoorNotFoundException;
import root.Services.Exceptions.Guest.GuestNotFoundException;
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
    private GuestEntity allowDoor(@RequestParam Map<String, String> params)
    {
        //ResponseEntity<GuestEntity> responseEntity;
        String guestPhone = PhoneTools.preparePhone(params.get("guestPhone"));
        String doorPhone = PhoneTools.preparePhone(params.get("doorPhone"));
        GuestEntity guestEntity = guestService.allowDoor(guestPhone, doorPhone);

        //responseEntity = new ResponseEntity<>(guestEntity, HttpStatus.ACCEPTED);

        return guestEntity;
    }

    @RequestMapping(path = "/dennyDoor", method = RequestMethod.POST)
    private GuestEntity dennyDoor(@RequestParam Map<String, String> params)
    {
        //ResponseEntity<GuestEntity> responseEntity;
        String guestPhone = PhoneTools.preparePhone(params.get("guestPhone"));
        String doorPhone = PhoneTools.preparePhone(params.get("doorPhone"));
        GuestEntity guestEntity = guestService.dennyDoor(guestPhone, doorPhone);

        //responseEntity = new ResponseEntity<>(guestEntity, HttpStatus.ACCEPTED);

        return guestEntity;
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

        if (allowedDoors.contains(checkingDoor) && guestEntity.getEnabled())
        {
            responseEntity = new ResponseEntity(HttpStatus.ACCEPTED);
        }
        else
        {
            responseEntity = new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return responseEntity;
    }

    @RequestMapping(path = "/setEnableGuest", method = RequestMethod.POST)
    private GuestEntity setEnableGuest(@RequestParam Map<String, String> params)
    {
        String guestPhone = PhoneTools.preparePhone(params.get("guestPhone"));
        Boolean enabled = Boolean.valueOf(params.get(("enabled")));

        return guestService.setEnableGuest(guestPhone,enabled);

    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)

    private GuestEntity addGuest(@RequestParam Map<String, String> params)
    {
        String guestPhone = PhoneTools.preparePhone(params.get("guestPhone"));
        String name = params.get("name");
        Boolean enabled = Boolean.valueOf(params.get("enabled"));

        return guestService.addGuest(name, guestPhone, enabled);
    }


}


