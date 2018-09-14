package root.Entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Сущность роли пользователя, который будет непосредственно работать с программой(сотрудник УК или мы).
@Entity
public class RoleEntity
{
    @Id
    //@GeneratedValue
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
