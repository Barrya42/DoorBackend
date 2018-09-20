package root.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import root.Entitys.DoorEntity;
import root.Entitys.GuestEntity;


public interface DoorEntityRepository extends JpaRepository<DoorEntity, Long>
{
    public Optional<DoorEntity> findOneByphone(String phone);
}
