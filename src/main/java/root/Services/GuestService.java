package root.Services;

import java.util.Optional;

import root.Entitys.GuestEntity;

public interface GuestService
{
    Optional<GuestEntity> findOneByPhone(String phone);

    GuestEntity addGuest(String name, String phone, Boolean enabled);

    GuestEntity setEnableGuest(String guestPhone, Boolean enabled);

    GuestEntity allowDoor(String guestPhone, String doorPhone);

    GuestEntity dennyDoor(String guestPhone, String doorPhone);

    GuestEntity updateGuest(GuestEntity params);

    Boolean checkDoorForGuest(String guestPhone, String doorPhone);
}
