package root.Services.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import root.Entitys.DoorEntity;
import root.Entitys.GuestEntity;
import root.Repositories.DoorEntityRepository;
import root.Repositories.GuestEntityRepository;
import root.Services.Exceptions.Door.DoorNotFoundException;
import root.Services.Exceptions.Guest.GuestAlreadyExistException;
import root.Services.Exceptions.Guest.GuestAlreadyHaveThisDoorException;
import root.Services.Exceptions.Guest.GuestNotFoundException;
import root.Services.GuestService;

@Service
@ComponentScan("root/Repositories")
public class GuestServiceImpl implements GuestService
{
    @Autowired
    GuestEntityRepository guestService;

    @Autowired
    DoorEntityRepository doorEntityRepository;

    @Override
    public Optional<GuestEntity> findOneByPhone(String phone)
    {

        return guestService.findOneByphone(phone);
    }

    @Override
    public GuestEntity addGuest(String name, String guestPhone, Boolean enabled)
    {
        guestService.findOneByphone(guestPhone)
                .ifPresent(guestEntity ->
                {
                    throw new GuestAlreadyExistException(guestEntity.getPhone());
                });
        GuestEntity guestEntity = new GuestEntity(name, guestPhone, enabled);
        return guestService.save(guestEntity);
    }

    @Override
    public GuestEntity setEnableGuest(String guestPhone, Boolean enabled)
    {
        GuestEntity guestEntity = guestService.findOneByphone(guestPhone)
                .orElseThrow(() -> new GuestNotFoundException(guestPhone));
        guestEntity.setEnabled(enabled);
        return guestService.save(guestEntity);
    }

    @Override
    public GuestEntity allowDoor(String guestPhone, String doorPhone)
    {
        DoorEntity checkingDoor = doorEntityRepository.findOneByphone(doorPhone)
                .orElseThrow(() -> new DoorNotFoundException(doorPhone));
        GuestEntity guestEntity = guestService.findOneByphone(guestPhone)
                .orElseThrow(() -> new GuestNotFoundException(guestPhone));
        Set<DoorEntity> allowedDoors = guestEntity.getAccessedDoors();
        if (allowedDoors.contains(checkingDoor))
        {
            throw new GuestAlreadyHaveThisDoorException(guestPhone, doorPhone);
        }
        else
        {
            allowedDoors.add(checkingDoor);
        }
        return guestService.save(guestEntity);
    }

    @Override
    public GuestEntity dennyDoor(String guestPhone, String doorPhone)
    {
        DoorEntity checkingDoor = doorEntityRepository.findOneByphone(doorPhone)
                .orElseThrow(() -> new DoorNotFoundException(doorPhone));
        GuestEntity guestEntity = guestService.findOneByphone(guestPhone)
                .orElseThrow(() -> new GuestNotFoundException(guestPhone));
        Set<DoorEntity> allowedDoors = guestEntity.getAccessedDoors();

        allowedDoors.remove(checkingDoor);

        return guestService.save(guestEntity);
    }

    @Override
    public GuestEntity updateGuest(Map<String, String> params)
    {
//        GuestEntity foundedGuestEntity = guestService.findById(guestEntity.getId())
//                .orElseThrow(() -> new GuestNotFoundException(guestEntity.getPhone()));
//        throw new RuntimeException("Not Implemeted");

        if (params.containsKey("id"))
        {
            long id = Long.valueOf(params.get("id"));
            String guestPhone = params.getOrDefault("guestPhone", "");
            String guestName = params.getOrDefault("guestName", "");
            Boolean enabled = Boolean.valueOf(params.getOrDefault("enabled", "false"));


            GuestEntity guestEntity = guestService.findById(id)
                    .orElseThrow(() -> new GuestNotFoundException(guestPhone));
            guestEntity.setEnabled(enabled);
        }
        return null;
    }

    @Override
    public Boolean checkDoorForGuest(String guestPhone, String doorPhone)
    {
        DoorEntity checkingDoor = doorEntityRepository.findOneByphone(doorPhone)
                .orElseThrow(() -> new DoorNotFoundException(doorPhone));
        GuestEntity guestEntity = guestService.findOneByphone(guestPhone)
                .orElseThrow(() -> new GuestNotFoundException(guestPhone));
        if (guestEntity.getAccessedDoors()
                .contains(checkingDoor) && guestEntity.isEnabled())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
