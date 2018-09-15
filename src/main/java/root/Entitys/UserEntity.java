package root.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Сущьность пользователя, который будет непосредственно работать с программой(сотрудник УК или мы).
@Entity
@Table(name = "Users")
public class UserEntity
{
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String username;
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

    public String getUsername()
    {
        return username;
    }

    UserEntity()
    {
    }
}
