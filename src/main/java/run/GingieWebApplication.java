package run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//http://localhost:8080
@SpringBootApplication(scanBasePackages = {"run", "controllers", "models", "services"})
public class GingieWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GingieWebApplication.class, args);
    }

}
