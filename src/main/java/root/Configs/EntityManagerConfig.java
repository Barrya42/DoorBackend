package root.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
public class EntityManagerConfig
{
    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean("guestEntityManager")
    public EntityManager entityManager()
    {
        return entityManagerFactory.createEntityManager();
    }
}
