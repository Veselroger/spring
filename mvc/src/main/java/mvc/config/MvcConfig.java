package mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Web MVC configuration.
 * Of type {@link WebMvcConfigurer} to customize Spring Web MVC.
 *
 * @see <a href=https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-config/view-controller.html>Spring View Controllers</a>
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home.html");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}