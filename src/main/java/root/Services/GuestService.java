package root.Services;

import java.util.Optional;

import root.Entitys.GuestEntity;

public interface GuestService
{
    Optional<GuestEntity> findOneByPhone(String phone);

    GuestEntity addGuest(GuestEntity newGuestEntity);

    GuestEntity updateGuest(GuestEntity params);

    Boolean checkDoorForGuest(String guestPhone, String doorPhone);
}
