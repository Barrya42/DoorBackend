package root.Entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import root.Tools.PhoneTools;

//Сущньсть непосредственно двери/шлакбаума/калитки
@Entity
@Table(name = "Doors", uniqueConstraints =
@UniqueConstraint(columnNames = {"id", "phone"}))
public class DoorEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String adress;
    private String phone;
    private float balance;
    private boolean isOpen;
    private boolean active;

    public boolean isActive()
    {
        return this.active;
    }

    public long getId()
    {
        return id;
    }

    public String getAdress()
    {
        return adress;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public float getBalance()
    {
        return balance;
    }

    public boolean isOpen()
    {
        return isOpen;
    }

    DoorEntity()
    {

    }

    @Override
    public boolean equals(Object obj)
    {
        DoorEntity other = (DoorEntity) obj;
        if (PhoneTools.preparePhone(phone)
                .equals(PhoneTools.preparePhone(other.phone)) || other.getId() == ((DoorEntity) obj).getId())
        {
            return true;
        }
        return super.equals(obj);
    }
}
