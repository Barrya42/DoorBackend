package root.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import root.Entitys.GuestEntity;
import root.Entitys.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity,Long>
{
    public Optional<UserEntity> findOneByphone(String phone);
    public Optional<UserEntity> findOneByusername(String Username);
}
