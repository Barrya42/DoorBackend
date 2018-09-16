package root.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Сущьность пользователя, который будет непосредственно работать с программой(сотрудник УК или мы).
@Entity
//@Transactional
@Table(name = "Users")
public class UserEntity
{
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    @Id
    @GeneratedValue
    private long id;
    private boolean enabled;
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

    public String[] getRolesArray()
    {
        Iterator iterator = roles.iterator();
        String[] res = new String[roles.size()];
        int i = 0;
        while (iterator.hasNext())
        {
            RoleEntity roleEntity = (RoleEntity) iterator.next();
            res[i] = roleEntity.getName();
            i++;
        }
        return res;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUsername()
    {
        return username;
    }

    public boolean getEnabled()
    {
        return enabled;
    }

    UserEntity()
    {
    }
}
