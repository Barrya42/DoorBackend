package root.Entitys;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "Guests", uniqueConstraints =
@UniqueConstraint(columnNames = {"id", "phone"}))
public class GuestEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
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

    public GuestEntity(String name, String phone, Boolean enabled)
    {
        this.enabled = enabled;
        this.name = name;
        this.phone = phone;
        this.accessedDoors = new HashSet<>();
    }

    public GuestEntity()
    {

    }

    @Override
    public String toString()
    {
        return "id: " + String.valueOf(id) +
                "phone: " + phone + ";\n" +
                "name: " + name + ";\n" +
                (enabled ? "Активен" : "Не активен");
    }

}
