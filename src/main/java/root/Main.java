package root;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.EntityManager;

import root.Services.ServicesImpl.DoorServiceImpl;
import root.Services.ServicesImpl.GuestServiceImpl;

@EnableAutoConfiguration
@Configuration
@ComponentScan("root")
//@ComponentScan("root.Services")

//@Import(DataConfig.class)
public class Main
{
@Autowired
GuestServiceImpl guestService;
    public static void main(String[] args)
    {
        SpringApplication.run(new Class<?>[]{Main.class}, args);

    }

    @Bean
    CommandLineRunner init(GuestServiceImpl guestService
    )
    {

        return (evt) ->
        {

            //System.out.print(guestService.findOneByPhone("+79130744802"));
        };
    }
}
