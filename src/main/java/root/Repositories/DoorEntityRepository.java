package root.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import root.Entitys.DoorEntity;


public interface DoorEntityRepository extends JpaRepository<DoorEntity, Long>
{
}
