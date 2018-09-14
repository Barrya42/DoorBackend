package root.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import root.Entitys.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>
{
}
