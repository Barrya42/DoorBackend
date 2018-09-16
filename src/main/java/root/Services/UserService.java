package root.Services;

import java.util.Optional;

import root.Entitys.GuestEntity;

public interface UserService
{
    public Optional<GuestEntity> findOneByPhone(String phone);
    public Optional<GuestEntity> findOneByUsername(String username);
}
