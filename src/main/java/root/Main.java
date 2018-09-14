package root;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import root.Services.ServicesImpl.DoorServiceImpl;

@EnableAutoConfiguration
@Configuration
@ComponentScan("root")
//@ComponentScan("root.Services")

//@Import(DataConfig.class)
public class Main
{

    public static void main(String[] args)
    {
        SpringApplication.run(new Class<?>[]{Main.class}, args);

    }

    @Bean
    CommandLineRunner init(DoorServiceImpl doorService
    )
    {
        return (evt) ->
        {
            System.out.print(doorService.Count());
        };
    }
}
