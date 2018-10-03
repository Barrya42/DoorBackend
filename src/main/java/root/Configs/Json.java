package root.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class Json
{
    @Bean(name = "jsonConverter")
    public HttpMessageConverter jsonConverter()
    {
        List<MediaType> supported = new ArrayList<MediaType>();
        supported.add(MediaType.APPLICATION_JSON);
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new org.springframework.http.converter.json
                .MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(supported);
        return mappingJackson2HttpMessageConverter;
    }
}
