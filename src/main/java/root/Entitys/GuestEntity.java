package root.Entitys;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import root.Tools.PhoneTools;

@Entity
@Table(name = "Guests", uniqueConstraints =
@UniqueConstraint(columnNames = {"id", "phone"}))
public class GuestEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean enabled;

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPhone(String phone)
    {
        // TODO: 05.10.2018 проверить заходит ли десериализатор сюда
        this.phone = phone;
    }

    private String name;

    public void setId(long id)
    {
        this.id = id;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public void setAccessedDoors(Set<DoorEntity> accessedDoors)
    {
        this.accessedDoors = accessedDoors;
    }

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

    @Override
    public boolean equals(Object obj)
    {
        GuestEntity other = (GuestEntity) obj;
        if (PhoneTools.preparePhone(phone)
                .equals(PhoneTools.preparePhone(other.phone)))
        {
            return true;
        }
        return super.equals(obj);
    }
}
