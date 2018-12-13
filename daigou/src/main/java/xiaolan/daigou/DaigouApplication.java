package xiaolan.daigou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"xiaolan.daigou"})
@EnableJpaRepositories(basePackages = "xiaolan.daigou.dao")
public class DaigouApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaigouApplication.class, args);
	}
}
