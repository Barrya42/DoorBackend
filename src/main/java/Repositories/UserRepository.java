package Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Entitys.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>
{
}
