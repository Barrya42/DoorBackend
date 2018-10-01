package root.Services;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import root.Entitys.DoorEntity;
import root.Entitys.GuestEntity;

public interface GuestService
{
    Optional<GuestEntity> findOneByPhone(String phone);

    Optional<GuestEntity> addGuest(String name, String phone, Boolean enabled);

    Optional<GuestEntity> findByID(long id);

    GuestEntity allowDoor(GuestEntity guestEntity, DoorEntity doorEntity);

    GuestEntity dennyDoor(GuestEntity guestEntity, DoorEntity checkingDoor);
}
