package root.Services.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        return  guestService.findOneByphone(phone);
    }
}
