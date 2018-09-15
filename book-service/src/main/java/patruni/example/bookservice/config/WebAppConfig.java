package patruni.example.bookservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"patruni.example.bookservice.config", "patruni.example.bookservice.controllers"})
public class WebAppConfig extends WebMvcConfigurerAdapter {

}
