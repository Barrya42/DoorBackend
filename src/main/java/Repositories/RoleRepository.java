package Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Entitys.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>
{
}
