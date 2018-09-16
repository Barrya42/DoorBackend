package root.Services.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Optional;

import root.Entitys.GuestEntity;
import root.Services.UserService;

public class UserServiceImpl implements UserService
{
    @Autowired
    UserDetailsManager userDetailsManager;

    @Override
    public Optional<GuestEntity> findOneByPhone(String phone)
    {

        return Optional.empty();
    }

    @Override
    public Optional<GuestEntity> findOneByUsername(String username)
    {
        return Optional.empty();
    }
}
