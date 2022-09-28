package principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "controller")
public class PrincipalSpring {

	
	public static void main(String[] args) {
		SpringApplication.run(PrincipalSpring.class, args);

	}

}
