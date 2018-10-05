package root.Entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//Сущность роли пользователя, который будет непосредственно работать с программой(сотрудник УК или мы).
@Entity
@Table(name = "Roles", uniqueConstraints =
@UniqueConstraint(columnNames = {"id", "name"}))
public class RoleEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    RoleEntity()
    {

    }
}
