package $package$.$name;format="normalize"$;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("$package$.$name$.dao")
@ComponentScan(value = { "$package$" })
public class $name;format="Camel"$Application {

    public static void main(String[] args) {
        SpringApplication.run($name;format="Camel"$Application.class, args);
    }

}
