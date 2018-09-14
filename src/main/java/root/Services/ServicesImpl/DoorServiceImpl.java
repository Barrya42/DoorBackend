package root.Services.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import root.Repositories.DoorEntityRepository;
import root.Services.DoorService;

@Service
@ComponentScan("root/Repositories")
public class DoorServiceImpl implements DoorService
{
    @Autowired
    DoorEntityRepository doorEntityRepository;

    @Override
    public long Count()
    {
        return doorEntityRepository.count();
    }
}
