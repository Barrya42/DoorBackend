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
import root.Repositories.DoorEntityRepository;
import root.Services.DoorService;
import root.Services.Exceptions.Guest.GuestAlreadyExistException;
import root.Tools.PhoneTools;

@Service
@ComponentScan("root/Repositories")
public class DoorServiceImpl implements DoorService
{
    @Autowired
    private
    DoorEntityRepository doorEntityRepository;

    @Autowired
    @Required
    @Qualifier("doorEntityManager")
    private
    EntityManager entityManager;

    @Override
    public Optional<DoorEntity> findOneByPhone(String phone)
    {
        return doorEntityRepository.findOneByphone(phone);
    }

    @Override
    public DoorEntity addDoor(DoorEntity sourceDoorEntity)
    {
        sourceDoorEntity.setPhone(PhoneTools.preparePhone(sourceDoorEntity.getPhone()));
        doorEntityRepository.findById(sourceDoorEntity.getId())
                .ifPresent(guestEntity1 ->
                {
                    throw new GuestAlreadyExistException(guestEntity1.getPhone());
                });
        try
        {
            entityManager.getTransaction()
                    .begin();
            entityManager.merge(sourceDoorEntity);
            entityManager.flush();
            entityManager.getTransaction()
                    .commit();
        }
        catch (PersistenceException e)
        {
            throw new GuestAlreadyExistException(sourceDoorEntity.getPhone());
        }
        catch (RuntimeException e)
        {
            entityManager.getTransaction()
                    .rollback();
        }

        return sourceDoorEntity;
    }

    @Override
    // TODO: 05.10.2018 Подумать над использованием ролей или еще чего то ибо получается что мы можем авторизовавшись подлюбой ролью у которой есть доступ кэтому методу и полность обновить карточку
    public DoorEntity updateDoor(DoorEntity sourceDoorEntity)
    {
        sourceDoorEntity.setPhone(PhoneTools.preparePhone(sourceDoorEntity.getPhone()));
        entityManager.getTransaction()
                .begin();
        DoorEntity doorEntity = entityManager.merge(sourceDoorEntity);
        entityManager.flush();
        entityManager.getTransaction()
                .commit();
        return doorEntity;
    }
}
