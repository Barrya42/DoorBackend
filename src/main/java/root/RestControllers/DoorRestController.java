package root.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import root.Entitys.DoorEntity;
import root.Entitys.GuestEntity;
import root.Services.DoorService;
import root.Services.Exceptions.Door.DoorNotFoundException;
import root.Services.Exceptions.Guest.GuestNotFoundException;
import root.Tools.PhoneTools;

@RestController
@ComponentScan("root")
@RequestMapping(path = "/doors")
public class DoorRestController
{
    @Autowired
    DoorService doorService;

    @RequestMapping(path = "/getInfo", method = RequestMethod.GET)
    private DoorEntity readDoor(@RequestParam Map<String, String> params)
    {
        String phone = PhoneTools.preparePhone(params.get("guestPhone"));

        return doorService.findOneByPhone(phone)
                .orElseThrow(() -> new DoorNotFoundException(phone));
    }

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    private DoorEntity addDoor(@RequestBody DoorEntity newDoorEntity)
    {
        return doorService.addDoor(newDoorEntity);
    }

    @RequestMapping(path = "/updateDoor", method = RequestMethod.POST)
    private DoorEntity updateDoor(@RequestBody DoorEntity doorEntity)
    {
        return doorService.updateDoor(doorEntity);
    }
}

