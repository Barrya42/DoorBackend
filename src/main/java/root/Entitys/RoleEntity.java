package root.Entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Сущность роли пользователя, который будет непосредственно работать с программой(сотрудник УК или мы).
@Entity
@Table(name="Roles")
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
