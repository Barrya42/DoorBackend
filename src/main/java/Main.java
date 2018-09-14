import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("Services.ServicesImpl")
@EnableAutoConfiguration
//@Import(DataConfig.class)
public class Main
{

    public static void main(String[] args)
    {
        SpringApplication.run(new Class<?>[]{Main.class}, args);

    }


}
