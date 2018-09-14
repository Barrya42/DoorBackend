package root.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import root.Entitys.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>
{
}
