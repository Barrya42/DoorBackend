package root.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//Сущьность пользователя, который будет непосредственно работать с программой(сотрудник УК или мы).
@Entity
public class UserEntity
{
    @Id
    //@GeneratedValue
    private long id;

    private String name;
    private String phone;
    @JsonIgnore
    private String password;

    @OneToMany
    private Set<RoleEntity> roles;

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getPhone()
    {
        return phone;
    }

    public Set<RoleEntity> getRoles()
    {
        return roles;
    }

    public String getPassword()
    {
        return password;
    }

    UserEntity()
    {
    }
}
