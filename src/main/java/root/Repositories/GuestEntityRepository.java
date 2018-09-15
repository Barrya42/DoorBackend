package root.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import root.Entitys.GuestEntity;

public interface GuestEntityRepository extends JpaRepository<GuestEntity, Long>
{
    public Optional<GuestEntity> findOneByphone(String phone);
}
