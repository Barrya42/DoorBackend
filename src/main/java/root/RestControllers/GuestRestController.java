package root.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import root.Entitys.GuestEntity;
import root.Services.DoorService;
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

    @RequestMapping(path = "/checkDoor", method = RequestMethod.GET)
    private ResponseEntity checkDoor(@RequestParam Map<String, String> params)
    {
        ResponseEntity responseEntity;
        String guestPhone = PhoneTools.preparePhone(params.get("guestPhone"));
        String doorPhone = PhoneTools.preparePhone(params.get("doorPhone"));

        if (guestService.checkDoorForGuest(guestPhone, doorPhone))
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
    private GuestEntity addGuest(@RequestBody GuestEntity newGuestEntity)
    {
        return guestService.addGuest(newGuestEntity);
    }

    @RequestMapping(path = "/updateGuest", method = RequestMethod.POST)
    private GuestEntity updateGuest(@RequestBody GuestEntity guestEntity)
    {
        return guestService.updateGuest(guestEntity);
    }
}


