package root.Services.ServicesImpl;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import root.Entitys.DoorEntity;
import root.Entitys.GuestEntity;
import root.Repositories.DoorEntityRepository;
import root.Repositories.GuestEntityRepository;
import root.Services.Exceptions.Door.DoorNotFoundException;
import root.Services.Exceptions.Guest.GuestAlreadyExistException;
import root.Services.Exceptions.Guest.GuestNotFoundException;
import root.Services.GuestService;
import root.Tools.PhoneTools;

@Service
@ComponentScan("root/Repositories")
public class GuestServiceImpl implements GuestService
{
    @Autowired
    private GuestEntityRepository guestEntityRepository;

    @Autowired
    @Required
    @Qualifier("guestEntityManager")
    private EntityManager entityManager;

    @Autowired
    private DoorEntityRepository doorEntityRepository;

    @Override
    public Optional<GuestEntity> findOneByPhone(String phone)
    {
        return guestEntityRepository.findOneByphone(phone);
    }

    @Override
    public GuestEntity addGuest(GuestEntity sourceGuestEntity)
    {
        sourceGuestEntity.setPhone(PhoneTools.preparePhone(sourceGuestEntity.getPhone()));
        //GuestEntity guestEntity;
        guestEntityRepository.findById(sourceGuestEntity.getId())
                .ifPresent(guestEntity1 ->
                {
                    throw new GuestAlreadyExistException(guestEntity1.getPhone());
                });
        try
        {
            entityManager.getTransaction()
                    .begin();
            entityManager.merge(sourceGuestEntity);
            entityManager.flush();
            entityManager.getTransaction()
                    .commit();
        }
        catch (PersistenceException e)
        {
            throw new GuestAlreadyExistException(sourceGuestEntity.getPhone());
        }
        catch (RuntimeException e)
        {
            entityManager.getTransaction()
                    .rollback();
        }

        return sourceGuestEntity;
    }

    @Override
    public GuestEntity updateGuest(GuestEntity sourceGuestEntity)
    {
        sourceGuestEntity.setPhone(PhoneTools.preparePhone(sourceGuestEntity.getPhone()));
        entityManager.getTransaction()
                .begin();
        GuestEntity guestEntity = entityManager.merge(sourceGuestEntity);
        entityManager.flush();
        entityManager.getTransaction()
                .commit();
        return guestEntity;
    }

    @Override
    public Boolean checkDoorForGuest(String guestPhone, String doorPhone)
    {
        DoorEntity checkingDoor = doorEntityRepository.findOneByphone(doorPhone)
                .orElseThrow(() -> new DoorNotFoundException(doorPhone));
        GuestEntity guestEntity = guestEntityRepository.findOneByphone(guestPhone)
                .orElseThrow(() -> new GuestNotFoundException(guestPhone));
        if (guestEntity.getAccessedDoors()
                .contains(checkingDoor) && guestEntity.isEnabled() && checkingDoor.isActive())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
