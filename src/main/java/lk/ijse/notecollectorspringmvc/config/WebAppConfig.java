package lk.ijse.notecollectorspringmvc.config;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "lk.ijse.notecollectorspringmvc")
@EnableWebMvc // to get true power of the spring web mvc project
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
) // requset eken ena multiple parts handle karanna kiyala  kiyanne meken
public class WebAppConfig {

}
