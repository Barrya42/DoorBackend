package root.Services.ServicesImpl;

import com.sun.xml.internal.bind.v2.TODO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import root.Entitys.DoorEntity;
import root.Entitys.GuestEntity;
import root.Repositories.DoorEntityRepository;
import root.Repositories.GuestEntityRepository;
import root.Services.Exceptions.Door.DoorNotFoundException;
import root.Services.Exceptions.Guest.GuestAlreadyExistException;
import root.Services.Exceptions.Guest.GuestAlreadyHaveThisDoorException;
import root.Services.Exceptions.Guest.GuestNotFoundException;
import root.Services.GuestService;
import root.Tools.PhoneTools;

@Service
@ComponentScan("root/Repositories")
public class GuestServiceImpl implements GuestService
{
    @Autowired
    GuestEntityRepository guestService;

    @Autowired
    @Qualifier("guestEntityManager")
    EntityManager entityManager;

    @Autowired
    DoorEntityRepository doorEntityRepository;

    @Override
    public Optional<GuestEntity> findOneByPhone(String phone)
    {

        return guestService.findOneByphone(phone);
    }

    @Override
    public GuestEntity addGuest(GuestEntity sourceGuestEntity)
    {
        sourceGuestEntity.setPhone(PhoneTools.preparePhone(sourceGuestEntity.getPhone()));
        //GuestEntity guestEntity;
        // TODO: 04.10.2018 не работает !!!!!!!!!
        guestService.findById(sourceGuestEntity.getId())
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
