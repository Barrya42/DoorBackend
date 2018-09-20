package root.Entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import root.Tools.PhoneTools;

//Сущньсть непосредственно двери/шлакбаума/калитки
@Entity
@Table(name = "Doors")
public class DoorEntity
{
    @Id
    @GeneratedValue
    private long id;
    private String adress;
    private String mac;
    private String phone;
    private float balance;
    private boolean isOpen;

    public long getId()
    {
        return id;
    }

    public String getAdress()
    {
        return adress;
    }

    public String getMac()
    {
        return mac;
    }

    public String getPhone()
    {
        return phone;
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
                .equals(PhoneTools.preparePhone(other.phone)))
        {
            return true;
        }
        return super.equals(obj);
    }
}
