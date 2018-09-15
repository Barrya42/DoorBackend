package root.Services;

import java.util.Optional;

import root.Entitys.GuestEntity;

public interface GuestService
{
    public Optional<GuestEntity> findOneByPhone(String phone);
}
