package root.Entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Сущньсть непосредственно двери/шлакбаума/калитки
@Entity
public class DoorEntity
{
    @Id
    //@GeneratedValue
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

}
