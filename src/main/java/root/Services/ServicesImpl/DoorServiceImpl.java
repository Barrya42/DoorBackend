package root.Services.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

import root.Entitys.DoorEntity;
import root.Repositories.DoorEntityRepository;
import root.Services.DoorService;

@Service
@ComponentScan("root/Repositories")
public class DoorServiceImpl implements DoorService
{
    @Autowired
    DoorEntityRepository doorEntityRepository;


    @Override
    public Optional<DoorEntity> findOneByPhone(String phone)
    {
        return doorEntityRepository.findOneByphone(phone);
    }
}
