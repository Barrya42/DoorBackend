package Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import Entitys.DoorEntity;


public interface DoorEntityRepository extends JpaRepository<DoorEntity, Long>
{
}
