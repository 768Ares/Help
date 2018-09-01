package pl.storm;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@ComponentScan(basePackages = "pl.storm")
@EnableAspectJAutoProxy
public class AppConfiguration {

}
