package root.Services.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

import root.Entitys.GuestEntity;
import root.Repositories.GuestEntityRepository;
import root.Services.GuestService;
import root.Tools.PhoneTools;

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

}
