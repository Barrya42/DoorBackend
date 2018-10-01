package root.Services.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

import root.Entitys.DoorEntity;
import root.Entitys.GuestEntity;
import root.Repositories.GuestEntityRepository;
import root.Services.GuestService;

@Service
@ComponentScan("root/Repositories")
public class GuestServiceImpl implements GuestService
{
    @Autowired
    GuestEntityRepository guestService;

    @Override
    public Optional<GuestEntity> findOneByPhone(String phone)
    {

        return guestService.findOneByphone(phone);
    }

    @Override
    public Optional<GuestEntity> addGuest(String name, String phone, Boolean enabled)
    {
        GuestEntity guestEntity = new GuestEntity(name, phone, enabled);
        guestService.save(guestEntity);
        return Optional.of(guestService.save(guestEntity));
    }

    @Override
    public Optional<GuestEntity> findByID(long id)
    {
        return guestService.findById(id);
    }

    @Override
    public GuestEntity allowDoor(GuestEntity guestEntity, DoorEntity doorEntity)
    {
        guestEntity.getAccessedDoors()
                .add(doorEntity);
        return guestService.save(guestEntity);
    }

    @Override
    public GuestEntity dennyDoor(GuestEntity guestEntity, DoorEntity denyingDoor)
    {
        guestEntity.getAccessedDoors()
                .remove(denyingDoor);
        return guestService.save(guestEntity);
    }

}
