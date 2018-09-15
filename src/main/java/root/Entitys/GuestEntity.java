package root.Entitys;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Guests", uniqueConstraints=
@UniqueConstraint(columnNames={"id", "phone"}))
public class GuestEntity
{
    @Id
    @GeneratedValue
    private long id;
    private boolean enabled;
    private String name;
    private String phone;
    @OneToMany
    private Set<DoorEntity> accessedDoors;

    public long getId()
    {
        return id;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public String getName()
    {
        return name;
    }

    public String getPhone()
    {
        return phone;
    }

    public Set<DoorEntity> getAccessedDoors()
    {
        return accessedDoors;
    }

    @Override
    public String toString()
    {
        return "phone: " + phone + ";\n" +
                "name: " + name + ";\n" +
                (enabled ? "Активен" : "Не активен");
    }

}
