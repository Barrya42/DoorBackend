package root.Services;

import java.util.Optional;

import root.Entitys.DoorEntity;

public interface DoorService
{
    Optional<DoorEntity> findOneByPhone(String phone);

    DoorEntity addDoor(DoorEntity newDoorEntity);

    DoorEntity updateDoor(DoorEntity doorEntity);
}
