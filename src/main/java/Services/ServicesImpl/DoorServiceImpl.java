package Services.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Repositories.DoorEntityRepository;
import Services.DoorService;

@Service
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
