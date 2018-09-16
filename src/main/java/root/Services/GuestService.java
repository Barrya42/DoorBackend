package root.Services;

import java.util.Map;
import java.util.Optional;

import root.Entitys.GuestEntity;

public interface GuestService
{
    public Optional<GuestEntity> findOneByPhone(String phone);
    public Optional<GuestEntity> addGuest(String name, String phone, Boolean enabled);
    public Optional<GuestEntity> findByID(long id);
}
